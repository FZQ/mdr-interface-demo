package com.succezbi.mdr.impl.metamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="MetaPackage")
@Table(name = "MDR_METAPACKAGE")
public class MetaPackage {

	@Id
	@Column(name="NAME")
	private String name = null;

	@OneToMany(mappedBy = "pkg")
	private List<MetaClass> classes = null;

	@Transient
	private MetaFactory factory = null;
	
	@Transient
	private MetaExtent extent = null;
	
	public MetaClass getMetaClass(String type) {
		if ("metamodel.MetaClass".equals(type)) {
			return new MetaClass();
		}
		return null;
	}

	public MetaFactory getFactory() {
		if (this.factory == null) {
			this.factory = new MetaFactory(this.getExtent(), this);
		}
		return this.factory;
	}

	public void setClasses(List<MetaClass> classes) {
		this.classes = classes;
	}

	public List<MetaClass> getClasses() {
		return classes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setExtent(MetaExtent extent) {
		this.extent = extent;
	}

	public MetaExtent getExtent() {
		return extent;
	}
}
