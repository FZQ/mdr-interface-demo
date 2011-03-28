package com.succezbi.mdr.api.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import com.succezbi.mdr.api.MetaDataEntity;

public class MetaDataEntityImpl implements MetaDataEntity{

	private String id = null;
	private String type = null;
	
	public MetaDataEntityImpl(String type) {
		if(this.type == null){
			throw new RuntimeException("MetaDataEntity必须有类型");
		}
		this.type = type;
	}
	
	public String getID() {
		return this.id;
	}
	
	protected void setID(String id){
		
	}

	public String getName() {
		String hql = "select obj.name from ModelElement as obj where id=:id";
		
		
		return null;
	}

	public String getType() {
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	public int getChildsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Iterator getChildsIterator() {
		// TODO Auto-generated method stub
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

	public int getIntegerProperty(String key, int dfvalue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getIntegerProperty(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean getBooleanProperty(String key, boolean dfvalue) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getBooleanProperty(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getStringProperty(String key, String dfvalue) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStringProperty(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addProperty(String key, Object object) {
		// TODO Auto-generated method stub
		
	}

	public void removeProperty(String key) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasProperty(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setProperty(String String, Object obj) {
		// TODO Auto-generated method stub
		
	}

	public boolean isConsist() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getIntProperty(String key, int dfvalue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getIntProperty(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setIntProperty(String key, int value) {
		// TODO Auto-generated method stub
		
	}

	public boolean getBoolProperty(String key, boolean dfvalue) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getBoolProperty(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setBoolProperty(String key, boolean value) {
		// TODO Auto-generated method stub
		
	}

	public void setStringProperty(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	public void getImportedEntities() {
		// TODO Auto-generated method stub
		
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
