package com.lab.ui.beans;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.lab.utils.HibernateUtilsAnnot;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class TestJasper 
{

	public static void main(String[] args) throws HibernateException, JRException, IOException 
	{
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		JasperReport jasperReport = JasperCompileManager
	               .compileReport("d:/reports/rdcMedicalReport.jrxml");
		// Parameters for report
	       Map<String, Object> parameters = new HashMap<String, Object>();
	       parameters.put("clientId", 3);
	 
	       // DataSource
	       // This is simple example, no database.
	       // then using empty datasource.
	       JRDataSource dataSource = new JREmptyDataSource();
	       
	       Session session = HibernateUtilsAnnot.currentSession();
	       Connection connection = session.connection();
//	       Connection connection = HibernateUtilsAnnot.getSessionFactory().openStatelessSession().connection();
	 
//	       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
	       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, connection);
	 
	    
	       // Make sure the output directory exists.
	       File outDir = new File("d:/jasperoutput");
	       outDir.mkdirs();
	 
	       // Export to PDF.
	       JasperExportManager.exportReportToPdfFile(jasperPrint,
	               "d:/jasperoutput/StyledTextReport.pdf");
	        
	       System.out.println("Done!");

	}

}
