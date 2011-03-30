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
import com.succezbi.mdr.impl.metamodel.MetaExtent;

public class MetaDataEntityImpl implements MetaDataEntity{
	
	private String id = null;
	private String type = null;
	private MetaExtent extent = null;
	
	private HashMap map = new HashMap();
	
	public MetaDataEntityImpl(MetaExtent extent, String type) {
		if(type == null){
			throw new RuntimeException("MetaDataEntity必须有类型");
		}
		this.type = type;
		this.extent = extent;
	}
	
	public String getID() {
		return this.id;
	}
	
	protected void setID(String id){
		
	}

	public String getName() {
		String hql = "select obj.name from ModelElement as obj where id=:id";
		Session session = this.extent.getSession();
		Query query = session.createQuery(hql);
		List list = query.list();
		int size = list.size();
		if(size == 1){
			return (String) list.get(0);
		}else{
			if(size <= 0){
				throw new RuntimeException();
			}else{
				throw new RuntimeException();
			}
		}
	}

	public String getType() {
		return this.type;
	}

	public String getParentID() {
		return null;
	}

	public MetaDataEntity getParent() {
		return null;
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
		
	}

	public boolean isNameExist() {
		return false;
	}

	public void setParentID(String id) {
		
	}

	public void setParent(MetaDataEntity parent) {
		
	}

	public void setType(String type) {
		// TODO Auto-generated method stub
		
	}

	public void setTypeName() {
		// TODO Auto-generated method stub
		
	}

	public String[] getChildsWithType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getChildsCountWithType(String type) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Iterator getChildsWithTypeIterator(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addChild(MetaDataEntity child) {
		// TODO Auto-generated method stub
		
	}

	public void bindChild(String childid) {
		// TODO Auto-generated method stub
		
	}

	public InputStream getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setContent(InputStream is) {
		// TODO Auto-generated method stub
		
	}

	public void writeContent(OutputStream os) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getProperty(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStringProperty(String key, String dfvalue) {
		if(!map.containsKey(key)){
			return dfvalue;
		}
		return (String) map.get(key);
	}

	public String getStringProperty(String key) {
		return (String) map.get(key);
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

	public void setProperty(String String, Object obj) {
		
	}

	public boolean isConsist() {
		return false;
	}

	public int getIntProperty(String key, int dfvalue) {
		if(!map.containsKey(key)){
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
		if(!map.containsKey(key)){
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
		// TODO Auto-generated method stub
		return null;
	}

	public String getIdByAliasName(String aliasname) {
		// TODO Auto-generated method stub
		return null;
	}

	public String hasAliasName(String aliasname) {
		// TODO Auto-generated method stub
		return null;
	}

	public void getImportingEntities() {
		// TODO Auto-generated method stub
		
	}

}
