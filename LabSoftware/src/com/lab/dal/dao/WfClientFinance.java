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
@Table (name = "wf_client_finance")
public class WfClientFinance 
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name = "cash_amount")
	private Integer cashAmount;
	
	@Column(name = "cash_paid_status")
	private String cashPaidStatus;
	
	@Column(name = "cash_paid_date")
	private Date cashPaidDate;
	
	
	public WfClientFinance() 
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


	public Integer getCashAmount() {
		return cashAmount;
	}


	public void setCashAmount(Integer cashAmount) {
		this.cashAmount = cashAmount;
	}


	public String getCashPaidStatus() {
		return cashPaidStatus;
	}


	public void setCashPaidStatus(String cashPaidStatus) {
		this.cashPaidStatus = cashPaidStatus;
	}


	public Date getCashPaidDate() {
		return cashPaidDate;
	}


	public void setCashPaidDate(Date cashPaidDate) {
		this.cashPaidDate = cashPaidDate;
	}

}
