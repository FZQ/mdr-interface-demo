package com.succezbi.mdr.impl.dim;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.abs.Feature;
import com.succezbi.mdr.impl.abs.ModelElement;
import com.succezbi.mdr.impl.svr.Project;

@Entity(name="Dimension")
public class Dimension extends Feature{
	
	public Dimension(ModelElement parent, String name) {
		super(parent, name);
	}

	@ManyToOne
	@JoinColumn(name="ProjectID")
	private Project project = null;

	@Override
	protected String getEntityName() {
		return "Dimension";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
	}
}
