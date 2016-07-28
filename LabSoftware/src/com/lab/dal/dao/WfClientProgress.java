package com.lab.dal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WF_client_progress")
public class WfClientProgress
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name = "regn")
	private String regn;
	
	@Column(name = "cash")
	private String cash;
	
	@Column(name = "gpe")
	private String gpe;
	
	@Column(name = "xray")
	private String xray;
	
	@Column(name = "sample")
	private String sample;
	
	@Column(name = "pathologist")
	private String pathologist;
	
	public WfClientProgress() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WfClient getClientId() {
		return clientId;
	}

	public void setClientId(WfClient clientId) {
		this.clientId = clientId;
	}

	public String getRegn() {
		return regn;
	}

	public void setRegn(String regn) {
		this.regn = regn;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getGpe() {
		return gpe;
	}

	public void setGpe(String gpe) {
		this.gpe = gpe;
	}

	public String getXray() {
		return xray;
	}

	public void setXray(String xray) {
		this.xray = xray;
	}

	public String getSample() {
		return sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}

	public String getPathologist() {
		return pathologist;
	}

	public void setPathologist(String pathologist) {
		this.pathologist = pathologist;
	}
}
