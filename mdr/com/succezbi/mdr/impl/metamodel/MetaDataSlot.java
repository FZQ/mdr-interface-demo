package com.succezbi.mdr.impl.metamodel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "MetaDataSlot")
@Table(name = "MDR_METADATASLOT")
@IdClass(MetaDataSlot.PrimaryKey.class)
public class MetaDataSlot {

	@Id
	private String dskey = null;

	@Id
	private MetaObject metaobject = null;

	@Column(name = "value", columnDefinition = "blob")
	private byte[] valuebytes = null;

	protected void setValuebytes(byte[] valuebytes) {
		this.valuebytes = valuebytes;
	}

	protected byte[] getValuebytes() {
		return valuebytes;
	}

	@Transient
	public void setValue(Object object) throws IOException {
		if (object == null) {
			return;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			try {
				oos.writeObject(object);
				valuebytes = bos.toByteArray();
			}
			finally {
				oos.close();
			}
		}
		finally {
			bos.close();
		}
	}

	@Transient
	public Object getValue() throws IOException, ClassNotFoundException {
		if (this.valuebytes == null) {
			return null;
		}
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(valuebytes));
		try {
			return ois.readObject();
		}
		finally {
			ois.close();
		}
	}

	public void setMetaobject(MetaObject metaobject) {
		this.metaobject = metaobject;
	}

	public MetaObject getMetaobject() {
		return metaobject;
	}

	public void setDskey(String dskey) {
		this.dskey = dskey;
	}

	public String getDskey() {
		return dskey;
	}
	
	@Embeddable
	public static class PrimaryKey implements Serializable {

		private static final long serialVersionUID = 7582481434712381494L;

		@Column(name = "dskey", length = 128)
		private String dskey = null;

		public void setDskey(String dskey) {
			this.dskey = dskey;
		}

		public String getDskey() {
			return dskey;
		}

		@ManyToOne
		@JoinColumn(name = "metaobject", columnDefinition = "varchar(32)")
		private MetaObject metaobject = null;


		public void setMetaobject(MetaObject metaobject) {
			this.metaobject = metaobject;
		}

		public MetaObject getMetaobject() {
			return metaobject;
		}
	}
}
