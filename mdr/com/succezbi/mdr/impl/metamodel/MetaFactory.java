package com.succezbi.mdr.impl.metamodel;


public abstract class MetaFactory {
	
	private MetaExtent extent;

	public MetaFactory(MetaExtent extent) {
		this.extent = extent;
	}
	
	public MetaExtent getExtent(){
		return this.extent;
	}

	public abstract MetaObject create(MetaClass cls, String name, String parentid);

}
