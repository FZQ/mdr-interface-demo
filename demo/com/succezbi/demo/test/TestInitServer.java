package com.succezbi.demo.test;

import junit.framework.TestCase;
import server.ServerRegister;

import com.succezbi.mdr.api.MDRApiFactory;
import com.succezbi.mdr.api.MetaDataEngine;
import com.succezbi.mdr.api.MetaDataEntity;

public class TestInitServer extends TestCase {

	private static final MetaDataEngine engine = MDRApiFactory.getMetaDataEngine();

	/**
	 * 测试初始化服务器
	 * @throws Exception 
	 */
	public void testInitServer() throws Exception {
		ServerRegister sr = new ServerRegister();
		sr.register();
		int count = engine.getEntityCountByType("SBIServer");
		String id = null;
		if (count == 0) {
			MetaDataEntity server = engine.createNewEntity("SBIServer", "SBIServer", "root");
			server.setProperty("caption", "SBI主服务器");
			server.setProperty("version", "0.0.1");
			server.setProperty("ip", "192.168.0.1");
			id = engine.save(server);
		}
		assertEquals("初始化服务器后获取服务器元数据数目：", 1, engine.getEntityCountByType("SBIServer"));
		MetaDataEntity server = engine.get(id);
		String caption = server.getStringProperty("caption");
		assertEquals("获取服务器的标题：", "SBI主服务器", caption);
	}

	/**
	 * 测试获取服务器的直接子节点，包括：
	 * project
	 * 
	 */
	public void testListServerChilds() {

	}
}
