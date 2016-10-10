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
@Table(name = "wf_lab_result_blood")
public class WfLabResultBlood 
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name = "haemoglobin")
	private String haemoglobin;
	
	@Column(name = "tf_malaria")
	private String tfMalaria;
	
	@Column(name = "tf_micro_filaria")
	private String tfMicroFilaria;
	
	@Column(name = "blood_gp")
	private String bloodGp;
	
	@Column(name = "blood_glucose")
	private String bloodGlucose;
	
	@Column(name = "lft")
	private String lft;
	
	@Column(name = "sgot")
	private String sgot;
	
	@Column(name = "sgpt")
	private String sgpt;
	
	@Column(name = "rft")
	private String rft;
	
	@Column(name = "urea")
	private String urea;
	
	@Column(name = "creatinine")
	private String creatinine;
	
	@Column(name = "anti_hiv")
	private String antiHiv;
	
	@Column(name = "anti_hcv")
	private String antiHcv;

	@Column(name = "hbs_ag")
	private String hbsAg;
	
	@Column(name = "vdrl")
	private String vdrl;
	
	@Column(name = "tpha")
	private String tpha;
	
	@Column(name = "lab_insert_date")
	private Date labInsertDate;
	

	
	
	public WfLabResultBlood() 
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


	public String getHaemoglobin() {
		return haemoglobin;
	}


	public void setHaemoglobin(String haemoglobin) {
		this.haemoglobin = haemoglobin;
	}


	public String getTfMalaria() {
		return tfMalaria;
	}


	public void setTfMalaria(String tfMalaria) {
		this.tfMalaria = tfMalaria;
	}


	public String getTfMicroFilaria() {
		return tfMicroFilaria;
	}


	public void setTfMicroFilaria(String tfMicroFilaria) {
		this.tfMicroFilaria = tfMicroFilaria;
	}


	public String getBloodGp() {
		return bloodGp;
	}


	public void setBloodGp(String bloodGp) {
		this.bloodGp = bloodGp;
	}


	public String getBloodGlucose() {
		return bloodGlucose;
	}


	public void setBloodGlucose(String bloodGlucose) {
		this.bloodGlucose = bloodGlucose;
	}


	public String getLft() {
		return lft;
	}


	public void setLft(String lft) {
		this.lft = lft;
	}


	public String getSgot() {
		return sgot;
	}


	public void setSgot(String sgot) {
		this.sgot = sgot;
	}


	public String getSgpt() {
		return sgpt;
	}


	public void setSgpt(String sgpt) {
		this.sgpt = sgpt;
	}


	public String getUrea() {
		return urea;
	}


	public void setUrea(String urea) {
		this.urea = urea;
	}


	public String getCreatinine() {
		return creatinine;
	}


	public void setCreatinine(String creatinine) {
		this.creatinine = creatinine;
	}


	public String getAntiHiv() {
		return antiHiv;
	}


	public void setAntiHiv(String antiHiv) {
		this.antiHiv = antiHiv;
	}


	public String getAntiHcv() {
		return antiHcv;
	}


	public void setAntiHcv(String antiHcv) {
		this.antiHcv = antiHcv;
	}


	public String getHbsAg() {
		return hbsAg;
	}


	public void setHbsAg(String hbsAg) {
		this.hbsAg = hbsAg;
	}


	public String getVdrl() {
		return vdrl;
	}


	public void setVdrl(String vdrl) {
		this.vdrl = vdrl;
	}


	public String getTpha() {
		return tpha;
	}


	public void setTpha(String tpha) {
		this.tpha = tpha;
	}


	public String getRft() {
		return rft;
	}


	public void setRft(String rft) {
		this.rft = rft;
	}


	public Date getLabInsertDate() {
		return labInsertDate;
	}


	public void setLabInsertDate(Date labInsertDate) {
		this.labInsertDate = labInsertDate;
	}






}
