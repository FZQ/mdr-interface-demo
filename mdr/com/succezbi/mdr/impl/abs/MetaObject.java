package com.succezbi.mdr.impl.abs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.DetachedCriteria;

@Entity(name="MetaObject")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MetaObject{
	
	@Id
	@Column(name = "ID", length = 32)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id = null;

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
	
}
