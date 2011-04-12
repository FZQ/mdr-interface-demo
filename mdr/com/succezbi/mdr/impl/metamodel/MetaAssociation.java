package com.succezbi.mdr.impl.metamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "MetaAssociation")
@Table(name = "MDR_METAASSOCIATION")
@IdClass(MetaAssociation.PrimaryKey.class)
public class MetaAssociation {

	@Id
	private MetaClass metaclass = null;

	@Id
	private MetaClass type = null;

	@Column(name = "MULTIPLICITY")
	private int multiplicity;

	public void setMetaclass(MetaClass metaclass) {
		this.metaclass = metaclass;
	}

	public MetaClass getMetaclass() {
		return metaclass;
	}

	public void setMultiplicity(int multiplicity) {
		this.multiplicity = multiplicity;
	}

	public int getMultiplicity() {
		return multiplicity;
	}

	public void setType(MetaClass type) {
		this.type = type;
	}

	public MetaClass getType() {
		return type;
	}
	

	@Embeddable
	public static class PrimaryKey implements Serializable {

		private static final long serialVersionUID = 6240535869652883673L;

		@ManyToOne
		@JoinColumn(name = "METACLASS", columnDefinition="varchar(128)")
		private MetaClass metaclass = null;

		@ManyToOne
		@JoinColumn(name = "TYPE", columnDefinition="varchar(128)")
		private MetaClass type = null;

		public void setMetaclass(MetaClass metaclass) {
			this.metaclass = metaclass;
		}

		public MetaClass getMetaclass() {
			return metaclass;
		}

		public void setType(MetaClass type) {
			this.type = type;
		}

		public MetaClass getType() {
			return type;
		}

	}

}
