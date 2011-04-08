package com.succezbi.mdr.impl.metamodel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "MDR_METAPACKAGE")
public abstract class MetaPackage extends MetaObject{

	@OneToMany(mappedBy = "pkg")
	private List<MetaClass> classes = null;

	protected MetaPackage(MetaExtent extent) {
		super(extent);
	}

	public MetaClass getMetaClass(String type){
		if("metamodel.MetaClass".equals(type)){
			return new MetaClass(this.getExtent());
		}
		return null;
	}

	public abstract MetaFactory getFactory();

	public void setClasses(List<MetaClass> classes) {
		this.classes = classes;
	}

	public List<MetaClass> getClasses() {
		return classes;
	}
}
