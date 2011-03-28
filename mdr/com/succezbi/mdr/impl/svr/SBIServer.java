package com.succezbi.mdr.impl.svr;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.abs.Namespace;

public class SBIServer extends Namespace{

	protected SBIServer() {
		super(null, null);
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
