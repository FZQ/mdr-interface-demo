package com.succezbi.mdr.impl.svr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.criterion.DetachedCriteria;

import com.succezbi.mdr.impl.core.Namespace;

@Entity(name = "SBIServer")
@Table(name = "SVR_SBISERVER")
public class SBIServer extends Namespace {

	@Column(name = "VERSION", length = 128)
	private String version = null;

	@Column(name = "IP", length = 17)
	private String ip = null;

	@Override
	protected String getEntityName() {
		return "SBIServer";
	}

	@Override
	protected DetachedCriteria createDetachedCriteria() {
		return null;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

}
