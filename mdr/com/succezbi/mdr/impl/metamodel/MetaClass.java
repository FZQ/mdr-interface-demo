package com.succezbi.mdr.impl.metamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MDR_METACLASS")
public class MetaClass {

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

}
