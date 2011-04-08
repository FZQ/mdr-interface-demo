package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.Namespace;
import com.succezbi.mdr.impl.metamodel.MetaExtent;

@Entity(name="SBIProjectFold")
@Table(name="SBIProjectFold")
public class SBIProjectFold extends Namespace{

	protected SBIProjectFold(MetaExtent extent, SBIProject parent, String name) {
		super(extent, null, name);
	}

	@Override
	protected String getEntityName() {
		return "Server.Project.ProjectFold";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

}
