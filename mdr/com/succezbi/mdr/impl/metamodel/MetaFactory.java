package com.succezbi.mdr.impl.metamodel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

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

	public MetaObject create(MetaClass cls, Map properties) {
		if (!this.pkg.equals(cls.getPkg())) {
			throw new RuntimeException("类不属于这个包");
		}
		String classpath = cls.getClasspath();
		try {
			Class<MetaObject> javaclass = (Class<MetaObject>) Class.forName(classpath);
			Constructor<MetaObject> construct = javaclass.getConstructor();
			MetaObject instance = construct.newInstance();
			Iterator<MetaAttribute> it = cls.getAttributeIterator();
			while(it.hasNext()){
				MetaAttribute attribute = it.next();
				String key = attribute.getName();
				if(properties.containsKey(key)){
					Object value = properties.remove(key);
					MetaDataType datatype = attribute.getDatatype();
					Class<?> dtclass = String.class;//datatype.getJavaClass();
					Method method = javaclass.getMethod("set" + attribute.getNameForMethod(), dtclass);
					method.invoke(instance, value);
				}
			}
			if(!properties.isEmpty()){
				instance.setProperties(properties);
			}
			return instance;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
