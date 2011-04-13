package com.succezbi.demo.test;

import java.io.InputStream;

import junit.framework.TestCase;

import com.succezbi.mdr.api.MDRApiFactory;
import com.succezbi.mdr.api.MetaDataEngine;
import com.succezbi.mdr.api.MetaDataEntity;
import com.succezbi.mdr.impl.svr.SBIProject;
import com.succezbi.mdr.impl.svr.SBIServer;

public class TestCreateProject extends TestCase {

	private static final MetaDataEngine engine = MDRApiFactory.getMetaDataEngine();

	@Override
	protected void setUp() throws Exception {
		InputStream is = SBIServer.class.getResourceAsStream("metamodel-server.xml");
		try {
			engine.register(is);
		}
		finally {
			is.close();
		}
		super.setUp();
	}

	public void testCreateProject() throws Exception {
		MetaDataEntity server = engine.createNewEntity(null, SBIServer.class.getName(), "Server");
		server.setProperty("description", "测试用例：TestCreateProject#testCreateProject");
		String serverid = engine.save(server);
		
		MetaDataEntity project1 = engine.createNewEntity(serverid, SBIProject.class.getName(), "Project1");
		engine.save(project1);
		MetaDataEntity project2 = engine.createNewEntity(serverid, SBIProject.class.getName(), "Project2");
		engine.save(project2);
	}
}
