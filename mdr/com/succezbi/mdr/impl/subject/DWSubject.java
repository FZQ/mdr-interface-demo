package com.succezbi.mdr.impl.subject;

import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.ModelElement;

@Table(name="MDR_DWSUBJECT")
public class DWSubject extends ModelElement{

	@Override
	protected String getEntityName() {
		return "DWSubject";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

}
