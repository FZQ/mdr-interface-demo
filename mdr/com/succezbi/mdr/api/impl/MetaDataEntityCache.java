package com.succezbi.mdr.api.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import com.succezbi.mdr.api.MetaDataEntity;

public class MetaDataEntityCache implements MetaDataEntity{
	private String id = null;
	private boolean consist = false;
	private String name = null;
	private Map<String, Object> properties = null;
	private String parentid = null;
	private String type;
	
	public String getID() {
		return this.id;
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
		return this.parentid ;
	}

	public void setParentID(String id) {
		this.parentid = id;
	}

	public void setParent(MetaDataEntity parent) {
		this.parentid = parent.getID();
	}

	public MetaDataEntity getParent() {
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
		// TODO Auto-generated method stub
		return null;
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
		
	}

	public String getStringProperty(String key, String dfvalue) {
		return null;
	}

	public String getStringProperty(String key) {
		return null;
	}

	public void setStringProperty(String key, String value) {
		
	}

	public void removeProperty(String key) {
		
	}

	public boolean hasProperty(String key) {
		return false;
	}

	public void setProperty(String key, Object value) {
		
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

}
