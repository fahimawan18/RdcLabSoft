package com.lab.ui.beans.admin;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.lab.utils.Environment;
import com.lab.utils.MessageConstants;

@ManagedBean(name= "crit")
@SessionScoped
public class CriteriaBean 
{
	private List<SelectItem> twoOptionsList; //Yes, No
	private List<SelectItem> npOptionsList; // Nil, Present
	private List<SelectItem> ntpOptionsList; // Nil, trace, present
	private List<SelectItem> microOptionsList; //
	private List<SelectItem> npwOptionsList ; //Negative, positive, weak reactive
	private List<SelectItem> snsOptionsList ; //Seen, Not Seen
	private List<SelectItem> bloodGpList ;
	private List<SelectItem> negPosOptionsList ; //Negative, positive
	private List<SelectItem> gccCountryList;	// GCC countries
	private String maxFileSize;
	private String invalidFileSizeMessage; 
	
	private URL clsPath=getClass().getResource("..\\..\\..\\..\\..\\resources\\");
	private File medReportTemplateFile;
	private File cashReceiptTemplateFile;
	
	
	private String pageTitle;
	
	public CriteriaBean() 
	{
		// TODO Auto-generated constructor stub
		this.twoOptionsList = new ArrayList<SelectItem>();
		this.npOptionsList = new ArrayList<SelectItem>();
		this.ntpOptionsList = new ArrayList<SelectItem>();
		this.microOptionsList = new ArrayList<SelectItem>();
		this.npwOptionsList = new ArrayList<SelectItem>();
		this.snsOptionsList = new ArrayList<SelectItem>();
		this.bloodGpList = new ArrayList<SelectItem>();
		this.negPosOptionsList = new ArrayList<SelectItem>();
		this.gccCountryList = new ArrayList<SelectItem>();
		this.maxFileSize = Environment.getFileMaxSize();
	
//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource("resources/rdcCashReceipt.jrxml").getFile());
//        System.out.println("0909090  "+file.getAbsolutePath());
        
		medReportTemplateFile = new File(clsPath.getPath()+Environment.getMedicalReportTemplateFile());
		cashReceiptTemplateFile = new File(clsPath.getPath()+Environment.getCashReceiptTemplateFile());
	}
	
	
	

	public List<SelectItem> getTwoOptionsList() 
	{
		if(this.twoOptionsList.size()==0)
		{
			this.twoOptionsList.add(new SelectItem("",MessageConstants.Constants.SELECT_ONE_STRING));
			this.twoOptionsList.add(new SelectItem(MessageConstants.Constants.YES_STRING,MessageConstants.Constants.YES_STRING));
			this.twoOptionsList.add(new SelectItem(MessageConstants.Constants.NO_STRING,MessageConstants.Constants.NO_STRING));
		}
		return twoOptionsList;
	}

	public void setTwoOptionsList(List<SelectItem> twoOptionsList) {
		this.twoOptionsList = twoOptionsList;
	}




	public List<SelectItem> getNpOptionsList() 
	{
		if(this.npOptionsList.size()==0)
		{
			this.npOptionsList.add(new SelectItem("",MessageConstants.Constants.SELECT_ONE_STRING));
			this.npOptionsList.add(new SelectItem(MessageConstants.Constants.NIL_STRING,MessageConstants.Constants.NIL_STRING));
			this.npOptionsList.add(new SelectItem(MessageConstants.Constants.PRESENT_STRING,MessageConstants.Constants.PRESENT_STRING));
			
		}
		return npOptionsList;
	}




	public void setNpOptionsList(List<SelectItem> npOptionsList) {
		this.npOptionsList = npOptionsList;
	}




	public List<SelectItem> getNtpOptionsList() 
	{
		if(this.ntpOptionsList.size()==0)
		{
			this.ntpOptionsList.add(new SelectItem("",MessageConstants.Constants.SELECT_ONE_STRING));
			this.ntpOptionsList.add(new SelectItem(MessageConstants.Constants.NIL_STRING,MessageConstants.Constants.NIL_STRING));
			this.ntpOptionsList.add(new SelectItem(MessageConstants.Constants.TRACE_STRING,MessageConstants.Constants.TRACE_STRING));
			this.ntpOptionsList.add(new SelectItem(MessageConstants.Constants.PRESENT_STRING,MessageConstants.Constants.PRESENT_STRING));
		}
		return ntpOptionsList;
	}




