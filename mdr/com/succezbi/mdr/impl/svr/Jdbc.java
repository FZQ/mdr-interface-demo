package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.Classifier;
import com.succezbi.mdr.impl.metamodel.MetaExtent;

@Entity
public class Jdbc extends Classifier{

	protected Jdbc(MetaExtent extent, SBIServer parent, String name) {
		super(extent, parent, name);
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
