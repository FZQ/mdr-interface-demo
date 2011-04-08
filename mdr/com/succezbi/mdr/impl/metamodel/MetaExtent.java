package com.succezbi.mdr.impl.metamodel;

import java.net.URL;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity(name="MetaExtent")
public class MetaExtent {

	private static final Logger log = Logger.getLogger(MetaExtent.class);

	@Transient
	private static Configuration configuration = null;
	
	@Transient
	private static SessionFactory sessionFactory = null;
	
	@Transient
	private ArrayList<String> pathes = new ArrayList<String>();

	public MetaExtent() {
		this.init();
	}
	
	private void init(){
		configuration = new Configuration();
		URL res = MetaExtent.class.getResource("hibernate.cfg.xml");
		configuration.configure(res);
		for(int i = 0; i < pathes.size();i++){
			String path = pathes.get(i);
			try {
				this.registMetaClass(path);
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		sessionFactory = configuration.buildSessionFactory();
	}

	/**
	 * 将一个元数据的定义类载入到元数据引擎中
	 * @param classpath
	 * @throws ClassNotFoundException
	 */
	private void registMetaClass(String classpath) throws ClassNotFoundException {
		try {
			Class<?> clazz = Class.forName(classpath);
			log.info("注册类:" + classpath + "到hibernate");
			configuration.addAnnotatedClass(clazz);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void addMetaClass(String path){
		this.pathes.add(path);
	}

	public void rebuild() {
		this.init();
	}
	
	public Session getSession() {
		return sessionFactory.openSession();
	}
}
