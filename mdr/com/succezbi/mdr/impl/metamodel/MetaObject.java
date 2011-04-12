package com.succezbi.mdr.impl.metamodel;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.DetachedCriteria;

@Entity(name = "MetaObject")
@Table(name = "MDR_METAOBJECT")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MetaObject {

	@Transient
	private MetaExtent extent = null;

	@Id
	@Column(name = "ID", length = 32)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id = null;

	@OneToMany(mappedBy = "metaobject")
	@MapKey(name = "dskey")
	private Map<String, MetaDataSlot> properties = new HashMap<String, MetaDataSlot>();

	public MetaExtent getExtent() {
		return this.extent;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Transient
	protected abstract String getEntityName();

	/**
	 * 获取hibernate的QBE入口对象DetachedCriteria
	 * @return
	 */
	@Transient
	protected abstract DetachedCriteria createDetachedCriteria();

	public void setExtent(MetaExtent extent) {
		this.extent = extent;
	}

	public void setProperties(Map<String, MetaDataSlot> properties) {
		this.properties = properties;
	}

	public Map<String, MetaDataSlot> getProperties() {
		return properties;
	}

}
