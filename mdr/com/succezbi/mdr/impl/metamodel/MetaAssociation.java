package com.succezbi.mdr.impl.metamodel;

import javax.persistence.Entity;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.MetaObject;

@Entity(name="MetaAssociation")
public class MetaAssociation extends MetaObject{

	public MetaAssociation(MetaExtent extent) {
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
