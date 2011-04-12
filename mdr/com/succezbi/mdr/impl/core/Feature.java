package com.succezbi.mdr.impl.core;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.succezbi.mdr.impl.metamodel.MetaObject;

@Entity(name="Feature")
@Table(name="CORE_FEATURE")
public abstract class Feature extends MetaObject {

	@ManyToOne
	@JoinColumn(name = "ClassifierID")
	private Classifier classifier = null;


	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}

	public Classifier getClassifier() {
		return classifier;
	}

}
