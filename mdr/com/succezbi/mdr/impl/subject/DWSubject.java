package com.succezbi.mdr.impl.subject;

import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.abs.ModelElement;
import com.succezbi.mdr.impl.abs.Namespace;

@Table(name="MDR_DWSUBJECT")
public class DWSubject extends ModelElement{

	protected DWSubject(Namespace parent, String name) {
		super(parent, name);
	}

	@Override
	protected String getEntityName() {
		return "DWSubject";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

}
