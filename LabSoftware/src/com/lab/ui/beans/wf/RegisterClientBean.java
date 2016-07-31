package com.lab.ui.beans.wf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.model.UploadedFile;

import com.lab.bll.wf.RegisterClientBll;
import com.lab.dal.dao.WfClient;
import com.lab.dal.dao.WfClientFinance;
import com.lab.dal.dao.WfClientGpe;
import com.lab.dal.dao.WfClientProgress;
import com.lab.dal.dao.WfClientSamples;
import com.lab.dal.dao.WfClientScannedFiles;
import com.lab.dal.dao.WfClientXray;
import com.lab.dal.dao.WfLabResultBlood;
import com.lab.dal.dao.WfLabResultMicro;
import com.lab.dal.dao.WfLabResultSputum;
import com.lab.dal.dao.WfLabResultStool;
import com.lab.dal.dao.WfLabResultUrine;
import com.lab.ui.beans.FileUploadView;
import com.lab.utils.Environment;
import com.lab.utils.MessageConstants;
import com.lab.utils.MessageUtils;
import com.lab.utils.NavigationConstants;

@ManagedBean(name="registerClientBean")
@SessionScoped
public class RegisterClientBean 
{
	private RegisterClientBll bll;
	private WfClient toAddClient;
	private WfClient toSearchClient;
	private List<WfClient> clientsList;
	private WfClient selectedClient;
	private FileUploadView fileUpload;	
	private UploadedFile uploadedFile;
	
	private WfClientGpe toAddGpe;
	private int printCopies;
	
	
	
	
	public RegisterClientBean() 
	{
		// TODO Auto-generated constructor stub
		this.toAddClient = new WfClient();
		this.toSearchClient = new WfClient();
		fileUpload = new FileUploadView();
		this.selectedClient = new WfClient();
		if(this.toSearchClient.getCashPayment()==null || 
				this.toSearchClient.getCashPayment().getId()==null )
		{
			this.toSearchClient.setCashPayment(new WfClientFinance());
		}
		if(this.toSearchClient.getProgress() == null || 
				this.toSearchClient.getProgress().getId()==null)
		{
			this.toSearchClient.setProgress(new WfClientProgress());
		}
		
		if(this.toSearchClient.getXray() == null || 
				this.toSearchClient.getXray().getId()==null)
		{
			this.toSearchClient.setXray(new WfClientXray());
		}
		
		this.toAddGpe= new WfClientGpe();
		 
	}
	
	public String addCient()
	{
		System.out.println("in add client method");
		this.bll = new RegisterClientBll();
		if(bll.addCient(toAddClient))
		{
			this.toAddClient = new WfClient();
			
			
			MessageUtils.info("Data saved/updated successfully");
//			return NavigationConstants.HOME_NAVIGATION;
		}
		else
		{
			MessageUtils.error("Error saving data");
		}
		
		
		return "";
	}
	
	public String searchClients()
	{		
		RegisterClientBll bll =new RegisterClientBll();
		this.clientsList = bll.searchClients(toSearchClient);
		initializeNullObjs();
		return "";
	}
	
	public String uploadScannedFiles()
	{		
		RegisterClientBll bll =new RegisterClientBll();
		System.out.println("in upload scanned files");
		if(bll.uploadFiles(selectedClient, fileUpload))
		{
//			FacesUtils.addInfoMessage("Files uploaded successfully");
			MessageUtils.info(MessageConstants.Messages.FILE_UPLOAD_SUCCESS);
			this.selectedClient = new WfClient();
			this.fileUpload = new FileUploadView();
		}
		else
		{
//			FacesUtils.addErrorMessage("Error uploading files");
			MessageUtils.error(MessageConstants.Messages.FILE_UPLOAD_FAILURE);
		}
		
		return "";
	}
	
