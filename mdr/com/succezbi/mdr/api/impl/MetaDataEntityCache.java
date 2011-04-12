package com.succezbi.mdr.api.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.succezbi.mdr.api.MetaDataEntity;

public class MetaDataEntityCache implements MetaDataEntity {

	private String id = null;

	private boolean consist = false;

	private String name = null;

	private Map<String, Object> properties = new HashMap<String, Object>();

	private String parentid = null;

	private String type;

	public String getID() {
		return this.id;
	}
	
	public void setID(String id) {
		this.id = id;
	}

	public boolean isConsist() {
		return this.consist;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNameExist() {
		return this.name != null;
	}

	public String getParentID() {
		return this.parentid;
	}

	public void setParentID(String id) {
		this.parentid = id;
	}

	public void setParent(MetaDataEntity parent) {
		this.parentid = parent.getID();
	}

	public MetaDataEntity getParent() {
		if(this.parentid == null){
			return null;
		}
		return null;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return null;
	}

	public void setTypeName() {

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
		return this.properties;
	}

	public Object getProperty(String key) {
		return this.properties.get(key);
	}

	public String getStringProperty(String key, String dfvalue) {
		return this.properties.containsKey(key) ? this.getStringProperty(key) : dfvalue;
	}

	public String getStringProperty(String key) {
		return (String) this.properties.get(key);
	}

	public void setStringProperty(String key, String value) {
		this.properties.put(key, value);
	}

	public void removeProperty(String key) {
		this.properties.remove(key);
	}

	public boolean hasProperty(String key) {
		return this.properties.containsKey(key);
	}

	public void setProperty(String key, Object value) {
		this.properties.put(key, value);
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
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIntProperty(String key, int dfvalue) {
		return this.properties.containsKey(key)?this.getIntProperty(key):dfvalue;
	}

	public int getIntProperty(String key) {
		return (Integer) this.properties.get(key);
	}

	public void setIntProperty(String key, int value) {
		this.properties.put(key, value);
	}

	public boolean getBoolProperty(String key, boolean dfvalue) {
		return this.properties.containsKey(key)?this.getBoolProperty(key):dfvalue;
	}

	public boolean getBoolProperty(String key) {
		return (Boolean) this.properties.get(key);
	}

	public void setBoolProperty(String key, boolean value) {
		this.properties.put(key, value);
	}

}
