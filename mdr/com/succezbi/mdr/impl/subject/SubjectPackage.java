package com.succezbi.mdr.impl.subject;

import com.succezbi.mdr.impl.metamodel.MetaClass;
import com.succezbi.mdr.impl.metamodel.MetaExtent;
import com.succezbi.mdr.impl.metamodel.MetaFactory;
import com.succezbi.mdr.impl.metamodel.MetaPackage;

public class SubjectPackage extends MetaPackage {
	
	protected SubjectPackage(MetaExtent extent) {
		super(extent);
	}

	private static final String SUBJECT = "Server.Project.Subject";

	public MetaClass getMetaClass(String type) {
		
		return null;
	}

	@Override
	public MetaFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}
}
