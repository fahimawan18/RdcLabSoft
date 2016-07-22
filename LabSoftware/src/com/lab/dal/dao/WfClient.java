package com.lab.dal.dao;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.lab.utils.Environment;



@Entity
@Table (name = "wf_client")
public class WfClient 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="RDC_TOKEN_NO")
	private String rdcTokenNo;
	
	@Column(name ="GAMCA_SLIP_NO")
	private String gamcaSlipNo;
	
	@Column(name ="FIRST_NAME")
	private String firstName;
	
	@Column (name = "LAST_NAME")
	private String lastName;
	
	@Column(name ="FATHER_NAME")
	private String fatherName;
	
	@Column(name ="gender")
	private String gender;
	
	@Column(name ="nationality")
	private String nationality;
	
	@Column(name ="dob")
	private Date dob;
	
	@Column(name ="passport_no")
	private String passportNo;
	
	@Column(name ="passport_issue_date")
	private Date passportIssueDate;
	
	@Column(name ="passport_expiry_date")
	private Date passportExpiryDate;
	
	@Column(name ="passport_issue_place")
	private String passportIssuePlace;
	
	@Column(name ="cnic_no")
	private String cnicNo;
	
	@Column(name ="marital_status")
	private String maritalStatus;
	
	@Column(name ="mobile_no")
	private String mobileNo;
	
	@Column(name ="applied_for_country")
	private String appliedForCountry;
	
	@Column(name ="applied_for_position")
	private String appliedForPosition;
	
	@Column(name ="rect_agency")
	private String rectAgency;
	
	@Column(name = "client_status")
	private String clientStatus;
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn (name = "insert_by")
	private ApplicationUsers insertBy;
	
	@ManyToOne 
	@JoinColumn (name = "update_by")
	private ApplicationUsers updateBy;
	
	@Column(name = "insert_date")
	private Date insertDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@ManyToOne 
	@JoinColumn (name = "final_declared_by")
	private ApplicationUsers finalDeclaredBy;
	
	@Column(name = "final_declared_date")
	private Date finalDeclaredDate;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="clientId")  
	private WfClientScannedFiles scannedFiles;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="clientId")
	private WfClientFinance cashPayment;
	
	@OneToOne(mappedBy="clientId")  
	private WfClientGpe gpe;
	
	@OneToOne( mappedBy="clientId")  
	private WfClientXray xray;
	
	@OneToOne(mappedBy="clientId")  
	private WfClientSamples samples;
	
	@OneToOne(mappedBy="clientId")  
	private WfLabResultUrine urine;
	
	@OneToOne(mappedBy="clientId")  
	private WfLabResultMicro micro;
	
	@OneToOne(mappedBy="clientId")  
	private WfLabResultBlood blood;
	
	@OneToOne(mappedBy="clientId")  
	private WfLabResultSputum sputum;
	
	@OneToOne(mappedBy="clientId")  
	private WfLabResultStool stool;
	
	@OneToOne(mappedBy="clientId")  
	private WfClientProgress progress;
	
	@javax.persistence.Transient
	private String picPath;
	
	
	
	
	public WfClient() 
	{
		// TODO Auto-generated constructor stub
		this.insertDate = new Date();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRdcTokenNo() {
		return rdcTokenNo;
	}

	public void setRdcTokenNo(String rdcTokenNo) {
		this.rdcTokenNo = rdcTokenNo;
	}

	public String getGamcaSlipNo() {
		return gamcaSlipNo;
	}

	public void setGamcaSlipNo(String gamcaSlipNo) {
		this.gamcaSlipNo = gamcaSlipNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getPassportIssueDate() {
		return passportIssueDate;
	}

	public void setPassportIssueDate(Date passportIssueDate) {
		this.passportIssueDate = passportIssueDate;
	}

	public Date getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(Date passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public String getPassportIssuePlace() {
		return passportIssuePlace;
	}

	public void setPassportIssuePlace(String passportIssuePlace) {
		this.passportIssuePlace = passportIssuePlace;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAppliedForCountry() {
		return appliedForCountry;
	}

	public void setAppliedForCountry(String appliedForCountry) {
		this.appliedForCountry = appliedForCountry;
	}

	public String getAppliedForPosition() {
		return appliedForPosition;
	}

	public void setAppliedForPosition(String appliedForPosition) {
		this.appliedForPosition = appliedForPosition;
	}

	public String getRectAgency() {
		return rectAgency;
	}

	public void setRectAgency(String rectAgency) {
		this.rectAgency = rectAgency;
	}

	public String getClientStatus() {
		return clientStatus;
	}

	public void setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
	}

	public String getCnicNo() {
		return cnicNo;
	}

	public void setCnicNo(String cnicNo) {
		this.cnicNo = cnicNo;
	}

	public ApplicationUsers getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(ApplicationUsers insertBy) {
		this.insertBy = insertBy;
	}

	public ApplicationUsers getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(ApplicationUsers updateBy) {
		this.updateBy = updateBy;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public WfClientScannedFiles getScannedFiles() {
		return scannedFiles;
	}

	public void setScannedFiles(WfClientScannedFiles scannedFiles) {
		this.scannedFiles = scannedFiles;
	}

	public WfClientFinance getCashPayment() {
		return cashPayment;
	}

	public void setCashPayment(WfClientFinance cashPayment) {
		this.cashPayment = cashPayment;
	}

	public WfClientGpe getGpe() {
		return gpe;
	}

	public void setGpe(WfClientGpe gpe) {
		this.gpe = gpe;
	}

	public WfClientXray getXray() {
		return xray;
	}

	public void setXray(WfClientXray xray) {
		this.xray = xray;
	}

	public WfClientSamples getSamples() {
		return samples;
	}

	public void setSamples(WfClientSamples samples) {
		this.samples = samples;
	}

	public WfLabResultUrine getUrine() {
		return urine;
	}

	public void setUrine(WfLabResultUrine urine) {
		this.urine = urine;
	}

	public WfLabResultMicro getMicro() {
		return micro;
	}

	public void setMicro(WfLabResultMicro micro) {
		this.micro = micro;
	}

	public WfLabResultBlood getBlood() {
		return blood;
	}

	public void setBlood(WfLabResultBlood blood) {
		this.blood = blood;
	}

	public WfLabResultSputum getSputum() {
		return sputum;
	}

	public void setSputum(WfLabResultSputum sputum) {
		this.sputum = sputum;
	}

	public WfLabResultStool getStool() {
		return stool;
	}

	public void setStool(WfLabResultStool stool) {
		this.stool = stool;
	}

	public WfClientProgress getProgress() {
		return progress;
	}

	public void setProgress(WfClientProgress progress) {
		this.progress = progress;
	}

	public ApplicationUsers getFinalDeclaredBy() {
		return finalDeclaredBy;
	}

	public void setFinalDeclaredBy(ApplicationUsers finalDeclaredBy) {
		this.finalDeclaredBy = finalDeclaredBy;
	}

	public Date getFinalDeclaredDate() {
		return finalDeclaredDate;
	}

	public void setFinalDeclaredDate(Date finalDeclaredDate) {
		this.finalDeclaredDate = finalDeclaredDate;
	}

	public String getPicPath() 
	{
		if(this.scannedFiles!=null && this.scannedFiles.getPhotoMime()!=null &&
				this.scannedFiles.getPhotoMime().trim().length()>0)
		{
			this.picPath = Environment.getFilePreviewPath()+this.id+Environment.getPhotoNameFormat()
				+this.scannedFiles.getPhotoMime();
		}
		else
		{
			this.picPath = Environment.getFilePreviewPath()+this.id+Environment.getPhotoNameFormat()
					+"jpg";
		}
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}


}
