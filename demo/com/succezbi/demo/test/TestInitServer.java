package com.succezbi.demo.test;

import java.io.InputStream;

import junit.framework.TestCase;

import com.succezbi.mdr.api.MDRApiFactory;
import com.succezbi.mdr.api.MetaDataEngine;
import com.succezbi.mdr.api.MetaDataEntity;
import com.succezbi.mdr.impl.svr.SBIProject;
import com.succezbi.mdr.impl.svr.SBIServer;

public class TestInitServer extends TestCase {

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

	/**
	 * 测试初始化服务器
	 * @throws Exception 
	 */
	public void testInitServer() throws Exception {
		int count = engine.getEntityCountByType("SBIServer");
		String id = null;
		if (count == 0) {
			MetaDataEntity server = engine.createNewEntity(null, SBIServer.class.getName(), "root");
			server.setProperty("caption", "SBI主服务器");
			server.setProperty("version", "0.0.1");
			server.setProperty("ip", "192.168.0.1");
			id = engine.save(server);
			String caption = server.getStringProperty("caption");
			assertEquals("获取服务器的标题：", "SBI主服务器", caption);
			System.out.println(id);
		}
		assertEquals("初始化服务器后获取服务器元数据数目：", 2, engine.getEntityCountByType("SBIServer"));
		if (id != null) {
			MetaDataEntity server = engine.get(id);
		}
		MetaDataEntity[] entities = engine.getEntityByType(SBIServer.class.getName());
		for (int i = 0; i < entities.length; i++) {
			MetaDataEntity entity = entities[i];
			System.out.println(entity.getType());
		}

	}

	/**
	 * 测试获取服务器的直接子节点，包括：
	 * project
	 * @throws Exception 
	 * 
	 */
	public void testListServerChilds() throws Exception {
		MetaDataEntity server = engine.createNewEntity(null, SBIServer.class.getName(), "root");
		server.setProperty("caption", "SBI主服务器");
		server.setProperty("version", "0.0.1");
		server.setProperty("ip", "192.168.0.1");
		String svrid = engine.save(server);
		
		MetaDataEntity project1 = engine.createNewEntity(svrid, SBIProject.class.getName(), "project1");
		String project1id = engine.save(project1);
		System.out.println(project1id);
		
		MetaDataEntity project2 = engine.createNewEntity(svrid, SBIProject.class.getName(), "project2");
		String project2id = engine.save(project2);
		System.out.println(project2id);
		
		MetaDataEntity parent = project2.getParent();
		assertEquals("", svrid, parent.getID());
		
	}
}
