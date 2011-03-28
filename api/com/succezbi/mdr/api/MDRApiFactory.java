package com.succezbi.mdr.api;

import com.succezbi.mdr.api.impl.MetaDataEngineImpl;

public class MDRApiFactory {
	private static MetaDataEngine engine = null;
	public static MetaDataEngine getMetaDataEngine(){
		if(engine == null){
			engine = new MetaDataEngineImpl();
		}
		return engine;
	}
}
