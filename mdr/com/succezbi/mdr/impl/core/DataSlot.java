package com.succezbi.mdr.impl.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DataSlot {

	@Id
	@Column(name = "key")
	private String key = null;
	
	@ManyToOne
	@JoinColumn(name="objectid")
	private MetaObject object = null;

	@Column(name = "value")
	private Object value = null;

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setObject(MetaObject object) {
		this.object = object;
	}

	public MetaObject getObject() {
		return object;
	}
}
