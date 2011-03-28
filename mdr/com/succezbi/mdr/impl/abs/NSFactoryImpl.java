package com.succezbi.mdr.impl.abs;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.succezbi.util.HibernateUtil;

public class NSFactoryImpl implements NSFactory{

	@Override
	public FileObject createFileObject(FileObject parent, String name, boolean isdir) {
		HBRFileObjectImpl fileobject = new HBRFileObjectImpl(parent, name, isdir);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		return fileobject;
	}

	@Override
	public Organization createOrganization(Organization parent, String name, boolean isUper) {
		return new HBROrganizationImpl(parent, name);
	}
}
