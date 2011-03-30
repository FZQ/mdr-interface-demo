package com.succezbi.mdr.impl.core;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.metamodel.MetaExtent;

@Entity(name = "MetaObject")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MetaObject {
	
	@Transient
	private MetaExtent extent = null;
	
	public MetaObject(MetaExtent extent) {
		this.extent = extent;
	}
	
	public MetaExtent getExtent(){
		return this.extent;
	}

	@Id
	@Column(name = "ID", length = 32)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id = null;
	
	@OneToMany(mappedBy="object")
	@MapKey(name="key")
	private Map<String, DataSlot> properties = new HashMap<String, DataSlot>();
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	protected abstract String getEntityName();

	/**
	 * 获取hibernate的QBE入口对象DetachedCriteria
	 * @return
	 */
	protected abstract DetachedCriteria createDetachedCriteria();

	public void setProperties(Map<String, DataSlot> properties) {
		this.properties = properties;
	}

	public Map<String, DataSlot> getProperties() {
		return properties;
	}

}
