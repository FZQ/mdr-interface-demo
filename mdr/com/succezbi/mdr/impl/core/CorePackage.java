package com.succezbi.mdr.impl.core;

import com.succezbi.mdr.impl.metamodel.MetaClass;
import com.succezbi.mdr.impl.metamodel.MetaExtent;
import com.succezbi.mdr.impl.metamodel.MetaFactory;
import com.succezbi.mdr.impl.metamodel.MetaPackage;

public class CorePackage extends MetaPackage {

	protected CorePackage(MetaExtent extent) {
		super(extent);
	}

	private String CORE_CLASSIFIER = "core.classifier";

	private String CORE_FEATURE = "core.feature";

	public MetaClass createMetaClass(String type) {
		
		return null;
	}

	@Override
	public MetaClass getMetaClass(String type) {
		return null;
	}

	@Override
	public MetaFactory getFactory() {
		return null;
	}
}
