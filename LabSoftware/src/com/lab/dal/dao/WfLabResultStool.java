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
@Table(name = "wf_lab_result_stool")
public class WfLabResultStool
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name = "helminthes")
	private String helminthes;
	
	@Column(name = "giardia")
	private String giardia;
	
	@Column(name = "bilharzias")
	private String bilharzias;
	
	@Column(name = "salmonella")
	private String salmonella;
	
	@Column(name = "shigella")
	private String shigella;
	
	@Column(name = "cholera")
	private String cholera;	
	
	public WfLabResultStool() {
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

	public String getHelminthes() {
		return helminthes;
	}

	public void setHelminthes(String helminthes) {
		this.helminthes = helminthes;
	}

	public String getGiardia() {
		return giardia;
	}

	public void setGiardia(String giardia) {
		this.giardia = giardia;
	}

	public String getBilharzias() {
		return bilharzias;
	}

	public void setBilharzias(String bilharzias) {
		this.bilharzias = bilharzias;
	}

	public String getSalmonella() {
		return salmonella;
	}

	public void setSalmonella(String salmonella) {
		this.salmonella = salmonella;
	}

	public String getShigella() {
		return shigella;
	}

	public void setShigella(String shigella) {
		this.shigella = shigella;
	}

	public String getCholera() {
		return cholera;
	}

	public void setCholera(String cholera) {
		this.cholera = cholera;
	}
	

}
