package com.lab.dal;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lab.dal.dao.ApplicationUsers;
import com.lab.dal.dao.WfClient;
import com.lab.dal.dao.WfClientProgress;
import com.lab.dal.dao.WfTrackReport;
import com.lab.utils.Environment;
import com.lab.utils.HibernateUtilsAnnot;
import com.lab.utils.MessageConstants;
import com.lab.utils.ViewScannedFilesUtils;

public class SamplesDal {
	
	
	public JasperPrint getBarCodeReport(Integer clientId)
	{
		System.out.println("in getBarCodeReport DAL method");
		JasperPrint jasperPrint = null;
		Session session = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			Connection connection = session.connection();
//			JasperReport jasperReport = JasperCompileManager.compileReport(cb.getCashReceiptTemplateFile().getPath());
			
			InputStream template = JasperReport.class
				    .getResourceAsStream(Environment.getReportsTemplatePath()+
				    		Environment.getBarCodesTemplateFile());// "/resources/rdcCashReceipt.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(template);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
		    parameters.put("clientId", clientId);
		    jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, connection);

//		    File files = new File(Environment.getBarCodesStoragePath());
//		    if(files.exists())
//		    {
////		    	Do nothing
//		    }
//		    else
//		    {
//		    	files.mkdirs();
//		    }
//		    		 
////		    Export to PDF.
//		    JasperExportManager.exportReportToPdfFile(jasperPrint,
//		    		Environment.getBarCodesStoragePath()+clientId+
//		    		Environment.getBarCodesNameFormat());
		    System.out.println("Done!");		
		    connection.close();
		    
//		    Going to display generated report in another browser window
//		    ViewScannedFilesUtils fu = new ViewScannedFilesUtils();
//		    fu.viewScannedFile("application/pdf", Environment.getBarCodesStoragePath(), clientId+
//		    		Environment.getBarCodesNameFormat());
		    
		}
		catch(JRException e)
		{
			e.printStackTrace();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		return jasperPrint;
		
	}
	
	public boolean addSamples(WfClient toAdd, ApplicationUsers currentUser)
	{
		System.out.println("in add client Samples dal method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			if(toAdd.getSamples().getId()==null
					|| toAdd.getSamples().getId()<1)
			{
				System.out.println("saving new Samples ");
				toAdd.getSamples().setClientId(toAdd);
				session.save(toAdd.getSamples());				
			}
			else
			{
				System.out.println("updating Samples ");
				session.update(toAdd.getSamples());
			}
			
			if(toAdd.getProgress() == null || toAdd.getProgress().getId()==null)
			{
				WfClientProgress progress = new WfClientProgress();
				progress.setClientId(toAdd);
				progress.setSample(MessageConstants.Constants.YES_STRING);
				session.save(progress);					
			}
			else
			{
				toAdd.getProgress().setSample(MessageConstants.Constants.YES_STRING);
				session.update(toAdd.getProgress());
			}	
			
			saveTrackReport(MessageConstants.Constants.TrackActions.SAMPLES, toAdd, session, currentUser);
						
			tx.commit();
					
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			tx.rollback();
			flag = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			flag = false;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		
		return flag;
	}
	
	public void saveTrackReport(String activity, WfClient client, Session session, ApplicationUsers currentUser)throws HibernateException
	{
		WfTrackReport report = new WfTrackReport();
		report.setOperator(currentUser);
		report.setActivity(activity);
		report.setClientId(client);
		
		session.save(report);
	}

}