	public void setNtpOptionsList(List<SelectItem> ntpOptionsList) {
		this.ntpOptionsList = ntpOptionsList;
	}




	public List<SelectItem> getMicroOptionsList() 
	{
		if(microOptionsList.size() ==0)
		{
			this.microOptionsList.add(new SelectItem("", MessageConstants.Constants.SELECT_ONE_STRING));
			this.microOptionsList.add(new SelectItem(MessageConstants.Constants.NIL_STRING, MessageConstants.Constants.NIL_STRING));
			this.microOptionsList.add(new SelectItem(MessageConstants.Constants.LabMicro.RANGE_1_2,MessageConstants.Constants.LabMicro.RANGE_1_2));
			this.microOptionsList.add(new SelectItem(MessageConstants.Constants.LabMicro.RANGE_3_5,MessageConstants.Constants.LabMicro.RANGE_3_5));
			this.microOptionsList.add(new SelectItem(MessageConstants.Constants.LabMicro.RANGE_5_10,MessageConstants.Constants.LabMicro.RANGE_5_10));
		}
		return microOptionsList;
	}




	public void setMicroOptionsList(List<SelectItem> microOptionsList) {
		this.microOptionsList = microOptionsList;
	}




	public List<SelectItem> getNpwOptionsList() 
	{
		if(this.npwOptionsList.size()==0)
		{
			this.npwOptionsList.add(new SelectItem("", MessageConstants.Constants.SELECT_ONE_STRING));
			this.npwOptionsList.add(new SelectItem(MessageConstants.Constants.NEGATIVE_STRING, MessageConstants.Constants.NEGATIVE_STRING));
			this.npwOptionsList.add(new SelectItem(MessageConstants.Constants.POSITIVE_STRING, MessageConstants.Constants.POSITIVE_STRING));
			this.npwOptionsList.add(new SelectItem(MessageConstants.Constants.WEAK_REACTIVE_STRING, MessageConstants.Constants.WEAK_REACTIVE_STRING));
			
			
		}
		return npwOptionsList;
	}




	public void setNpwOptionsList(List<SelectItem> npwOptionsList) {
		this.npwOptionsList = npwOptionsList;
	}




	public List<SelectItem> getSnsOptionsList() 
	{
		if(this.snsOptionsList.size()==0)
		{
			this.snsOptionsList.add(new SelectItem("", MessageConstants.Constants.SELECT_ONE_STRING));
			this.snsOptionsList.add(new SelectItem(MessageConstants.Constants.SEEN_STRING, MessageConstants.Constants.SEEN_STRING));
			this.snsOptionsList.add(new SelectItem(MessageConstants.Constants.NOT_SEEN_STRING, MessageConstants.Constants.NOT_SEEN_STRING));	
		}		
		return snsOptionsList;
	}




	public void setSnsOptionsList(List<SelectItem> snsOptionsList) {
		this.snsOptionsList = snsOptionsList;
	}




	public List<SelectItem> getBloodGpList() 
	{
		if(this.bloodGpList.size()==0)
		{
			this.bloodGpList.add(new SelectItem("", MessageConstants.Constants.SELECT_ONE_STRING));
			this.bloodGpList.add(new SelectItem(MessageConstants.Constants.BloodGps.A_POS,MessageConstants.Constants.BloodGps.A_POS));
			this.bloodGpList.add(new SelectItem(MessageConstants.Constants.BloodGps.B_POS,MessageConstants.Constants.BloodGps.B_POS));
			this.bloodGpList.add(new SelectItem(MessageConstants.Constants.BloodGps.AB_POS,MessageConstants.Constants.BloodGps.AB_POS));
			this.bloodGpList.add(new SelectItem(MessageConstants.Constants.BloodGps.O_POS,MessageConstants.Constants.BloodGps.O_POS));
			
			this.bloodGpList.add(new SelectItem(MessageConstants.Constants.BloodGps.A_NEG,MessageConstants.Constants.BloodGps.A_NEG));
			this.bloodGpList.add(new SelectItem(MessageConstants.Constants.BloodGps.B_NEG,MessageConstants.Constants.BloodGps.B_NEG));
			this.bloodGpList.add(new SelectItem(MessageConstants.Constants.BloodGps.AB_NEG,MessageConstants.Constants.BloodGps.AB_NEG));
			this.bloodGpList.add(new SelectItem(MessageConstants.Constants.BloodGps.O_NEG,MessageConstants.Constants.BloodGps.O_NEG));
			
		}
		return bloodGpList;
	}




