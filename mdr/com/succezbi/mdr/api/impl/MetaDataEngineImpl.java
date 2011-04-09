package com.succezbi.mdr.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.succezbi.mdr.api.MetaDataEngine;
import com.succezbi.mdr.api.MetaDataEntity;
import com.succezbi.mdr.impl.core.ModelElement;
import com.succezbi.mdr.impl.metamodel.MetaClass;
import com.succezbi.mdr.impl.metamodel.MetaDataSlot;
import com.succezbi.mdr.impl.metamodel.MetaExtent;
import com.succezbi.mdr.impl.metamodel.MetaFactory;
import com.succezbi.mdr.impl.metamodel.MetaObject;
import com.succezbi.mdr.impl.metamodel.MetaPackage;

/**
 * 元数据引擎类实现
 * @author classfoo
 *
 */
public class MetaDataEngineImpl implements MetaDataEngine {

	private MetaExtent extent = null;

	private String name = null;

	private String id = null;

	private Map<String, Object> properties = new HashMap<String, Object>();

	public MetaDataEngineImpl() {
		extent = new MetaExtent();
		InputStream mis = MetaClass.class.getResourceAsStream("metamodel.xml");
		try {
			this.register(mis);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				mis.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		InputStream cis = ModelElement.class.getResourceAsStream("metamodel-core.xml");
		try {
			this.register(cis);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				cis.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void register(String xml) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xml);
		this.regist(doc);
	}

	public void register(InputStream is) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
		this.regist(doc);
	}

	private void regist(Document doc) {
		//分析过程中缓存涉及的metaclass，最终映射好后再将之转入存储
		Element root = doc.getRootElement();
		if (!"XMI".equalsIgnoreCase(root.getName())) {
			throw new RuntimeException("模型文件根节点应该为xmi，而传入的模型文件根节点为" + root.getName());
		}
		Iterator it = root.elementIterator();
		while (it.hasNext()) {
			Element ele = (Element) it.next();
			String name = ele.getName();
			if ("XMI.header".equalsIgnoreCase(name)) {
				this.parseXmiHeader(ele);
			}
			if ("XMI.extensions".equalsIgnoreCase(name)) {
				this.parseXmiExtensions(ele);
			}
			if ("XMI.content".equalsIgnoreCase(name)) {
				this.parseXmiContent(ele);
			}
		}
	}

	/**
	 * 注册分析xml过程中记录下的MetaClass列表
	 * @param metaclasses
	 */
	private void registMetaClasses(ArrayList<MetaClass> metaclasses) {
		for (int i = 0; i < metaclasses.size(); i++) {
			MetaClass metaclass = metaclasses.get(i);
			this.registMetaClass(metaclass);
		}
	}

