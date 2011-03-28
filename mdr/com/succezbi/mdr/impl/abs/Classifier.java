package com.succezbi.mdr.impl.abs;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "Classifier")
public abstract class Classifier extends Namespace {
	
	protected Classifier(Namespace parent, String name) {
		super(parent, name);
	}
	
	@OneToMany(mappedBy="classifier")
	private Set<Feature> features = null;

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public Set<Feature> getFeatures() {
		return features;
	}
}
