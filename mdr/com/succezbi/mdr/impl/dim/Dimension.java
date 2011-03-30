package com.succezbi.mdr.impl.dim;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.Feature;
import com.succezbi.mdr.impl.core.ModelElement;
import com.succezbi.mdr.impl.metamodel.MetaExtent;
import com.succezbi.mdr.impl.svr.SBIProject;

@Entity(name="Dimension")
public class Dimension extends Feature{
	
	public Dimension(MetaExtent extent, ModelElement parent, String name) {
		super(extent, parent, name);
	}

	@ManyToOne
	@JoinColumn(name="ProjectID")
	private SBIProject project = null;

	@Override
	protected String getEntityName() {
		return "Dimension";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

	public void setProject(SBIProject project) {
		this.project = project;
	}

	public SBIProject getProject() {
		return project;
	}
}
