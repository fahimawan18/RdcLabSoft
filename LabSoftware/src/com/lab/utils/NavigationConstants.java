package com.lab.utils;

public class NavigationConstants {

	public static String ON_AUTHENTICATION_NAVIGATION = "MainSummary.jsf";
	public static String ON_AUTHENTICATION_FAIL_NAVIGATION = "ntlmLogin.jsf";
	public static String ON_AUTHENTICATION_MULTI_ACCOUNTS = "MultiLoginOption.jsf";
	static final public String SUCCESS = "success";
	static final public String FAILURE = "failure";
	static final public String LOGOUT = "logoutExit";
	
	public static String HOME_NAVIGATION = "/pages/homePage.xhtml?faces-redirect=true";
	public static String LOGOUT_NAVIGATION = "/pages/logout.xhtml?faces-redirect=true";	
	
	public static String REGISTER_CLIENT_NAVIGATION = "/pages/clients/registerClient.xhtml?faces-redirect=true";
	public static String UPDATE_CLIENT_NAVIGATION = "/pages/clients/updateClient.xhtml?faces-redirect=true";
	public static String UPDATE_CLIENT_DETAILS_NAVIGATION = "/pages/clients/updateClientDetails.xhtml?faces-redirect=true";
	public static String UPOAD_CLIENT_DATA_NAVIGATION = "/pages/clients/uploadScanned.xhtml?faces-redirect=true";
	
	public static String RESET_REPEATER_NAVIGATION = "/pages/repeater/resetRepeater.xhtml?faces-redirect=true";
	
	public static String CLIENT_PAYMENY_NAVIGATION = "/pages/clients/cashPayment.xhtml?faces-redirect=true";
	public static String CLIENT_PAYMENY_DETAILS_NAVIGATION = "/pages/clients/cashPaymentDetails.xhtml?faces-redirect=true";
	
	public static String MEDICAL_GPE_NAVIGATION = "/pages/medical/medicalGpe.xhtml?faces-redirect=true";
	public static String MEDICAL_GPE_DETAILS_NAVIGATION = "/pages/medical/medicalGpeDetails.xhtml?faces-redirect=true";
	public static String MEDICAL_XRAY_NAVIGATION = "/pages/medical/medicalXray.xhtml?faces-redirect=true";
	
	public static String RADIO_XRAY_NAVIGATION = "/pages/radiology/radioXray.xhtml?faces-redirect=true";
	public static String RADIO_XRAY_DETAILS_NAVIGATION = "/pages/radiology/radioXrayDetails.xhtml?faces-redirect=true";
	
	public static String SAMPLE_DATA_NAVIGATION = "/pages/lab/sampleCollection.xhtml?faces-redirect=true";
	public static String SAMPLE_DATA_DETAILS_NAVIGATION = "/pages/lab/sampleCollectionDetails.xhtml?faces-redirect=true";
	public static String LAB_DATA_NAVIGATION = "/pages/lab/labDept.xhtml?faces-redirect=true";
	public static String LAB_DATA_DETAILS_NAVIGATION = "/pages/lab/labDeptDetails.xhtml?faces-redirect=true";
	
	public static String PATHO_STATUS_NAVIGATION = "/pages/pathology/viewAllStatus.xhtml?faces-redirect=true";
	public static String PATHO_VERIFY_NAVIGATION = "/pages/pathology/verifyLabResult.xhtml?faces-redirect=true";
	public static String PATHO_VERIFY_DETAILS_NAVIGATION = "/pages/pathology/verifyLabResultDetails.xhtml?faces-redirect=true";
	
	public static String DIRECTOR_FINAL_NAVIGATION = "/pages/director/finalDeclaration.xhtml?faces-redirect=true";
	public static String DIRECTOR_FINAL_DETAILS_NAVIGATION = "/pages/director/finalDeclarationDetails.xhtml?faces-redirect=true";
	
	public static String PRINT_REPORT_NAVIGATION = "/pages/reporting/printMedicalReport.xhtml?faces-redirect=true";
		
	public static String SEARCH_NAVIGATION = "/pages/search/advanceSearch.xhtml?faces-redirect=true";
	
	
	public static String CONTACTUS_NAVIGATION = "/pages/contactUs.xhtml?faces-redirect=true";
	
	public static String INDL_NAVIGATION = "/pages/addIndlDetails.xhtml?faces-redirect=true";
	public static String VISIT_NAVIGATION = "/pages/addVisitDetails.xhtml?faces-redirect=true";

	public static String ADMIN_ADD_USERS_NAVIGATION = "/pages/admin/addUsers.xhtml?faces-redirect=true";
	public static String ADMIN_MANAGE_USERS_NAVIGATION = "/pages/admin/manageUsers.xhtml?faces-redirect=true";
	public static String ADMIN_CHANGE_PASSWORD = "/pages/admin/changePassword.xhtml?faces-redirect=true";
	public static String ADMIN_MANAGE_FACTORS_NAVIGATION = "/pages/admin/manageFactors.xhtml?faces-redirect=true";
}
