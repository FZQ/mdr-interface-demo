package com.succezbi.mdr.impl.metamodel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MetaExtent {

	private static Configuration configuration = new Configuration();

	private static SessionFactory sessionFactory = null;

	public MetaExtent() {
		configuration.configure();
	}

	public void registMetaClass(String classpath) throws ClassNotFoundException {
		try {
			Class<?> cls = Class.forName(classpath);
			configuration.addAnnotatedClass(cls);
			this.rebuildSessionFactory(configuration);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void rebuildSessionFactory() {
		synchronized (sessionFactory) {
			sessionFactory = configuration.buildSessionFactory();
		}
	}

	public void rebuildSessionFactory(Configuration cfg) {
		synchronized (sessionFactory) {
			sessionFactory = cfg.buildSessionFactory();
			configuration = cfg;
		}
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}
}
