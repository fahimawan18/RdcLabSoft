package com.lab.bll.wf;

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

import com.lab.dal.SamplesDal;
import com.lab.dal.dao.ApplicationUsers;
import com.lab.dal.dao.WfClient;
import com.lab.utils.Environment;
import com.lab.utils.HibernateUtilsAnnot;
import com.lab.utils.ViewScannedFilesUtils;

public class SamplesBll {
	
	public String getBarCodeUrls(Integer clientId)
	{
		System.out.println("in getBarCodeUrls bll method");
		
		String pdfPath = null;
		
		try
		{
			JasperPrint jasperPrint = new SamplesDal().getBarCodeReport(clientId);

		    File files = new File(Environment.getBarCodesStoragePath());
		    if(files.exists())
		    {
//		    	Do nothing
		    }
		    else
		    {
		    	files.mkdirs();
		    }
		    		 
//		    Export to PDF.
		    JasperExportManager.exportReportToPdfFile(jasperPrint,
		    		Environment.getBarCodesStoragePath()+clientId+
		    		Environment.getBarCodesNameFormat());
		    System.out.println("Done!");		
		    pdfPath = Environment.getBarCodePreviewPath() + clientId +
		    		Environment.getBarCodesNameFormat();
		    System.out.println("Done with path ..  " + pdfPath);
//		    Going to display generated report in another browser window
//		    ViewScannedFilesUtils fu = new ViewScannedFilesUtils();
//		    fu.viewScannedFile("application/pdf", Environment.getBarCodesStoragePath(), clientId+
//		    		Environment.getBarCodesNameFormat());
		    
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return pdfPath;
		
	}
	
	public boolean addSamples(WfClient toAdd, ApplicationUsers currentUser){
		return new SamplesDal().addSamples(toAdd, currentUser);
	}

}
