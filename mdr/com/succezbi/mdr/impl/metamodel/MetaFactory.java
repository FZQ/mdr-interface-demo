package com.succezbi.mdr.impl.metamodel;

import java.lang.reflect.Constructor;

public class MetaFactory {

	private MetaPackage pkg = null;

	private MetaExtent extent = null;

	public MetaFactory(MetaExtent extent, MetaPackage pkg) {
		this.pkg = pkg;
		this.extent = extent;
	}

	public MetaExtent getExtent() {
		return this.extent;
	}

	public MetaPackage getPackage() {
		return this.pkg;
	}

	public MetaObject create(MetaClass cls, String name, String parentid) {
		if (!this.pkg.equals(cls.getPkg())) {
			throw new RuntimeException("类不属于这个包");
		}
		String classpath = cls.getClasspath();
		try {
			Class<MetaObject> javaclass = (Class<MetaObject>) Class.forName(classpath);
			Constructor<MetaObject> construct = javaclass.getConstructor();
			MetaObject instance = construct.newInstance();
			return instance;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
