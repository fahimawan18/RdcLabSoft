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
@Table(name = "WF_client_gpe")
public class WfClientGpe
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name = "bp")
	private String bp;
	
	@Column(name = "temperature", precision = 5, scale = 2)
	private Double temperature;
	
	@Column(name = "temperature_scale")
	private String temperatureScale;
	
	@Column(name = "height", precision = 5, scale =2)
	private Double height;
	
	@Column(name = "height_Scale")
	private String heightScale;
	
	@Column(name = "weight", precision = 5, scale =2)
	private Double weight;
	
	@Column(name = "weight_Scale")
	private String weightScale;
	
	@Column(name = "ext_fingers")
	private String extFingers;
	
	@Column(name = "ext_deformity")
	private String extDeformity;
	
	@Column(name = "ext_vericose")
	private String extVericose;
	
	@Column(name = "neck")
	private String neck;
	
	@Column(name = "neuro_pe")
	private String neuroPe;
	
	@Column(name = "cns")
	private String cns;
	
	@Column(name = "lungs")
	private String lungs;
	
	@Column(name = "heart")
	private String heart;
	
	@Column(name = "abdominal")
	private String abdominal;
	
	@Column(name = "liver")
	private String liver;
	
	@Column(name = "kidneys")
	private String kidneys;
	
	@Column(name = "spleen")
	private String spleen;
	
	@Column(name = "hernia")
	private String hernia;
	
	@Column(name = "venereal")
	private String venereal;
	
	@Column(name = "allergy")
	private String allergy;
	
	@Column(name = "other")
	private String other;
	
	@Column(name = "psychiatry")
	private String psychiatry;
	
	@Column(name = "ear_Left")
	private String earLeft;
	
	@Column(name = "ear_Right")
	private String earRight;
	
	@Column(name = "eye_left")
	private String eyeLeft;
	
	@Column(name = "eye_right")
	private String eyeRight;
	
	@Column(name = "pregnancy")
	private String pregnancy;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "medical_status")
	private String medicalStatus;
	
	@Column(name = "skin")
	private String skin;
	
	public WfClientGpe() {
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

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getTemperatureScale() {
		return temperatureScale;
	}

	public void setTemperatureScale(String temperatureScale) {
		this.temperatureScale = temperatureScale;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getHeightScale() {
		return heightScale;
	}

	public void setHeightScale(String heightScale) {
		this.heightScale = heightScale;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getWeightScale() {
		return weightScale;
	}

	public void setWeightScale(String weightScale) {
		this.weightScale = weightScale;
	}

	public String getExtFingers() {
		return extFingers;
	}

	public void setExtFingers(String extFingers) {
		this.extFingers = extFingers;
	}

	public String getExtDeformity() {
		return extDeformity;
	}

	public void setExtDeformity(String extDeformity) {
		this.extDeformity = extDeformity;
	}

	public String getExtVericose() {
		return extVericose;
	}

	public void setExtVericose(String extVericose) {
		this.extVericose = extVericose;
	}

	public String getNeck() {
		return neck;
	}

	public void setNeck(String neck) {
		this.neck = neck;
	}

	public String getNeuroPe() {
		return neuroPe;
	}

	public void setNeuroPe(String neuroPe) {
		this.neuroPe = neuroPe;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public String getLungs() {
		return lungs;
	}

	public void setLungs(String lungs) {
		this.lungs = lungs;
	}

	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public String getAbdominal() {
		return abdominal;
	}

	public void setAbdominal(String abdominal) {
		this.abdominal = abdominal;
	}

	public String getLiver() {
		return liver;
	}

	public void setLiver(String liver) {
		this.liver = liver;
	}

	public String getKidneys() {
		return kidneys;
	}

	public void setKidneys(String kidneys) {
		this.kidneys = kidneys;
	}

	public String getSpleen() {
		return spleen;
	}

	public void setSpleen(String spleen) {
		this.spleen = spleen;
	}

	public String getHernia() {
		return hernia;
	}

	public void setHernia(String hernia) {
		this.hernia = hernia;
	}

	public String getVenereal() {
		return venereal;
	}

	public void setVenereal(String venereal) {
		this.venereal = venereal;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getPsychiatry() {
		return psychiatry;
	}

	public void setPsychiatry(String psychiatry) {
		this.psychiatry = psychiatry;
	}

	public String getEarLeft() {
		return earLeft;
	}

	public void setEarLeft(String earLeft) {
		this.earLeft = earLeft;
	}

	public String getEarRight() {
		return earRight;
	}

	public void setEarRight(String earRight) {
		this.earRight = earRight;
	}

	public String getEyeLeft() {
		return eyeLeft;
	}

	public void setEyeLeft(String eyeLeft) {
		this.eyeLeft = eyeLeft;
	}

	public String getEyeRight() {
		return eyeRight;
	}

	public void setEyeRight(String eyeRight) {
		this.eyeRight = eyeRight;
	}

	public String getPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(String pregnancy) {
		this.pregnancy = pregnancy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMedicalStatus() {
		return medicalStatus;
	}

	public void setMedicalStatus(String medicalStatus) {
		this.medicalStatus = medicalStatus;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}
	
	
	
	

}
