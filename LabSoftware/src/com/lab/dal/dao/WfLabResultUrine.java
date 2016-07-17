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
@Table(name = "wf_lab_Result_urine")
public class WfLabResultUrine 
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name = "sugar")
	private String sugar;
	
	@Column(name = "albumin")
	private String albumin;
	
	public WfLabResultUrine() {
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

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	public String getAlbumin() {
		return albumin;
	}

	public void setAlbumin(String albumin) {
		this.albumin = albumin;
	}

}
