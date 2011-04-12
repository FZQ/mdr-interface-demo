package com.succezbi.mdr.impl.core;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Classifier")
@Table(name="CORE_CLASSIFIER")
public abstract class Classifier extends Namespace {
	
	@OneToMany(mappedBy="classifier")
	private Set<Feature> features = null;
	
	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
	
	public Set<Feature> getFeatures() {
		return features;
	}
}
