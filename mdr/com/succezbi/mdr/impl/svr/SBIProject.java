package com.succezbi.mdr.impl.svr;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.ModelElement;
import com.succezbi.mdr.impl.metamodel.MetaExtent;

public class SBIProject extends ModelElement{

	protected SBIProject(MetaExtent extent, SBIServer parent, String name) {
		super(extent, parent, name);
	}

	@Override
	protected String getEntityName() {
		return "Server.Project";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

}
