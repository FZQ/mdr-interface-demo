package com.succezbi.mdr.impl.abs;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public abstract class Feature extends ModelElement {

	@ManyToOne
	@JoinColumn(name = "ClassifierID")
	private Classifier classifier = null;

	public Feature(ModelElement parent, String name) {
		super(parent, name);
	}

	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}

	public Classifier getClassifier() {
		return classifier;
	}

}
