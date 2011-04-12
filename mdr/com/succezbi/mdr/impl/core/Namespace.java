package com.succezbi.mdr.impl.core;

import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.Session;

@Entity(name="Namespace")
@Table(name="CORE_NAMESPACE")
public abstract class Namespace extends ModelElement {

	@Column(name = "LFT")
	private int left;

	@Column(name = "RGT")
	private int right;
	
	@ManyToOne
	@JoinColumn(name="parentid")
	private Namespace parent = null;

	@Transient
	private String entityname = null;

	public Namespace getParent() {
		//查询LET<this.left,RGT>this.RGT，则得到全部的父节点，其中RGT最小的是直接父节点
		Session session = this.getExtent().getSession();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("from ").append(entityname).append(" where LFT<").append(this.getLeft()).append(" and RGT > ").append(
					this.getRight());
			String hql = sb.toString();
			Object obj = session.load(hql, null);
			Iterator it = session.createQuery(hql).iterate();
			if (it.hasNext()) {
				return (Namespace) it.next();
			}
			else {
				return null;
			}
		}
		finally {
			session.close();
		}
	}

	protected void setLeft(int left) {
		this.left = left;
	}

	protected int getLeft() {
		return left;
	}

	protected void setRight(int right) {
		this.right = right;
	}

	protected int getRight() {
		return right;
	}
	
	public String getAbsolutePath() {
		Session session = this.getExtent().getSession();
		String entityname = this.getEntityName();
		StringBuffer sb = new StringBuffer(30);
		sb.append("from ").append(entityname);
		sb.append(" where LFT>10 OR RGT<3");
		//		DetachedCriteria cri = this.createDetachedCriteria();
		//		List list = cri.getExecutableCriteria(session).list();
		//		for(int i = 0; i < list.size();i++){
		//			Object obj = list.get(i);
		//			System.out.println(obj);
		//		}
		Query query = session.createQuery("update FileObject set LFT=3");
		query.executeUpdate();
		//Iterator it = query.iterate();

		//		query.setParameter("entity", this.getEntityName());
		//		//query.setLockMode(paramString, paramLockMode);
		//		int row = query.executeUpdate();
		//		System.out.println("执行了" + row + "行更新!");
		return null;
	}


	public boolean exists() {
		return false;
	}


	public Namespace getRoot() {
		Session session = this.getExtent().getSession();
		return null;
	}


	public boolean delete() {
		return false;
	}


	public boolean create() {
		return false;
	}


	public Namespace[] list() {
		return null;
	}


	public boolean renameTo(Namespace dest) {
		return false;
	}


	public boolean moveTo(Namespace parent) {
		return false;
	}


	public boolean copyTo(Namespace parent) {
		return false;
	}


	public int compareTo(Namespace file) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setParent(Namespace parent) {
		this.parent = parent;
	}
}
