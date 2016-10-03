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
@Table (name = "wf_client_samples")
public class WfClientSamples 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name = "blood_Sample")
	private String bloodSample;
	
	@Column(name = "urine_Sample")
	private String urineSample;
	
	@Column(name = "sputum_Sample")
	private String sputumSample;
	
	@Column(name = "stool_Sample")
	private String stoolSample;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "insert_date")
	private Date insertDate;
	
	public WfClientSamples() {
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

	public String getBloodSample() {
		return bloodSample;
	}

	public void setBloodSample(String bloodSample) {
		this.bloodSample = bloodSample;
	}

	public String getUrineSample() {
		return urineSample;
	}

	public void setUrineSample(String urineSample) {
		this.urineSample = urineSample;
	}

	public String getSputumSample() {
		return sputumSample;
	}

	public void setSputumSample(String sputumSample) {
		this.sputumSample = sputumSample;
	}

	public String getStoolSample() {
		return stoolSample;
	}

	public void setStoolSample(String stoolSample) {
		this.stoolSample = stoolSample;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

}
