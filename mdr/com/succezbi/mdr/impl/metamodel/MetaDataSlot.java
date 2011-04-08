package com.succezbi.mdr.impl.metamodel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name="DataSlot")
@Table(name = "MDR_DATASLOT")
public class MetaDataSlot implements Serializable {

	private static final long serialVersionUID = -5796813691873046476L;

	@Id
	@Column(name = "key")
	private String key = null;

	@ManyToOne
	@JoinColumn(name = "objectid")
	private MetaObject object = null;
	

	@Column(name = "value", columnDefinition = "BLOB")
	private byte[] valuebytes = null;

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setObject(MetaObject object) {
		this.object = object;
	}

	public MetaObject getObject() {
		return object;
	}

	protected void setValuebytes(byte[] valuebytes) {
		this.valuebytes = valuebytes;
	}

	protected byte[] getValuebytes() {
		return valuebytes;
	}
	
	public void setValue(Object object) throws IOException{
		if(object == null){
			return;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		try{
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			try{
				oos.writeObject(object);
				valuebytes = bos.toByteArray();
			}finally{
				oos.close();
			}
		}finally{
			bos.close();
		}
	}
	
	public Object getValue() throws IOException, ClassNotFoundException{
		if(this.valuebytes == null){
			return null;
		}
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(valuebytes));
		try{
			return ois.readObject();
		}finally{
			ois.close();
		}
	}
}
