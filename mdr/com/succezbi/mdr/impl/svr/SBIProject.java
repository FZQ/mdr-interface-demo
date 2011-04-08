package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.ModelElement;

@Entity(name="SBIProject")
public class SBIProject extends ModelElement{

	@Override
	protected String getEntityName() {
		return "Server.Project";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

}
