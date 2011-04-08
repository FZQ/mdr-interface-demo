package com.succezbi.mdr.impl.metamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name="MetaClass")
@Table(name = "MDR_METACLASS")
public class MetaClass{
	
	@Id
	@Column(name="CLASSPATH")
	private String classpath = null;

	@Column(name = "NAME")
	private String name = null;

	@ManyToOne
	@JoinColumn(name = "PACKAGE")
	private MetaPackage pkg = null;
	
	@Column(name="superclasspath")
	private String superclass = null;

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

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	public String getClasspath() {
		return classpath;
	}

	public void setSuperclass(String superclass) {
		this.superclass = superclass;
	}

	public String getSuperclass() {
		return superclass;
	}
}
