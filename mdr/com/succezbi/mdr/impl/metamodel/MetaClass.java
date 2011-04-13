package com.succezbi.mdr.impl.metamodel;

import java.util.Iterator;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.criterion.DetachedCriteria;

@Entity(name = "MetaClass")
@Table(name = "MDR_METACLASS")
public class MetaClass extends MetaObject{

	@Column(name = "CLASSPATH", length = 128)
	private String classpath = null;

	@Column(name = "NAME")
	private String name = null;

	@ManyToOne
	@JoinColumn(name = "PACKAGE")
	private MetaPackage pkg = null;

	@ManyToOne
	@JoinColumn(name = "SUPERCLASS", columnDefinition = "varchar(32)")
	private MetaClass superclass = null;

	@OneToMany(mappedBy = "metaclass")
	@MapKey(name="name")
	private Map<String, MetaAttribute> attributes = null;

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

	@Transient
	public Iterator<MetaAttribute> getAttributeIterator() {
		return new AttributeIterator<MetaAttribute>(this);
	}
	
	public void setSuperclass(MetaClass superclass) {
		this.superclass = superclass;
	}

	public MetaClass getSuperclass() {
		return superclass;
	}

	private static final class AttributeIterator<E extends MetaAttribute> implements Iterator<MetaAttribute> {
		
		private Map<String, MetaAttribute> attributes = null;
		private Iterator<String> it = null;
		private MetaClass superclass = null;

		public AttributeIterator(MetaClass metaclass) {
			attributes = metaclass.getAttributes();
			it = attributes.keySet().iterator();
			superclass = metaclass.getSuperclass();
		}

		public boolean hasNext() {
			if (it.hasNext()) {
				return true;
			}
			if (superclass == null) {
				return false;
			}
			attributes = this.superclass.getAttributes();
			it = attributes.keySet().iterator();
			this.superclass = superclass.getSuperclass();
			return it.hasNext();
		}

		public MetaAttribute next() {
			return this.attributes.get(it.next());
		}

		public void remove() {
			this.attributes.remove(it.next());
		}

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

	public void setAttributes(Map<String, MetaAttribute> attributes) {
		this.attributes = attributes;
	}

	public Map<String, MetaAttribute> getAttributes() {
		return attributes;
	}
}
