package com.lab.dal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appl_users")
public class ApplicationUsers 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="APPOINTMENT")
	private String appointment;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ROLE_ADMIN")
	private String roleAdmin;
	
	@Column(name="ROLE_REGN")
	private String roleRegn;
	
	@Column(name="ROLE_FINANCE")
	private String roleFinance;
	
	@Column(name="ROLE_MEDICAL")
	private String roleMedical;
	
	@Column(name="ROLE_PATHOLOGY")
	private String rolePathology;
	
	@Column(name="ROLE_DIRECTOR")
	private String roleDirector;
	
	@Column(name="ROLE_SAMPLE")
	private String roleSample;
	
	@Column(name="ROLE_LAB")
	private String roleLab;
	
	@Column(name="ROLE_RADIOLOGIST")
	private String roleRadiologist;
	
	@Column(name="ROLE_XRAY")
	private String roleXray;
	
	@Column(name="ROLE_PRINT_ROOM")
	private String rolePrintRoom;	
	
	@Column(name="PROFILE_STATUS")
	private String profileStatus;
	
	
	public ApplicationUsers() 
	{
		// TODO Auto-generated constructor stub
		this.roleAdmin = "N";
		this.roleRegn = "N";
		this.roleFinance = "N";
		this.roleMedical = "N";
		this.roleSample = "N";
		this.roleDirector = "N";
		this.rolePathology = "N";
		this.roleRadiologist = "N";
		this.roleLab = "N";
		this.roleXray = "N";
		this.rolePrintRoom = "N";
	}

	
	public String getLoggedUserString()
	 {	 	
	 	return this.appointment+ "-"+this.getName();
	 }
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppointment() {
		return appointment;
	}
	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}


	public String getProfileStatus() {
		return profileStatus;
	}


	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}


	public String getRoleAdmin() {
		return roleAdmin;
	}


	public void setRoleAdmin(String roleAdmin) {
		this.roleAdmin = roleAdmin;
	}


	public String getRoleRegn() {
		return roleRegn;
	}


	public void setRoleRegn(String roleRegn) {
		this.roleRegn = roleRegn;
	}


	public String getRoleFinance() {
		return roleFinance;
	}


	public void setRoleFinance(String roleFinance) {
		this.roleFinance = roleFinance;
	}


	public String getRoleMedical() {
		return roleMedical;
	}


	public void setRoleMedical(String roleMedical) {
		this.roleMedical = roleMedical;
	}


	public String getRoleDirector() {
		return roleDirector;
	}


	public void setRoleDirector(String roleDirector) {
		this.roleDirector = roleDirector;
	}


	public String getRoleSample() {
		return roleSample;
	}


	public void setRoleSample(String roleSample) {
		this.roleSample = roleSample;
	}


	public String getRolePathology() {
		return rolePathology;
	}


	public void setRolePathology(String rolePathology) {
		this.rolePathology = rolePathology;
	}


	public String getRoleRadiologist() {
		return roleRadiologist;
	}


	public void setRoleRadiologist(String roleRadiologist) {
		this.roleRadiologist = roleRadiologist;
	}


	public String getRoleLab() {
		return roleLab;
	}


	public void setRoleLab(String roleLab) {
		this.roleLab = roleLab;
	}


	public String getRoleXray() {
		return roleXray;
	}


	public void setRoleXray(String roleXray) {
		this.roleXray = roleXray;
	}


	public String getRolePrintRoom() {
		return rolePrintRoom;
	}


	public void setRolePrintRoom(String rolePrintRoom) {
		this.rolePrintRoom = rolePrintRoom;
	}


}
