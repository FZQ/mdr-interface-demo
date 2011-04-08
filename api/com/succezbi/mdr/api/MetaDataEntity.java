package com.succezbi.mdr.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * 元数据模块提供给其他模块的元数据接口，这个接口相对于元数据模块内部的元数据接口来说
 * 是一个简化，主要目的是为了让元数据模块的使用者能够以简单统一的口径进行元数据的存取
 * @author classfoo
 *
 */
public interface MetaDataEntity {

	/**
	 * 所有的MetaDataEntity的ID都不能冲突
	 * @return
	 */
	public String getID();
	
	/**
	 * 元数据是否与后台数据库记录绑定，也就是是否已经物化
	 * @return
	 */
	public boolean isConsist();

	/**
	 * 在同一个父元数据下面所有的子元数据Name不能冲突，因为ID是一个UUID字符串，难以
	 * 被用户直接使用，因此name将在很多情况下直接提供给用户操作，作为相应的元数据的
	 * 标识，Name通过完整路径来互相区分
	 * @return
	 */
	public String getName();
	public void setName(String name);
	public boolean isNameExist();

	/**
	 * 获取父元数据的ID，如果返回Null，说明这个元数据是一个根元数据
	 * @return
	 */
	public String getParentID();
	public void setParentID(String id);
	public void setParent(MetaDataEntity parent);
	public MetaDataEntity getParent();

	/**
	 * 获取元数据类型，元数据类型是一个UUID，它在具体模型建模的时候就确定，并保证不会
	 * 在后续开发中对之进行修改
	 * @return
	 */
	public String getType();
	public void setType(String type);
	public String getTypeName();
	public void setTypeName();

	/**
	 * 获取所有的子元数据ID列表
	 * @return
	 */
	public String[] getChilds();
	public int getChildsCount();
	public Iterator getChildsIterator();

	/**
	 * 获取指定类型的所有直接子元数据ID列表
	 * @param type
	 * @return
	 */
	public String[] getChildsWithType(String type);
	public int getChildsCountWithType(String type);
	public Iterator getChildsWithTypeIterator(String type);

	/**
	 * 为元数据对象添加子元数据
	 * @param child
	 */
	public void addChild(MetaDataEntity child);
	public void bindChild(String childid);

	/**
	 * 获取元数据中存储的大字段内容
	 * @return
	 */
	public InputStream getContent();
	public void setContent(InputStream is);
	public void writeContent(OutputStream os);

	/**
	 * 获取元数据中存储的键值对，键值对内容通常都是简单类型，复杂类型需要考虑将之实现
	 * 为一个单独的元数据类型
	 * @return
	 */
	public Map<String,Object> getProperties();
	public Object getProperty(String key);
	public int getIntProperty(String key, int dfvalue);
	public int getIntProperty(String key);
	public void setIntProperty(String key, int value);
	public boolean getBoolProperty(String key, boolean dfvalue);
	public boolean getBoolProperty(String key);
	public void setBoolProperty(String key, boolean value);
	public String getStringProperty(String key, String dfvalue);
	public String getStringProperty(String key);
	public void setStringProperty(String key, String value);
	public void removeProperty(String key);
	public boolean hasProperty(String key);
	public void setProperty(String key, Object value);
	
	
	/**
	 * 获取本MetaEntity对其他MetaEntity的引用关系记录
	 */
	public void getImportedEntities();
	
	public String getAliasNameById(String id);
	public String getIdByAliasName(String aliasname);
	public String hasAliasName(String aliasname);
	
	/**
	 * 获取本MetaDataEntity被其他MetaDataEntity引用的记录
	 */
	public void getImportingEntities();

	/**
	 * 获取MetaDataEntity中的写入缓存，在将MetaDataEntity执行save到数据库前，对
	 * MetaDataEntity的所有修改都将会写入到一个缓存的MetaDataEntity实现中
	 * @return
	 */
	public MetaDataEntity getModifyCache();
}
