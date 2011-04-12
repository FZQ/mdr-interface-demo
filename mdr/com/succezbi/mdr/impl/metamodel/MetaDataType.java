package com.succezbi.mdr.impl.metamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="MetaDataType")
@Table(name="MDR_METADATATYPE")
public class MetaDataType{
	
	@Id
	@Column(name="classpath")
	private String classpath = null;

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	public String getClasspath() {
		return classpath;
	}

	@Transient
	public Class<?> getJavaClass() throws ClassNotFoundException {
		if(this.classpath == null){
			return null;
		}
		return Class.forName(this.classpath);
	}

}
