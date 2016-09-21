package com.lab.dal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "wf_client_scanned_files")
public class WfClientScannedFiles 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn (name = "client_id")
	private WfClient clientId;
	
	@Column(name= "SCANNED_PASSPORT")
	private byte[] scannedPassport;
	
	@Column(name= "SCANNED_GAMCA")
	private byte[] scannedGamca;
	
	@Column(name= "SCANNED_PHOTO")
	private byte[] scannedPhoto;
	
	@Column(name= "GAMCA_MIME")
	private String gamcaMime;
	
	@Column(name= "PASSPORT_MIME")
	private String passportMime;
	
	@Column(name= "PHOTO_MIME")
	private String photoMime;
	
	public WfClientScannedFiles() 
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

	public byte[] getScannedPassport() {
		return scannedPassport;
	}

	public void setScannedPassport(byte[] scannedPassport) {
		this.scannedPassport = scannedPassport;
	}

	public byte[] getScannedGamca() {
		return scannedGamca;
	}

	public void setScannedGamca(byte[] scannedGamca) {
		this.scannedGamca = scannedGamca;
	}

	public byte[] getScannedPhoto() {
		return scannedPhoto;
	}

	public void setScannedPhoto(byte[] scannedPhoto) {
		this.scannedPhoto = scannedPhoto;
	}

	public String getGamcaMime() {
		return gamcaMime;
	}

	public void setGamcaMime(String gamcaMime) {
		this.gamcaMime = gamcaMime;
	}

	public String getPassportMime() {
		return passportMime;
	}

	public void setPassportMime(String passportMime) {
		this.passportMime = passportMime;
	}

	public String getPhotoMime() {
		return photoMime;
	}

	public void setPhotoMime(String photoMime) {
		this.photoMime = photoMime;
	}
	
	
	

}
