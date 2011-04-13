package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.ModelElement;

@Entity(name="SVR_SBIProject")
public class SBIProject extends ModelElement{
	
	@ManyToOne
	@JoinColumn(name="PARENT")
	private SBIServer parent = null;

	@Override
	protected String getEntityName() {
		return "Server.Project";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

	public void setParent(SBIServer parent) {
		this.parent = parent;
	}

	public SBIServer getParent() {
		return parent;
	}

}
