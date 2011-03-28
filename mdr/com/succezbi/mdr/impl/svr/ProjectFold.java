package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.abs.Namespace;

@Entity(name="ProjectFold")
@Table(name="ProjectFold")
public class ProjectFold extends Namespace{

	protected ProjectFold(Project parent, String name) {
		super(parent, name);
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
