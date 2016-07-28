/**
 * 
 */
package com.lab.ui.beans;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.iac.web.util.FacesUtils;
import com.lab.ui.beans.admin.CriteriaBean;
import com.lab.utils.MessageConstants;
import com.lab.utils.NavigationConstants;

/**
 * @author 
 *
 */
@ManagedBean(name = "navBean")
@ViewScoped
public class PageNavigationBean implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private String pageName;
	
	private CriteriaBean cb = (CriteriaBean)FacesUtils.getManagedBean("crit");
	

	@PostConstruct
	public void init() {
		System.out.println("PageNavigationBean init");
		pageName = NavigationConstants.HOME_NAVIGATION;
	}

	public String navHomePage() {
		System.out.println("PageNavigationBean defaultPage.xhtml");
		pageName = NavigationConstants.HOME_NAVIGATION;
//		pageTitle = MessageConstants.Constants.PageTitles.HOME_PAGE;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.HOME_PAGE);
		return pageName;
	}
	
	public String navLogOut() {
		System.out.println("PageNavigationBean logout.xhtml");
//		UserBean.KEY_CURRENT_USER = null;
//		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//		pageName = NavigationConstants.LOGOUT_NAVIGATION;
		return pageName;
	}

	public String navRegisterClient() {
		System.out.println("PageNavigationBean registerClient.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.REGISTER_CLIENT_NAVIGATION;
//		pageTitle = MessageConstants.Constants.PageTitles.REGISTER_CLIENT;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.REGISTER_CLIENT);
		return pageName;
	}
	
	public String navUpdateClient() 
	{
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.UPDATE_CLIENT_NAVIGATION;
//		pageTitle = MessageConstants.Constants.PageTitles.UPDATE_CLIENT;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.UPDATE_CLIENT);
		return pageName;
	}
	
	public String navUpdateClientDetails() 
	{
		pageName = NavigationConstants.UPDATE_CLIENT_DETAILS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.UPDATE_CLIENT);
		return pageName;
	}
	
	public String navUploadScanned() {
		System.out.println("PageNavigationBean uploadScanned.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.UPOAD_CLIENT_DATA_NAVIGATION;
//		pageTitle = MessageConstants.Constants.PageTitles.UPLOAD_SCANNED;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.UPLOAD_SCANNED);
		return pageName;
	}
	
	public String navResetRepeater() 
	{		
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.RESET_REPEATER_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.RESET_REPEATER);
		return pageName;
	}
	
	public String navCashPayment() {
//		System.out.println("PageNavigationBean uploadScanned.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.CLIENT_PAYMENY_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.CASH_PAYMENT);
		return pageName;
	}
	
	public String navCashPaymentDetails() 
	{
		pageName = NavigationConstants.CLIENT_PAYMENY_DETAILS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.CASH_PAYMENT);
		return pageName;
	}
	
	public String navMedicalGpe() {
//		System.out.println("PageNavigationBean uploadScanned.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.MEDICAL_GPE_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.GPE_XRAY);
		return pageName;
	}
	
	public String navMedicalGpeDetails() {
//		System.out.println("PageNavigationBean uploadScanned.xhtml");
//		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.MEDICAL_GPE_DETAILS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.GPE_XRAY);
		return pageName;
	}

	
	public String navMedicalXray() {
//		System.out.println("PageNavigationBean uploadScanned.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.MEDICAL_XRAY_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.RADIOLOGY);
		return pageName;
	}
	
	public String navRadioXray() 
	{
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.RADIO_XRAY_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.RADIOLOGY);
		return pageName;
	}
	
	public String navRadioXrayDetails() 
	{
		pageName = NavigationConstants.RADIO_XRAY_DETAILS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.RADIOLOGY);
		return pageName;
	}

	
	public String navSample() {
//		System.out.println("PageNavigationBean uploadScanned.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.SAMPLE_DATA_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.SAMPLE_COLLECTION);
		return pageName;
	}

	public String navSampleDetails() {
		pageName = NavigationConstants.SAMPLE_DATA_DETAILS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.SAMPLE_COLLECTION);
		return pageName;
	}
	
	public String navLab() {
//		System.out.println("PageNavigationBean uploadScanned.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.LAB_DATA_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.LAB_MGMT);
		return pageName;
	}
	public String navLabDetails() 
	{
		pageName = NavigationConstants.LAB_DATA_DETAILS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.LAB_MGMT);
		return pageName;
	}
	
	public String navPathoStatusList() 
	{
//		System.out.println("PageNavigationBean uploadScanned.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.PATHO_STATUS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.ADV_SEARCH);
		return pageName;
	}
	
	public String navPathoVerifyLabResult() 
	{
//		System.out.println("PageNavigationBean uploadScanned.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.PATHO_VERIFY_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.PATHOLOGY);
		return pageName;
	}
	
	public String navPathoVerifyLabResultDetails() 
	{
		pageName = NavigationConstants.PATHO_VERIFY_DETAILS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.PATHOLOGY);
		return pageName;
	}
	
	public String navDirectorFinal() 
	{
//		System.out.println("PageNavigationBean uploadScanned.xhtml");
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.DIRECTOR_FINAL_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.DIRECTOR);
		return pageName;
	}
	
	public String navDirectorFinalDetails() 
	{
		pageName = NavigationConstants.DIRECTOR_FINAL_DETAILS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.DIRECTOR);
		return pageName;
	}
	
	public String navPrintReport() 
	{
		FacesUtils.resetManagedBean("registerClientBean");
		pageName = NavigationConstants.PRINT_REPORT_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.PRINT_REPORT);
		return pageName;
	}
	
	public String navAdvanceSearch() 
	{
		FacesUtils.resetManagedBean("advSearchBean");
		pageName = NavigationConstants.SEARCH_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.ADV_SEARCH);
		return pageName;
	}
	
	
	

	public String navAdminAddUsersPage() {
		System.out.println("PageNavigationBean admin/addUsers.xhtml");
		FacesUtils.resetManagedBean("adminBean");
		
		pageName = NavigationConstants.ADMIN_ADD_USERS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.ADMIN);
		return pageName;
	}
	
	public String navAdminManageUsersPage() {
		System.out.println("PageNavigationBean admin/manageUsers.xhtml");
		FacesUtils.resetManagedBean("adminBean");
		pageName = NavigationConstants.ADMIN_MANAGE_USERS_NAVIGATION;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.ADMIN);
		return pageName;
	}

	public String navAdminChangePassword() 
	{
		FacesUtils.resetManagedBean("adminBean");
		pageName = NavigationConstants.ADMIN_CHANGE_PASSWORD;
		cb.setPageTitle(MessageConstants.Constants.PageTitles.ADMIN);
		return pageName;
	}

	
	
	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName
	 *            the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

}
