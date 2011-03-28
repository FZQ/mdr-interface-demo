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
		this.createNewEntity(type, name);
		this.parseImpact(entity);
	}
}
