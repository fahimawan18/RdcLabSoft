package com.lab.dal.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "wf_client_xray")
public class WfClientXray 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name = "xray_status")
	private String xrayStatus;
	
	@Column(name = "lungs")
	private String lungs;
	
	@Column(name = "chest")
	private String chest;
	
	@Column(name = "opinion")
	private String opinion;
	
	@Column(name = "xray_insert_date")
	private Date xrayInsertDate;
	
	@Column(name = "radiology_insert_date")
	private Date radiologyInsertDate;
	
	public WfClientXray() 
	{
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

	public String getXrayStatus() {
		return xrayStatus;
	}

	public void setXrayStatus(String xrayStatus) {
		this.xrayStatus = xrayStatus;
	}

	public String getLungs() {
		return lungs;
	}

	public void setLungs(String lungs) {
		this.lungs = lungs;
	}

	public String getChest() {
		return chest;
	}

	public void setChest(String chest) {
		this.chest = chest;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Date getXrayInsertDate() {
		return xrayInsertDate;
	}

	public void setXrayInsertDate(Date xrayInsertDate) {
		this.xrayInsertDate = xrayInsertDate;
	}

	public Date getRadiologyInsertDate() {
		return radiologyInsertDate;
	}

	public void setRadiologyInsertDate(Date radiologyInsertDate) {
		this.radiologyInsertDate = radiologyInsertDate;
	}

}
