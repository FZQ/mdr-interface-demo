package com.succezbi.mdr.impl.metamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;


@Entity(name="MetaAttribute")
@Table(name="MDR_METAATTRIBUTE")
public class MetaAttribute extends MetaObject{

	@Override
	protected String getEntityName() {
		return null;
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

}
