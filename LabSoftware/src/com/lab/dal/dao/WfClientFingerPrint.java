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
@Table (name = "wf_client_fingerprint")
public class WfClientFingerPrint 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name= "FINGER_PRINT")
	private byte[] fingerPrint;
	
	@Column(name= "FINGER_INDEX")
	private String fingerIndex;
	
	@Column(name= "IS_DELETE")
	private String isDelete;
	
	@Column(name= "LENGTH")
	private int length;
	
	@Column(name= "INSERT_DATE")
	private Date insertDate;
	
	public WfClientFingerPrint() 
	{
		this.isDelete = "No";
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

	public byte[] getFingerPrint() {
		return fingerPrint;
	}

	public void setFingerPrint(byte[] fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

	

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getFingerIndex() {
		return fingerIndex;
	}

	public void setFingerIndex(String fingerIndex) {
		this.fingerIndex = fingerIndex;
	}

	
}
