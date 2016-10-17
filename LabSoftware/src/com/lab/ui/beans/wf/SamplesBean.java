package com.lab.ui.beans.wf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iac.web.util.FacesUtils;
import com.lab.bll.wf.RegisterClientBll;
import com.lab.bll.wf.SamplesBll;
import com.lab.dal.dao.WfClient;
import com.lab.dal.dao.WfClientProgress;
import com.lab.ui.beans.UserBean;
import com.lab.utils.HibernateUtilsAnnot;
import com.lab.utils.MessageConstants;
import com.lab.utils.MessageUtils;
import com.lab.utils.NavigationConstants;

@ManagedBean(name="samplesBean")
@SessionScoped
public class SamplesBean {
	private String printUrl;
	private String viewUrl;
	private WfClient selectedClient;
	private UserBean ub = ((UserBean)FacesUtils.getManagedBean("userBean"));
	
	public SamplesBean(){
		selectedClient = new WfClient();
	}
	
	public String getClientDetails() {
		
		
		
		this.setPrintUrl(this.getBarCodesPath());
//		cb.setPageTitle(MessageConstants.Constants.PageTitles.SAMPLE_COLLECTION);
		return NavigationConstants.SAMPLE_DATA_DETAILS_NAVIGATION;
	}
	
	
	public String getBarCodesPath()
	{
		System.out.println("***in printBarCodes method***");
		
		
		return new SamplesBll().getBarCodeUrls(selectedClient.getId());
		
		
	}
	
	public String addSamples()
	{
		System.out.println("in add client Samples bean method");
//		new SamplesBll().addSamples(toAdd, ub.getCurrentUser());
		
		if(validateData() == false)
		{
			MessageUtils.error(MessageConstants.Messages.MANDATORY_REQUIRED);
			
		}
		else if(new SamplesBll().addSamples(this.selectedClient, ub.getCurrentUser()))
		{
//			FacesUtils.addInfoMessage("Samples Details added successfully");
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.selectedClient = new WfClient();
			((RegisterClientBean)FacesUtils.getManagedBean("registerClientBean")).searchClients();
			return NavigationConstants.SAMPLE_DATA_NAVIGATION;
			
		}
		else
		{
//			FacesUtils.addErrorMessage("Error adding data");
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	private boolean validateData()
	{
		
		if(this.selectedClient.getSamples()==null )//|| this.selectedClient.getSamples().getId()==null 
			//|| this.selectedClient.getSamples().getId()<=0)
		{
			return false;
		}
		if(this.selectedClient.getSamples().getBloodSample()==null || this.selectedClient.getSamples().getBloodSample().trim().length()<=0)
		{
			return false;
		}
		if(this.selectedClient.getSamples().getUrineSample()==null || this.selectedClient.getSamples().getUrineSample().trim().length()<=0)
		{
			return false;
		}
		if(this.selectedClient.getSamples().getSputumSample()==null || this.selectedClient.getSamples().getSputumSample().trim().length()<=0)
		{
			return false;
		}
		if(this.selectedClient.getSamples().getStoolSample()==null || this.selectedClient.getSamples().getStoolSample().trim().length()<=0)
		{
			return false;
		}
		
		return true;
	}

	public String getPrintUrl() {
		return printUrl;
	}

	public void setPrintUrl(String printUrl) {
		this.printUrl = printUrl;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}


	public WfClient getSelectedClient() {
		return selectedClient;
	}


	public void setSelectedClient(WfClient selectedClient) {
		this.selectedClient = selectedClient;
	}

}
