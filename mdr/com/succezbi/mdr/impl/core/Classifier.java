package com.succezbi.mdr.impl.core;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.succezbi.mdr.impl.metamodel.MetaExtent;

@Entity(name = "Classifier")
public abstract class Classifier extends Namespace {
	
	protected Classifier(MetaExtent extent, Namespace parent, String name) {
		super(extent, parent, name);
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
