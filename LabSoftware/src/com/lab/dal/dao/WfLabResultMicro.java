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
@Table(name = "wf_lab_Result_micro")
public class WfLabResultMicro 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name = "pus_cells")
	private String pusCels;
	
	@Column(name = "rbc")
	private String rbc;

	@Column(name = "others")
	private String others;
	
	public WfLabResultMicro() {
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

	public String getPusCels() {
		return pusCels;
	}

	public void setPusCels(String pusCels) {
		this.pusCels = pusCels;
	}

	public String getRbc() {
		return rbc;
	}

	public void setRbc(String rbc) {
		this.rbc = rbc;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}
}
