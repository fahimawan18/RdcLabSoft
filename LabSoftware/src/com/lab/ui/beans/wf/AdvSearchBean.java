package com.lab.ui.beans.wf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.lab.bll.wf.RegisterClientBll;
import com.lab.dal.dao.WfClientFinance;
import com.lab.dal.dao.WfClientGpe;
import com.lab.dal.dao.WfClientProgress;
import com.lab.dal.dao.WfClient;
import com.lab.dal.dao.WfClientSamples;
import com.lab.dal.dao.WfClientScannedFiles;
import com.lab.dal.dao.WfClientXray;
import com.lab.dal.dao.WfLabResultBlood;
import com.lab.dal.dao.WfLabResultMicro;
import com.lab.dal.dao.WfLabResultSputum;
import com.lab.dal.dao.WfLabResultStool;
import com.lab.dal.dao.WfLabResultUrine;


@ManagedBean(name="advSearchBean")
@SessionScoped
public class AdvSearchBean
{
	private WfClient toSearchClient;
	private List<WfClient> clientsList;
	
	public AdvSearchBean() 
	{
		// TODO Auto-generated constructor stub
		this.toSearchClient = new WfClient();
		this.clientsList = new ArrayList<WfClient>();
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
	}
	
	
	public String advacneSearchClients()
	{		
		RegisterClientBll bll =new RegisterClientBll();
		this.clientsList = bll.advSearchClients(toSearchClient);
		initializeNullObjs();
		return "";
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
	

	
//	Getters and Setters
	public WfClient getToSearchClient() {
		return toSearchClient;
	}

	public void setToSearchClient(WfClient toSearchClient) {
		this.toSearchClient = toSearchClient;
	}


	public List<WfClient> getClientsList() {
		return clientsList;
	}


	public void setClientsList(List<WfClient> clientsList) {
		this.clientsList = clientsList;
	}
	
	

}