	/**
	 * 注册MetaClass到数据库表MDR_METACLASS中，将之放置到数据库中，是为了方便通过数
	 * 据库直接查询某个类型的所有元数据实例等
	 * @param metaclass
	 */
	private void registMetaClass(MetaClass metaclass) {
		Session session = this.extent.getSession();
		Query query = session.createQuery("from MetaClass where name=:name");
		query.setString("name", metaclass.getName());
		List value = query.list();
		if (value.size() == 0) {
			Transaction tx = session.beginTransaction();
			try {
				session.save(metaclass);
				tx.commit();
			}
			catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			}
			finally {
				session.close();
			}
		}
	}

	private void parseXmiExtensions(Element ele) {
		Iterator ei = ele.elementIterator();
		while (ei.hasNext()) {
			Element child = (Element) ei.next();
		}
	}

	private void parseXmiHeader(Element ele) {

	}

	private void parseXmiContent(Element ele) {
		Iterator ei = ele.elementIterator();
		while (ei.hasNext()) {
			Element child = (Element) ei.next();
			String childname = child.getName();
			String prefix = child.getNamespacePrefix();
			if ("SBI".equalsIgnoreCase(prefix) && "model".equalsIgnoreCase(childname)) {
				this.parseSBIModel(child);
			}
		}
	}

	private void parseSBIModel(Element ele) {
		String modelname = ele.attributeValue("name");
		MetaPackage pkg = new MetaPackage();
		pkg.setExtent(this.extent);
		pkg.setName(modelname);

		ArrayList<MetaClass> metaclasses = new ArrayList<MetaClass>();
		Iterator ei = ele.elementIterator();
		while (ei.hasNext()) {
			Element child = (Element) ei.next();
			String prefix = child.getNamespacePrefix();
			String childname = child.getName();
			if ("SBI".equalsIgnoreCase(prefix) && "class".equalsIgnoreCase(childname)) {
				this.parseSBIClass(child, pkg, metaclasses);
			}
		}
		this.extent.rebuild();
		pkg.setClasses(metaclasses);
		this.registMetaPackage(pkg);
		this.registMetaClasses(metaclasses);

	}

	private void registMetaPackage(MetaPackage pkg) {
		Session session = this.extent.getSession();
		Query query = session.createQuery("from MetaPackage where name=:name");
		query.setString("name", pkg.getName());
		List value = query.list();
		if (value.size() == 0) {
			Transaction tx = session.beginTransaction();
			try {
				session.save(pkg);
				tx.commit();
			}
			catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
			}
			finally {
				session.close();
			}
		}
	}

	private void parseSBIClass(Element ele, MetaPackage pkg, ArrayList<MetaClass> metaclasses) {
		MetaClass metaclass = new MetaClass();
		String classname = ele.attributeValue("name");
		metaclass.setName(classname);
		Element classpath = ele.element("classpath");
		if (classpath != null) {
			System.out.println(classpath.getText());
			String path = classpath.getText();
			metaclass.setClasspath(path);
			this.extent.addMetaClass(path);
		}
		Element superclass = ele.element("superclass");
		if (superclass != null) {
			String superclassname = superclass.getText();
			metaclass.setSuperclass(superclassname);
		}
		metaclass.setPkg(pkg);
		metaclasses.add(metaclass);
	}

	public MetaDataEntity createNewEntity(String type, String name) {
		MetaDataEntityImpl entity = new MetaDataEntityImpl(this.extent);
		entity.setType(type);
		entity.setName(name);
		return entity;
	}

	public MetaDataEntity get(String id) {
		MetaDataEntityImpl result = new MetaDataEntityImpl(this.extent);
		result.setId(id);
		return result;
	}

	public MetaDataEntity get(MetaDataEntity parent, String type, String name) {
		Session session = this.extent.getSession();
		try {
			String hql = "select id from :type where name = :name and parentid = :parentid";
			Query query = session.createQuery(hql);
			query.setString(0, type);
			query.setString(1, name);
			query.setString(2, parent.getID());
			List value = query.list();
			int size = value.size();
			if (size == 0) {
				return null;
			}
			else if (size == 1) {
				String id = (String) value.get(0);
				Object obj = value.get(0);
				MetaDataEntityImpl entity = new MetaDataEntityImpl(this.extent);
				entity.setType(type);
				entity.setId(id);
				return entity;
			}
			else {
				throw new RuntimeException();
			}
		}
		finally {
			session.close();
		}
	}

	public void modifyEntity(String id, MetaDataEntityHandler handler) {
		//获取Entity
		MetaDataEntity entity = null;
		//影响分析
		handler.handle(entity);
		this.createNewEntity("", "", "");

	}

	public MetaDataEntity createNewEntity(String parentid, String type, String name) {
		MetaDataEntity entity = this.createNewEntity(type, name);
		entity.setParentID(parentid);
		return entity;
	}

	public MetaDataEntity get(String parentid, String type, String name) {
		return null;
	}

	public MetaDataEntity[] getEntityByType(String type) {
		Session session = extent.getSession();
		try {
			String hql = "select id from " + type;
			Query query = session.createQuery(hql);
			List value = query.list();
			int size = value.size();
			if (size == 0) {
				return null;
			}
			MetaDataEntity[] result = new MetaDataEntity[size];
			for (int i = 0; i < size; i++) {
				MetaDataEntityImpl entity = new MetaDataEntityImpl(this.extent);
				entity.setType(type);
				String id = (String) value.get(0);
				entity.setId(id);
				result[i] = entity;
			}
			return result;
		}
		finally {
			session.close();
		}
	}

	public int getEntityCountByType(String type) {
		Session session = extent.getSession();
		try {
			String hql = "from " + type;
			Query query = session.createQuery(hql);
			//query.setString(0, type);
			List list = query.list();
			return list.size();
		}
		finally {
			session.close();
		}
	}

	public String save(MetaDataEntity entity) throws IOException {
		MetaDataEntity cache = entity.getModifyCache();
		String type = cache.getType();
		String name = cache.getName();
		String parentid = cache.getParentID();
		Session session = this.extent.getSession();
		Transaction tx = session.beginTransaction();
		String objectid = null;
		try {
			MetaClass cls = this.findMetaClass(session, type);
			MetaPackage pkg = cls.getPkg();
			MetaFactory factory = pkg.getFactory();
			MetaObject obj = factory.create(cls, name, parentid);
			objectid = (String) session.save(obj);
			Map<String, Object> property = cache.getProperties();
			if (property != null) {
				Set<String> keys = property.keySet();
				Iterator<String> it = keys.iterator();
				while (it.hasNext()) {
					String key = it.next();
					Object value = property.get(key);
					MetaDataSlot slot = new MetaDataSlot();
					slot.setKey(key);
					slot.setValue(value);
					slot.setObject(obj);
					session.saveOrUpdate(slot);
				}
			}
			tx.commit();
		}
		finally {
			session.close();
		}
		return objectid;
	}

	private MetaClass findMetaClass(Session session, String type) {
		MetaClass cls = (MetaClass) session.load(MetaClass.class, type);
		return cls;
	}
}
