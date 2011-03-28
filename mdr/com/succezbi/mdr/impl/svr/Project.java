package com.succezbi.mdr.impl.svr;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.abs.ModelElement;

public class Project extends ModelElement{

	protected Project(SBIServer parent, String name) {
		super(parent, name);
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
