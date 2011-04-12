package com.succezbi.mdr.impl.core;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Feature")
@Table(name="CORE_MODELELEMENT")
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
