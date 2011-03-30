package com.succezbi.mdr.impl.svr;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.Namespace;
import com.succezbi.mdr.impl.metamodel.MetaExtent;

public class SBIServer extends Namespace{

	protected SBIServer(MetaExtent extent) {
		super(extent, null, null);
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
