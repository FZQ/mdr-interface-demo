package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.ModelElement;
import com.succezbi.mdr.impl.metamodel.MetaExtent;

@Entity(name="SBIProject")
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
