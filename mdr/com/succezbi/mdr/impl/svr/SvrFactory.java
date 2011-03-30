package com.succezbi.mdr.impl.svr;

import com.succezbi.mdr.impl.core.MetaObject;
import com.succezbi.mdr.impl.metamodel.MetaClass;
import com.succezbi.mdr.impl.metamodel.MetaExtent;
import com.succezbi.mdr.impl.metamodel.MetaFactory;

public class SvrFactory extends MetaFactory {

	public SvrFactory(MetaExtent extent) {
		super(extent);
	}

	private static final String SVR_SBISERVER = "Server";

	@Override
	public MetaObject create(MetaClass cls, String name, String parentid) {
		String classname = cls.getName();
		if (SVR_SBISERVER.equals(classname)) {
			SBIServer server = new SBIServer(this.getExtent());
			return server;
		}
		return null;
	}

}
