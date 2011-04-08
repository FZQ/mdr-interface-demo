package com.succezbi.mdr.impl.metamodel;

import javax.persistence.Entity;

import org.hibernate.criterion.DetachedCriteria;


@Entity(name="MetaAttribute")
public class MetaAttribute extends MetaObject{

	public MetaAttribute(MetaExtent extent) {
		super(extent);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

}