	public void setBloodGpList(List<SelectItem> bloodGpList) {
		this.bloodGpList = bloodGpList;
	}




	public List<SelectItem> getNegPosOptionsList()
	{
		if(this.negPosOptionsList.size() == 0)
		{
			this.negPosOptionsList.add(new SelectItem("", MessageConstants.Constants.SELECT_ONE_STRING));
			this.negPosOptionsList.add(new SelectItem(MessageConstants.Constants.NEGATIVE_STRING, MessageConstants.Constants.NEGATIVE_STRING));
			this.negPosOptionsList.add(new SelectItem(MessageConstants.Constants.POSITIVE_STRING, MessageConstants.Constants.POSITIVE_STRING));
		}
		return negPosOptionsList;
	}




	public void setNegPosOptionsList(List<SelectItem> negPosOptionsList) {
		this.negPosOptionsList = negPosOptionsList;
	}




	public List<SelectItem> getGccCountryList() 
	{
		if(this.gccCountryList.size() ==0 )
		{
			
			this.gccCountryList.add(new SelectItem(MessageConstants.Constants.GCCCountries.BAHRAIN,MessageConstants.Constants.GCCCountries.BAHRAIN));
			this.gccCountryList.add(new SelectItem(MessageConstants.Constants.GCCCountries.QATAR,MessageConstants.Constants.GCCCountries.QATAR));
			this.gccCountryList.add(new SelectItem(MessageConstants.Constants.GCCCountries.KSA,MessageConstants.Constants.GCCCountries.KSA));
			this.gccCountryList.add(new SelectItem(MessageConstants.Constants.GCCCountries.OMAN,MessageConstants.Constants.GCCCountries.OMAN));
			this.gccCountryList.add(new SelectItem(MessageConstants.Constants.GCCCountries.KUWAIT,MessageConstants.Constants.GCCCountries.KUWAIT));
		}
		return gccCountryList;
	}




	public void setGccCountryList(List<SelectItem> countryList) {
		this.gccCountryList = countryList;
	}




	public File getMedReportTemplateFile() {
		return medReportTemplateFile;
	}




	public void setMedReportTemplateFile(File medReportTemplateFile) {
		this.medReportTemplateFile = medReportTemplateFile;
	}




	public File getCashReceiptTemplateFile() {
		return cashReceiptTemplateFile;
	}




	public void setCashReceiptTemplateFile(File cashReceiptTemplateFile) {
		this.cashReceiptTemplateFile = cashReceiptTemplateFile;
	}




	public String getPageTitle() {
		return pageTitle;
	}




	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}




	public String getMaxFileSize() 
	{
		if(maxFileSize==null || maxFileSize.trim().length()==0)
		{
			maxFileSize = Environment.getFileMaxSize();
		}
		return maxFileSize;
	}




	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}




	public String getInvalidFileSizeMessage() 
	{
		if(invalidFileSizeMessage==null || invalidFileSizeMessage.trim().length()==0)
		{
			int size=1;
			try
			{
				 size = Integer.valueOf(maxFileSize);
			}
			catch(NumberFormatException e)
			{
				size = 1;
			}
			this.invalidFileSizeMessage = MessageConstants.Messages.FILE_UPLOAD_SIZE+(size/(1024*1024))+" MB";
		}
		return invalidFileSizeMessage;
	}




	public void setInvalidFileSizeMessage(String invalidFileSizeMessage) {
		this.invalidFileSizeMessage = invalidFileSizeMessage;
	}
	
	

}
