package com.succezbi.mdr.api;

import java.io.InputStream;

public interface MetaDataEngine {
	
	public void register(String xml) throws Exception;
	public void register(InputStream is) throws Exception;
	
	/**
	 * 创建一个新的MetaDataEntity
	 * @param parentid
	 * @param type
	 * @param name
	 * @return
	 */
	public MetaDataEntity createNewEntity(String parentid, String type, String name);
	
	public MetaDataEntity get(String id);
	
	public MetaDataEntity get(String parentid, String type, String name);

	public MetaDataEntity[] getEntityByType(String type);
	
	public int getEntityCountByType(String type);

	public String save(MetaDataEntity server);
}