package com.succezbi.demo.test;

import junit.framework.TestCase;

import com.succezbi.mdr.api.MDRApiFactory;
import com.succezbi.mdr.api.MetaDataEngine;
import com.succezbi.mdr.api.MetaDataEntity;

public class TestProject extends TestCase{
	
	private static final MetaDataEngine engine = MDRApiFactory.getMetaDataEngine();
	
	@Override
	protected void setUp() throws Exception {
		
	}
	
	public void testCreateProject() throws Exception{
		MetaDataEntity project = engine.createNewEntity(null, "Server.Project", "project1");
		project.setProperty("version", "0.0.1");
		project.setProperty("DBConnPool", "3c7030f0536611e085dbf04da27e580f");
		project.setProperty("useServerDBConnPool", false);
		project.setProperty("useServerPool", "");
		assertEquals("Project保存前", true, project.isConsist());
		String id = engine.save(project);
		assertEquals("Project保存后", true, project.isConsist());
		
		//当一个元数据的entity是consist的后，便能够对齐进行一些操作：
		
		
		
	}
	
	public void testRenameProjet(){
		
	}
	
	public void testDropProject(){
		
	}
}
