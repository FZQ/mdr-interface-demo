package com.succezbi.mdr.impl.svr;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.Namespace;

@Entity(name="SBIServer")
@Table(name="MDR_SBISERVER")
public class SBIServer extends Namespace{

	@Override
	protected String getEntityName() {
		return "SBIServer";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

}
