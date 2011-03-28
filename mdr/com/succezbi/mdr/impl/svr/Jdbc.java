package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.abs.Classifier;

@Entity
public class Jdbc extends Classifier{

	protected Jdbc(SBIServer parent, String name) {
		super(parent, name);
	}

	@Override
	protected String getEntityName() {
		return null;
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}
	
}
