package com.succezbi.mdr.api.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.succezbi.mdr.api.MetaDataEntity;
import com.succezbi.mdr.impl.metamodel.MetaDataSlot;
import com.succezbi.mdr.impl.metamodel.MetaExtent;
import com.succezbi.mdr.impl.metamodel.MetaObject;

public class MetaDataEntityImpl implements MetaDataEntity {

	private MetaExtent extent = null;

	private HashMap map = new HashMap();

	private MetaDataEntityCache cache = null;

	private String id = null;

	private String parentid = null;

	private String type = null;

	private String name = null;

	public MetaDataEntityImpl(MetaExtent extent) {
		this.cache = new MetaDataEntityCache();
		this.extent = extent;
	}

	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.cache.setId(id);
		this.id = id;
	}

	public String getName() {
		String hql = "select obj.name from ModelElement as obj where id=:id";
		Session session = this.extent.getSession();
		Query query = session.createQuery(hql);
		query.setString("id", this.getID());
		List list = query.list();
		int size = list.size();
		if (size == 1) {
			return (String) list.get(0);
		}
		else {
			if (size <= 0) {
				throw new RuntimeException();
			}
			else {
				throw new RuntimeException();
			}
		}
	}

	public String getType() {
		return this.type;
	}

	public String getParentID() {
		String hql = "select parentid from " + type + " where id = " + this.getID();
		Session session = this.extent.getSession();
		Query query = session.createQuery(hql);
		List value = query.list();
		return (String) value.get(0);
	}

	public MetaDataEntity getParent() {
		String parentid = this.cache.getParentID();
		if (parentid == null) {
			parentid = this.getParentID();
		}
		MetaDataEntityImpl entity = new MetaDataEntityImpl(this.extent);
		entity.setID(this.parentid);
		return entity;
	}

	public String getTypeName() {
		return null;
	}

	public String[] getChilds() {
		return null;
	}

	public int getChildsCount() {
		return 0;
	}

	public Iterator getChildsIterator() {
		return null;
	}

	public void setName(String name) {
		this.cache.setName(name);
		this.name = name;
	}

	public boolean isNameExist() {
		return false;
	}

	public void setParentID(String id) {
		this.cache.setParentID(id);
		this.parentid = id;
	}

	public void setParent(MetaDataEntity parent) {
		String parentid = parent.getID();
		this.setParentID(parentid);
	}

	public void setType(String type) {
		this.type = type;
		this.cache.setType(type);
	}

	public void setTypeName() {

	}

	public String[] getChildsWithType(String type) {
		return null;
	}

	public int getChildsCountWithType(String type) {
		return 0;
	}

	public Iterator getChildsWithTypeIterator(String type) {
		return null;
	}

	public void addChild(MetaDataEntity child) {

	}

	public void bindChild(String childid) {

	}

	public InputStream getContent() {
		return null;
	}

	public void setContent(InputStream is) {

	}

	public void writeContent(OutputStream os) {

	}

	public Map<String, Object> getProperties() {
		return null;
	}

	public Object getProperty(String key) {
		return null;
	}

	public String getStringProperty(String key, String dfvalue) {
		if (!map.containsKey(key)) {
			return dfvalue;
		}
		return (String) map.get(key);
	}

	public String getStringProperty(String key) {
		Session session = this.extent.getSession();
		try {
			MetaObject obj = (MetaObject) session.get(MetaObject.class, this.id);
			MetaDataSlot slot = obj.getProperties().get(key);
			return (String) slot.getValue();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			session.close();
		}
	}

	public void addProperty(String key, Object value) {
		map.put(key, value);
	}

	public void removeProperty(String key) {
		map.remove(key);
	}

	public boolean hasProperty(String key) {
		return map.containsKey(key);
	}

	public void setProperty(String key, Object value) {
		this.cache.setProperty(key, value);
	}

	public boolean isConsist() {
		return false;
	}

	public int getIntProperty(String key, int dfvalue) {
		if (!map.containsKey(key)) {
			return dfvalue;
		}
		return (Integer) map.get(key);
	}

	public int getIntProperty(String key) {
		return (Integer) map.get(key);
	}

	public void setIntProperty(String key, int value) {
		map.put(key, value);
	}

	public boolean getBoolProperty(String key, boolean dfvalue) {
		if (!map.containsKey(key)) {
			return dfvalue;
		}
		return (Boolean) map.get(key);
	}

	public boolean getBoolProperty(String key) {
		return (Boolean) map.get(key);
	}

	public void setBoolProperty(String key, boolean value) {
		map.put(key, value);
	}

	public void setStringProperty(String key, String value) {
		map.put(key, value);
	}

	public void getImportedEntities() {

	}

	public String getAliasNameById(String id) {
		return null;
	}

	public String getIdByAliasName(String aliasname) {
		return null;
	}

	public String hasAliasName(String aliasname) {
		return null;
	}

	public void getImportingEntities() {

	}

	public MetaDataEntity getModifyCache() {
		return this.cache;
	}

}
