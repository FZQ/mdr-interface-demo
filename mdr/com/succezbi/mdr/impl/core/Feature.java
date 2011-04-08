package com.succezbi.mdr.impl.core;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="Feature")
public abstract class Feature extends ModelElement {

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
