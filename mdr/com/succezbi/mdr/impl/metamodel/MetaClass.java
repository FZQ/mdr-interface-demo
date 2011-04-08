package com.succezbi.mdr.impl.metamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.MetaObject;

@Entity(name="MetaClass")
@Table(name = "MDR_METACLASS")
public class MetaClass extends MetaObject{

	public MetaClass(MetaExtent extent) {
		super(extent);
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "NAME")
	private String name = null;

	@ManyToOne
	@JoinColumn(name = "PACKAGE")
	private MetaPackage pkg = null;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPkg(MetaPackage pkg) {
		this.pkg = pkg;
	}

	public MetaPackage getPkg() {
		return pkg;
	}

	public void setSuperClass(String classname) {
		
	}

	public void setJavaClass(String path) {
		
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
