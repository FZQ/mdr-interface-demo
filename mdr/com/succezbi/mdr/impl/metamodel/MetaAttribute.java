package com.succezbi.mdr.impl.metamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "MetaAttribute")
@Table(name = "MDR_METAATTRIBUTE")
@IdClass(MetaAttribute.MetaAttributePK.class)
public class MetaAttribute implements Serializable {

	private static final long serialVersionUID = 8931102204373393690L;

	@Id
	private String name = null;

	@Id
	private MetaClass metaclass = null;

	@ManyToOne
	@JoinColumn(name = "DATATYPE")
	private MetaDataType datatype = null;

	public void setDatatype(MetaDataType datatype) {
		this.datatype = datatype;
	}

	public MetaDataType getDatatype() {
		return datatype;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public String getNameForMethod(){
		return this.convertFirstCharToUppercase(this.name);
	}
	
	private String convertFirstCharToUppercase(String str){
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}


	public void setMetaclass(MetaClass metaclass) {
		this.metaclass = metaclass;
	}

	public MetaClass getMetaclass() {
		return metaclass;
	}


	public static class MetaAttributePK implements Serializable{

		private static final long serialVersionUID = -5942215460037342145L;

		@Column(name = "NAME", length = 128)
		private String name = null;

		@ManyToOne
		@JoinColumn(name = "METACLASS", columnDefinition="varchar(128)")
		private MetaClass metaclass = null;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

		public void setMetaclass(MetaClass metaclass) {
			this.metaclass = metaclass;
		}

		public MetaClass getMetaclass() {
			return metaclass;
		}

	}

}
