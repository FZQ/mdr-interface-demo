package com.succezbi.mdr.impl.subject;

import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.ModelElement;
import com.succezbi.mdr.impl.core.Namespace;
import com.succezbi.mdr.impl.metamodel.MetaExtent;

@Table(name="MDR_DWSUBJECT")
public class DWSubject extends ModelElement{

	protected DWSubject(MetaExtent extent, Namespace parent, String name) {
		super(extent, parent, name);
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
