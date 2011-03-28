package com.succezbi.mdr.impl.abs;


public class NSPackageImpl implements NSPackage{

	@Override
	public NSFactory getFactory() {
		return new NSFactoryImpl();
	}

	@Override
	public NSExtent getExtent() {
		return null;
	}

}
