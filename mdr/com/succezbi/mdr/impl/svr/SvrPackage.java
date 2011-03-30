package com.succezbi.mdr.impl.svr;

import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.succezbi.mdr.impl.metamodel.MetaClass;
import com.succezbi.mdr.impl.metamodel.MetaExtent;
import com.succezbi.mdr.impl.metamodel.MetaFactory;
import com.succezbi.mdr.impl.metamodel.MetaPackage;

public class SvrPackage extends MetaPackage {

	public SvrPackage(MetaExtent extent) {
		super(extent);
	}

	public void init() throws IOException, DocumentException {
		InputStream is = SvrPackage.class.getResourceAsStream("metamodel-server.xml");
		try {
			this.registMetaModel(is);
		}
		finally {
			is.close();
		}
	}

	private void registMetaModel(InputStream is) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
	}

	private static final String SVR_SBISERVER = "Server";

	@Override
	public MetaClass getMetaClass(String type) {
		if (SVR_SBISERVER.endsWith(type)) {
			//将SBIServer载入到hibernate的Configuration，映射表
			MetaClass metaclass = new MetaClass();
			metaclass.setName(type);
			return metaclass;
		}
		return null;
	}

	@Override
	public MetaFactory getFactory() {
		return new SvrFactory(this.getExtent());
	}
}
