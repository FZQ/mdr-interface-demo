package com.succezbi.mdr.impl.svr;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.metamodel.MetaObject;

@Entity
public class CustomExpression extends MetaObject {

	@Column(name = "NAME")
	private String name = null;

	@Column(name = "EXPRESSION")
	private String expression = null;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

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
