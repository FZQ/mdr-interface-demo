package com.succezbi.demo.test;

import java.io.Serializable;
import java.util.Map;

import com.succezbi.mdr.api.MetaDataEntity;
import com.succezbi.mdr.api.impl.MetaDataEntityImpl;

public abstract class SBIObject implements Serializable{

	private static final long serialVersionUID = 3257849892271523203L;
	
	protected MetaDataEntity meta = null;
	
	public SBIObject(Class<?> cls) {
		this.meta = new MetaDataEntityImpl();
	}
	
	protected MetaDataEntity createEmptyMetaDataEntity(){
		return new MetaDataEntityImpl();
	}
	
	public String getResourceId(){
		String parent = this.meta.getParentID();
		String uid = this.meta.getID();
		String type = this.meta.getType();
		String name = this.meta.getName();
		return new SBIResourceId(parent, uid, type, name, resourceObj);
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
		return this.meta.getIntegerProperty(key);
	}

	public boolean getBooleanProperty(String key){
		return this.meta.getBooleanProperty(key);
	}

	public String getStringProperty(String key){
		return this.meta.getStringProperty(key);
	}

	public void addProperty(String key, Object object){
		this.meta.addProperty(key, object);
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
