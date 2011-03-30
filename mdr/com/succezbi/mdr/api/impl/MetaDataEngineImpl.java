package com.succezbi.mdr.api.impl;

import java.io.InputStream;
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
import com.succezbi.mdr.impl.core.DataSlot;
import com.succezbi.mdr.impl.core.MetaObject;
import com.succezbi.mdr.impl.metamodel.MetaClass;
import com.succezbi.mdr.impl.metamodel.MetaExtent;
import com.succezbi.mdr.impl.metamodel.MetaFactory;
import com.succezbi.mdr.impl.metamodel.MetaPackage;

/**
 * 元数据引擎类实现
 * @author classfoo
 *
 */
public class MetaDataEngineImpl implements MetaDataEngine {

	private MetaExtent extent = null;

	public MetaDataEngineImpl() {
		extent = new MetaExtent();
	}

	public void register(String xml) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xml);
		this.register(doc);
	}

	public void register(InputStream is) throws Exception {
		SAXReader read = new SAXReader();
		Document doc = read.read(is);
		this.register(doc);
	}

	public void register(Document doc) {
		Element root = doc.getRootElement();
		if (!"model".equals(root.getName())) {
			throw new RuntimeException("模型文件根节点应该为model，而传入的模型文件根节点为" + root.getName());
		}
		Iterator it = root.elementIterator();
		while (it.hasNext()) {
			Element ele = (Element) it.next();
			String name = ele.getName();
			if ("hibernate-xml".equals(name)) {
				System.out.println();
			}
		}
	}

	public MetaDataEntity createNewEntity(String type, String name) {
		MetaDataEntityImpl entity = new MetaDataEntityImpl(this.extent, type);
		entity.setName(name);
		return entity;
	}

	public MetaDataEntity get(String id) {
		return null;
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
				MetaDataEntityImpl entity = new MetaDataEntityImpl(this.extent, type);
				entity.setID(id);
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
			String hql = "select id from :type";
			Query query = session.createQuery(hql);
			query.setString(0, type);
			List value = query.list();
			int size = value.size();
			if (size == 0) {
				return null;
			}
			MetaDataEntity[] result = new MetaDataEntity[size];
			for (int i = 0; i < size; i++) {
				MetaDataEntityImpl entity = new MetaDataEntityImpl(this.extent, type);
				String id = (String) value.get(0);
				entity.setID(id);
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
			String hql = "select count(id) from :type";
			Query query = session.createQuery(hql);
			query.setString(0, type);
			int count = query.executeUpdate();
			return count;
		}
		finally {
			session.close();
		}
	}

	public String save(MetaDataEntity entity) {
		String type = entity.getType();
		String name = entity.getName();
		String parentid = entity.getParentID();
		Session session = this.extent.getSession();
		Transaction tx = session.beginTransaction();
		try {
			MetaClass cls = this.findMetaClass(session, type);
			MetaPackage pkg = cls.getPkg();
			MetaFactory factory = pkg.getFactory();
			MetaObject obj = factory.create(cls, name, parentid);
			session.save(obj);
			Map<String, Object> property = entity.getProperties();
			Set<String> keys = property.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = property.get(key);
				DataSlot slot = new DataSlot();
				slot.setKey(key);
				slot.setValue(value);
				slot.setObject(obj);
				session.saveOrUpdate(slot);
			}
			tx.commit();
		}
		finally {
			session.close();
		}
		return null;
	}

	private MetaClass findMetaClass(Session session, String type) {
		MetaClass cls = (MetaClass) session.load(MetaClass.class, type);
		return cls;
	}
}