	public String resetRepeater()
	{		
		RegisterClientBll bll =new RegisterClientBll();
		System.out.println("in reset repeater method");
		
		
		if(bll.resetRepeaterList(clientsList))
		{
			MessageUtils.info("Reset Successfull");
			searchClients();
			
		}
		else
		{
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	
	public String updateCashStatus()
	{		
		RegisterClientBll bll =new RegisterClientBll();
		System.out.println("in update cash status method");
		
		/*
		 * Following updateList added on 23-jul-16
		 * Just to avoid amending already written code in bll that takes a list instead of a singe object
		 */		
		List<WfClient> updateList = new ArrayList<WfClient>();
		updateList.add(selectedClient);
		
		if(bll.updateCashStatus(updateList))
		{
			MessageUtils.info("Cash Payment Status updated successfully");
			searchClients();
			
		}
		else
		{
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	public String addGpe()
	{
		System.out.println("in add GPE method");
		bll =new RegisterClientBll();
		if(bll.addGpe(this.selectedClient))
		{
//			FacesUtils.addInfoMessage("GPE Details added successfully");
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.selectedClient = new WfClient();
			searchClients();
			return NavigationConstants.MEDICAL_GPE_NAVIGATION;
			
		}
		else
		{
//			FacesUtils.addErrorMessage("Error adding data");
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	public String updateXrayStatus()
	{		
		RegisterClientBll bll =new RegisterClientBll();
		System.out.println("in update xray status method");
		if(bll.updateXrayStatus(clientsList))
		{
//			FacesUtils.addInfoMessage("Xray Status updated successfully");
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
//			this.selectedClient = new WfClientRegister();
			searchClients();
			
		}
		else
		{
//			FacesUtils.addErrorMessage("Error updating data");
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	public String addSamples()
	{
		System.out.println("in add Samples method");
		bll =new RegisterClientBll();
		if(bll.addSamples(this.selectedClient))
		{
//			FacesUtils.addInfoMessage("Samples Details added successfully");
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.selectedClient = new WfClient();
			searchClients();
			return NavigationConstants.SAMPLE_DATA_NAVIGATION;
			
		}
		else
		{
//			FacesUtils.addErrorMessage("Error adding data");
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	public String saveRadiology()
	{
		System.out.println("in save radiology method");
		bll =new RegisterClientBll();
		if(bll.saveRadiology(this.selectedClient))
		{
//			FacesUtils.addInfoMessage("Samples Details added successfully");
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.selectedClient = new WfClient();
			searchClients();
			return NavigationConstants.RADIO_XRAY_NAVIGATION;
			
		}
		else
		{
//			FacesUtils.addErrorMessage("Error adding data");
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	public String addLabResults()
	{
		System.out.println("in add lab results method");
		bll =new RegisterClientBll();
		if(bll.addLabResults(this.selectedClient))
		{
//			FacesUtils.addInfoMessage("Lab Results added successfully");
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.selectedClient = new WfClient();
			searchClients();
			return NavigationConstants.LAB_DATA_NAVIGATION;
			
		}
		else
		{
//			FacesUtils.addErrorMessage("Error adding data");
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	public String verifyResults()
	{
		System.out.println("in verify results method");
		bll =new RegisterClientBll();
		if(bll.verifyResults(this.selectedClient))
		{
//			FacesUtils.addInfoMessage("Lab Results verified successfully");
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.selectedClient = new WfClient();
			searchClients();
			return NavigationConstants.PATHO_VERIFY_NAVIGATION;
			
		}
		else
		{
//			FacesUtils.addErrorMessage("Error verifying data");
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	public String repeatWithFreshSamples()
	{
		System.out.println("in repeat with fresh samples method");
		bll =new RegisterClientBll();
		if(bll.repeatWithFreshSamples(this.selectedClient))
		{
//			FacesUtils.addInfoMessage("Data saved successfully");
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.selectedClient = new WfClient();
			searchClients();
			return NavigationConstants.PATHO_VERIFY_NAVIGATION;
			
		}
		else
		{
//			FacesUtils.addErrorMessage("Error saving data");
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}

	
	public String declareFinalResults()
	{
		System.out.println("in final declare results method");
		bll =new RegisterClientBll();
		if(bll.declareFinalStatus(this.selectedClient))
		{
//			FacesUtils.addInfoMessage("Final Declaration endorsed successfully");
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.selectedClient = new WfClient();
			searchClients();
			return NavigationConstants.DIRECTOR_FINAL_NAVIGATION;
			
		}
		else
		{
//			FacesUtils.addErrorMessage("Error in making final declaration");
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}
		
		return "";
	}
	
	public String printMedicalReport()
	{
		System.out.println("in print medical report method");
		bll =new RegisterClientBll();
//		bll.printMedicalReport(selectedClient.getId());
		bll.printMedicalReportNew(selectedClient.getId());
		
		return "";
	}
	
	public String printCashReceipt()
	{
		System.out.println("in printCashReceipt method");
		bll =new RegisterClientBll();
		bll.printCashReceiptNew(selectedClient.getId());
		
		return "";
	}
	
	public String printBarCodes()
	{
		System.out.println("in printBarCodes method");
		
		bll =new RegisterClientBll();
//		bll.viewAndPrintBarCodes(selectedClient.getId());
		bll.printOnlyBarCodes(selectedClient.getId(),this.printCopies);
		
		return "";
	}
	
	public void printBarCodes(ActionEvent e)
	{
		System.out.println("in printBarCodes actionlistener");
		
		bll =new RegisterClientBll();
//		bll.viewAndPrintBarCodes(selectedClient.getId());
		bll.printOnlyBarCodes(selectedClient.getId(),this.printCopies);
		
	}

	public void initPrintCopies()
	{
		try
		{
			this.printCopies = Integer.valueOf(Environment.getPrintCopies());
		}
		catch(NumberFormatException e)
		{
			System.out.println(this.printCopies+" is not a valid number. Going to set its value as 1");
			this.printCopies = 1;
		}
	}
	
	public void addPrintCopiesNo(ActionEvent e)
	{
		System.out.println("No of copies ="+this.printCopies);
	}
	
	private void initializeNullObjs()
	{
		for(WfClient r:this.clientsList)
		{
			if(r.getCashPayment()==null || r.getCashPayment().getId()==null)
			{
				r.setCashPayment(new WfClientFinance());
			}
			if(r.getScannedFiles()==null || r.getScannedFiles().getId()==null)
			{
				r.setScannedFiles(new WfClientScannedFiles());
			}
			if(r.getGpe() == null || r.getGpe().getId()==null)
			{
				r.setGpe(new WfClientGpe());
			}
			if(r.getXray() == null || r.getXray().getId()==null)
			{
				r.setXray(new WfClientXray());
			}
			if(r.getSamples() == null || r.getSamples().getId() == null)
			{
				r.setSamples(new WfClientSamples());
			}
			if(r.getUrine() == null || r.getUrine().getId() == null)
			{
				r.setUrine(new WfLabResultUrine());
			}
			if(r.getMicro() == null || r.getMicro().getId() == null )
			{
				r.setMicro(new WfLabResultMicro());
			}
			if(r.getBlood() == null || r.getBlood().getId() == null )
			{
				r.setBlood(new WfLabResultBlood());
			}
			if(r.getSputum() == null || r.getSputum().getId() == null )
			{
				r.setSputum(new WfLabResultSputum());
			}
			if(r.getStool() == null || r.getStool().getId() == null )
			{
				r.setStool(new WfLabResultStool());
			}
			if(r.getProgress() == null || r.getProgress().getId()==null )
			{
				r.setProgress(new WfClientProgress());
			}
			
			

			
		}
		
	}
	
	
	public void stateChangeListener(ValueChangeEvent event) 
	{
		if(event.getNewValue()!=null && event.getNewValue().toString().trim().length()>0)
		{
			System.out.println(event.getNewValue().toString());
	        this.selectedClient.getBlood().setVdrl(event.getNewValue().toString());
		}
		else
		{
			System.out.println("No value selected");
		}
        
       
	}
	
	
//	Getters and Setters

	public WfClient getToAddClient() {
		return toAddClient;
	}

	public void setToAddClient(WfClient toAddClient) {
		this.toAddClient = toAddClient;
	}

	public List<WfClient> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<WfClient> clientsList) {
		this.clientsList = clientsList;
	}

	public FileUploadView getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUploadView fileUpload) {
		this.fileUpload = fileUpload;
	}

	public WfClient getToSearchClient() {
		return toSearchClient;
	}

	public void setToSearchClient(WfClient toSearchClient) {
		this.toSearchClient = toSearchClient;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public WfClient getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(WfClient selectedClient) {
		this.selectedClient = selectedClient;
	}

	public WfClientGpe getToAddGpe() {
		return toAddGpe;
	}

	public void setToAddGpe(WfClientGpe toAddGpe) {
		this.toAddGpe = toAddGpe;
	}

	public int getPrintCopies() {
		return printCopies;
	}

	public void setPrintCopies(int printCopies) {
		this.printCopies = printCopies;
	}
	

}
