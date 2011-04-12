package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.Classifier;

@Entity
@Table(name="SVR_JDBC")
public class Jdbc extends Classifier{

	@Override
	protected String getEntityName() {
		return null;
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}
	
}
