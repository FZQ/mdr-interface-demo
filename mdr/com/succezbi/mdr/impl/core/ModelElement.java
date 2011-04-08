package com.succezbi.mdr.impl.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.succezbi.mdr.impl.metamodel.MetaObject;

/**
 * ModelElement，ModelElement可以与其他ModelElement组成树，ModelElement组成树的方式
 * 一般是按照不同类型的ModelElement在不同的层次形式组成的，也就是不会存在一个类型的
 * ModelElement的子节点为其同类型的ModelElement情况。例如Project下可以放置ProjectFold
 * 但不能包含另外一个Project
 * @author classfoo
 *
 */
@Entity(name = "ModelElement")
@Table(uniqueConstraints = { @UniqueConstraint(name = "uniquename", columnNames = "name") })
public abstract class ModelElement extends MetaObject {

	@Column(name = "NAME")
	private String name = null;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parentid")
	private ModelElement parent = null;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setParent(ModelElement parent) {
		this.parent = parent;
	}

	public ModelElement getParent() {
		return parent;
	}
}
