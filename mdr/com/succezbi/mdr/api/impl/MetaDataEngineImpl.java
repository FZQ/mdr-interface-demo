package com.succezbi.mdr.api.impl;

import com.succezbi.mdr.api.MetaDataEngine;
import com.succezbi.mdr.api.MetaDataEntity;

public class MetaDataEngineImpl implements MetaDataEngine{

	public MetaDataEngineImpl() {
		
	}

	public void register(String xml) {
		
	}

	public MetaDataEntity createNewEntity(String type, String name) {
		MetaDataEntityImpl entity = new MetaDataEntityImpl(type);
		entity.setName(name);
		return entity;
	}

	public MetaDataEntity get(String id) {
		
		return null;
	}

	public MetaDataEntity get(MetaDataEntity parent, String type, String name) {
		return null;
	}
	
	public void modifyEntity(String id, MetaDataEntityHandler handler){
		//获取Entity
		MetaDataEntity entity = null;
		//影响分析
		handler.handle(entity);
		this.createNewEntity("", "", "");
		
	}

	public MetaDataEntity createNewEntity(String parentid, String type, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public MetaDataEntity get(String parentid, String type, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public MetaDataEntity[] getEntityByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getEntityCountByType(String type) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String save(MetaDataEntity server) {
		// TODO Auto-generated method stub
		return null;
	}
}
