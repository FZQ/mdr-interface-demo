package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.Namespace;

@Entity(name="SBIProjectFold")
@Table(name="SVR_SBIProjectFold")
public class SBIProjectFold extends Namespace{

	@Override
	protected String getEntityName() {
		return "Server.Project.ProjectFold";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

}
