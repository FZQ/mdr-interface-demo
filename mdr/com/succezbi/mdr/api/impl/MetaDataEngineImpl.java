package com.succezbi.mdr.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.succezbi.mdr.api.MetaDataEngine;
import com.succezbi.mdr.api.MetaDataEntity;
import com.succezbi.mdr.impl.core.ModelElement;
import com.succezbi.mdr.impl.metamodel.MetaClass;
import com.succezbi.mdr.impl.metamodel.MetaDataRepository;
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

	private MetaDataRepository repository = null;

	private String name = null;

	private String id = null;

	private Map<String, Object> properties = new HashMap<String, Object>();

	public MetaDataEngineImpl() {
		this.repository = new MetaDataRepository();
		this.extent = new MetaExtent();
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
		this.repository.register(xml, this.extent);
	}

	public void register(InputStream is) throws Exception {
		this.repository.register(is, this.extent);
	}

	public MetaDataEntity createNewEntity(String type, String name) {
		MetaDataEntityImpl entity = new MetaDataEntityImpl(this.extent);
		entity.setType(type);
		entity.setName(name);
		return entity;
	}

	public MetaDataEntity get(String id) {
		MetaDataEntityImpl result = new MetaDataEntityImpl(this.extent);
		result.setID(id);
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
				MetaDataEntityImpl entity = new MetaDataEntityImpl(this.extent);
				entity.setType(type);
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
		MetaObject obj = this.saveMetaObject(cache);
		Map<String, Object> properties = cache.getProperties();
		if (properties != null)
			this.saveProperties(obj, properties);
		String id = obj.getId();
		entity.setID(id);
		return id;
	}

	private MetaObject saveMetaObject(MetaDataEntity cache) {
		String type = cache.getType();
		Map<String, Object> map = cache.getProperties();
		Session session = this.extent.getSession();
		MetaClass cls = this.findMetaClass(session, type);
		MetaPackage pkg = cls.getPkg();
		MetaFactory factory = pkg.getFactory();
		MetaObject obj = factory.create(cls, map);
		Transaction tx = session.beginTransaction();
		try {
			session.save(obj);
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new RuntimeException(e);
		}
		finally {
			session.close();
		}
		return obj;
	}

	public void saveProperties(MetaObject obj, Map<String, Object> properties) throws IOException {
		Set<String> keys = properties.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = properties.get(key);
			Session session = this.extent.getSession();
			MetaDataSlot slot = new MetaDataSlot();
			slot.setDskey(key);
			slot.setMetaobject(obj);
			slot.setValue(value);
			Transaction tx = session.beginTransaction();
			try {
				session.saveOrUpdate(slot);
				tx.commit();
			}
			catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
				throw new RuntimeException(e);
			}
			finally {
				session.close();
			}
		}
	}

	private MetaClass findMetaClass(Session session, String type) {
		String hql = "from MetaClass where classpath=:type";
		Query query = session.createQuery(hql);
		query.setString("type", type);
		List values = query.list();
		int size = values.size();
		if(size == 0 || size > 1){
			throw new RuntimeException("无法查找MetaClass，同一个classpath不能有多条记录");
		}
		return (MetaClass) values.get(0);
	}
}
