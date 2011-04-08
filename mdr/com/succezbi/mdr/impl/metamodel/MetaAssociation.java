package com.succezbi.mdr.impl.metamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;


@Entity(name="MetaAssociation")
@Table(name="MDR_METAASSOCIATION")
public class MetaAssociation extends MetaObject{

	@Override
	protected String getEntityName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

}
