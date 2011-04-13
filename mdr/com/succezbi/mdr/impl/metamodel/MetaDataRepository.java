package com.succezbi.mdr.impl.metamodel;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MetaDataRepository {

	public MetaDataRepository() {
	}

	public void register(String xml, MetaExtent extent) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xml);
		this.regist(doc, extent);
	}

	public void register(InputStream is, MetaExtent extent) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
		this.regist(doc, extent);
	}

	/**
	 * 遍历过程更可以分为两次：
	 * 1，第一次，加载其中的Class定义，将之载入到MetaExtent中，注册到hiberante存储
	 * 2，第二次，解析xml的细节，将元模型存储到hibernate库中
	 * 这样分两次解析是为了存储元模型时，保证相应的Class定义已经载入到了hiberante
	 * @param doc
	 * @param extent
	 */
	private void regist(Document doc, MetaExtent extent) {
		//分析过程中缓存涉及的metaclass，最终映射好后再将之转入存储
		this.registModels(doc, extent);
		Session session = extent.getSession();
		Transaction tx = session.beginTransaction();
		try {
			this.persistModels(doc, session);
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new RuntimeException("载入模板失败");
		}
		finally {
			session.close();
		}

	}

	private void registModels(Document doc, MetaExtent extent) {
		Element root = doc.getRootElement();
		if (!"XMI".equalsIgnoreCase(root.getName())) {
			throw new RuntimeException("模型文件根节点应该为xmi，而传入的模型文件根节点为" + root.getName());
		}
		List contents = root.elements("XMI.content");
		for (int i = 0; i < contents.size(); i++) {
			Element content = (Element) contents.get(i);
			List models = content.elements("Model");
			for (int j = 0; j < models.size(); j++) {
				Element model = (Element) models.get(j);
				List classes = model.elements("class");
				this.registMetaClasses(classes, extent);
			}
			extent.rebuild();
		}

	}

	private void registMetaClasses(List classes, MetaExtent extent) {
		for (int i = 0; i < classes.size(); i++) {
			Element clazz = (Element) classes.get(i);
			Element classpath = clazz.element("classpath");
			extent.addMetaClass(classpath.getText());
		}
	}

	/**
	 * 将元模型定义固化到hibernate存储层中
	 * @param doc
	 * @param session
	 */
	private void persistModels(Document doc, Session session) {
		Element root = doc.getRootElement();
		if (!"XMI".equalsIgnoreCase(root.getName())) {
			throw new RuntimeException("模型文件根节点应该为xmi，而传入的模型文件根节点为" + root.getName());
		}
		Iterator it = root.elementIterator();
		while (it.hasNext()) {
			Element ele = (Element) it.next();
			String name = ele.getName();
			if ("XMI.header".equalsIgnoreCase(name)) {
				//this.parseXmiHeader(ele);
			}
			else if ("XMI.extensions".equalsIgnoreCase(name)) {
				//this.parseXmiExtensions(ele);
			}
			else if ("XMI.content".equalsIgnoreCase(name)) {
				this.persistContent(ele, session);
			}
		}
	}

	private void persistContent(Element ele, Session session) {
		Iterator ei = ele.elementIterator();
		while (ei.hasNext()) {
			Element child = (Element) ei.next();
			String childname = child.getName();
			String prefix = child.getNamespacePrefix();
			if ("SBI".equalsIgnoreCase(prefix) && "model".equalsIgnoreCase(childname)) {
				this.persistModel(child, session);
			}
		}
	}

	private void persistModel(Element ele, Session session) {
		String modelname = ele.attributeValue("name");
		MetaPackage pkg = new MetaPackage();
		pkg.setName(modelname);
		pkg = this.saveOrUpdateMetaPackage(pkg, session);
		Iterator ei = ele.elementIterator();
		while (ei.hasNext()) {
			Element child = (Element) ei.next();
			String prefix = child.getNamespacePrefix();
			String childname = child.getName();
			if ("SBI".equalsIgnoreCase(prefix) && "class".equalsIgnoreCase(childname)) {
				this.persistMetaClasses(child, pkg, session);
			}
		}
	}

	private void persistMetaClasses(Element ele, MetaPackage pkg, Session session) {
		MetaClass metaclass = new MetaClass();
		String classname = ele.attributeValue("name");
		metaclass.setName(classname);
		metaclass.setPkg(pkg);
		Iterator it = ele.elementIterator();
		while (it.hasNext()) {
			Element child = (Element) it.next();
			String name = child.getName();
			if ("classpath".equalsIgnoreCase(name)) {
				System.out.println(child.getText());
				String path = child.getText();
				metaclass.setClasspath(path);
			}
			else if ("superclass".equalsIgnoreCase(name)) {
				String hql = "from MetaClass where classpath=:path";
				Query query = session.createQuery(hql);
				query.setString("path", name);
				List value = query.list();
				int size = value.size();
				if(size == 1){
					MetaClass superclass = (MetaClass) value.get(0);
					metaclass.setSuperclass(superclass);
				}else{
					
				}
			}
		}
		metaclass = this.saveOrUpdateMetaClass(metaclass, session);
		Iterator features = ele.elementIterator("feature");
		while (features.hasNext()) {
			Element feature = (Element) features.next();
			this.persistMetaFeatures(feature, pkg, metaclass, session);
		}
	}

	private void persistMetaFeatures(Element ele, MetaPackage pkg, MetaClass metaclass, Session session) {
		Iterator it = ele.elementIterator();
		while (it.hasNext()) {
			Element child = (Element) it.next();
			String name = child.getName();
			if ("Attribute".equalsIgnoreCase(name)) {
				MetaAttribute attribute = new MetaAttribute();
				String attrname = child.attributeValue("name");
				System.out.println("Attribute:" + attrname);
				attribute.setName(attrname);
				attribute.setMetaclass(metaclass);
				this.saveOrUpdateMetaAttribute(attribute, metaclass, session);
			}
			else if ("Association".equalsIgnoreCase(name)) {
				MetaAssociation association = new MetaAssociation();
				association.setMetaclass(metaclass);
				String assname = child.attributeValue("name");
				System.out.println("Association:" + assname);
				//association.set
				String type = child.attributeValue("type");
				//MetaClass typeclass = pkg.getMetaClassByName(type);
				//association.setType(typeclass);
			}
		}
	}

	private MetaPackage saveOrUpdateMetaPackage(MetaPackage pkg, Session session) {
		Query query = session.createQuery("from MetaPackage where name=:name");
		query.setString("name", pkg.getName());
		List value = query.list();
		if (value.size() == 0) {
			session.save(pkg);
			return pkg;
		}
		else {
			return (MetaPackage) value.get(0);
		}
	}

	/**
	 * 注册MetaClass到数据库表MDR_METACLASS中，将之放置到数据库中，是为了方便通过数
	 * 据库直接查询某个类型的所有元数据实例等
	 * @param metaclass
	 * @param session
	 * @return
	 */
	private MetaClass saveOrUpdateMetaClass(MetaClass metaclass, Session session) {
		Query query = session.createQuery("from MetaClass where classpath=:path");
		String name = metaclass.getClasspath();
		query.setString("path", name);
		List value = query.list();
		if (value.size() == 0) {
			session.save(metaclass);
			return metaclass;
		}
		else {
			return (MetaClass) value.get(0);
		}
	}

	private MetaAttribute saveOrUpdateMetaAttribute(MetaAttribute attribute, MetaClass metaclass, Session session) {
		MetaAttribute.PrimaryKey id = new MetaAttribute.PrimaryKey();
		String name = attribute.getName();
		id.setName(name);
		id.setMetaclass(metaclass);
		Object value = session.get(MetaAttribute.class, id);
		if (value == null) {
			session.save(attribute);
			return attribute;
		}
		return (MetaAttribute) value;
	}

}
