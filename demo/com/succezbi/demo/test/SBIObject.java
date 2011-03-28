package com.succezbi.demo.test;

import java.io.Serializable;
import java.util.Map;

import com.succezbi.mdr.api.MetaDataEntity;

public abstract class SBIObject implements Serializable{

	private static final long serialVersionUID = 3257849892271523203L;
	
	protected MetaDataEntity meta = null;
	
	public SBIObject(Class<?> cls) {
		//this.meta = new MetaDataEntityImpl();
	}
	
	protected MetaDataEntity createEmptyMetaDataEntity(){
		//return new MetaDataEntityImpl();
		return null;
	}
	
	public String getResourceId(){
		String parent = this.meta.getParentID();
		String uid = this.meta.getID();
		String type = this.meta.getType();
		String name = this.meta.getName();
		//return new SBIResourceId(parent, uid, type, name, resourceObj);
		return null;
	}
	
	public String getId(){
		return this.meta.getID();
	}
	

	public Map<String, Object> getProperties(){
		return this.meta.getProperties();
	}

	public Object getProperty(String key){
		return this.meta.getProperty(key);
	}

	public int getIntegerProperty(String key){
		return this.meta.getIntProperty(key);
	}

	public boolean getBooleanProperty(String key){
		return this.meta.getBoolProperty(key);
	}

	public String getStringProperty(String key){
		return this.meta.getStringProperty(key);
	}

	public void addProperty(String key, Object object){
		this.meta.setProperty(key, object);
	}

	public void removeProperty(String key){
		this.meta.removeProperty(key);
	}

	public boolean hasProperty(String key){
		return this.hasProperty(key);
	}

	public void setProperty(String key, Object value){
		this.setProperty(key, value);
	}
}
