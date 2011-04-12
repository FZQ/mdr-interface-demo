package com.succezbi.mdr.impl.metamodel;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


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
	
	@ManyToOne
	@JoinColumn(name="SUPERCLASS")
	private MetaClass superclass = null;
	
	@OneToMany(mappedBy="metaclass")
	private List<MetaAttribute> attributes = null;

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

	public void setAttributes(List<MetaAttribute> attributes) {
		this.attributes = attributes;
	}

	public List<MetaAttribute> getAttributes() {
		return attributes;
	}

	public void setSuperclass(MetaClass superclass) {
		this.superclass = superclass;
	}

	public MetaClass getSuperclass() {
		return superclass;
	}
	
	private static final class AttributeIterator<E extends MetaAttribute> implements Iterator<E>{
		private Iterator<MetaAttribute> it = null;
		private MetaClass superclass = null;
		
		public AttributeIterator(MetaClass metaclass) {
			it = metaclass.getAttributes().iterator();
			superclass = metaclass.getSuperclass();
		}
		
		public boolean hasNext() {
			if(it.hasNext()){
				return true;
			}
			if(superclass == null){
				return false;
			}
			it = superclass.getAttributeIterator();
			this.superclass = superclass.getSuperclass();
			return it.hasNext();
		}

		public E next() {
			return (E) it.next();
		}

		public void remove() {
			it.remove();
		}

	}
}
