package server;

import java.io.InputStream;

import com.succezbi.mdr.api.MDRApiFactory;

public class ServerRegister {
	
	/**
	 * 注册服务器级别的元模型
	 * @throws Exception
	 */
	public void register() throws Exception{
		InputStream is = ServerRegister.class.getResourceAsStream("metamodel-server.xml");
		try{
			MDRApiFactory.getMetaDataEngine().register(is);
		}finally{
			is.close();
		}
	}

}
