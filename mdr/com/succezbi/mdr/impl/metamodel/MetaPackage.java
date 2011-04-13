package com.succezbi.mdr.impl.metamodel;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;

@Entity(name="MetaPackage")
@Table(name = "MDR_METAPACKAGE")
public class MetaPackage {

	@Id
	@Column(name="NAME")
	private String name = null;

	@OneToMany(mappedBy = "pkg")
	@MapKey(name = "name")
	private Map<String, MetaClass> classes = null;

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

	public MetaClass getMetaClassByName(String type) {
		Session session = this.extent.getSession();
		
		return null;
	}
}
