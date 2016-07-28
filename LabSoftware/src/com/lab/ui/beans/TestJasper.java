package com.lab.ui.beans;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.krysalis.barcode4j.impl.code39.Code39Bean;

import com.lab.utils.HibernateUtilsAnnot;
import com.onbarcode.barcode.Code39;

import net.sf.jasperreports.components.barbecue.BarcodeProviders.Code39Provider;
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

	public static void main(String[] args) 
	{
		 try
         { 
             
			 System.out.println("test");
			 Code39 barcode = new Code39();
              
              barcode.setData("AC1605992");
              barcode.setX(2);
              barcode.setY(60);
              barcode.setBarcodeWidth(50);
              barcode.setBarcodeHeight(25);

              barcode.setLeftMargin(5);
              barcode.setRightMargin(0);
              barcode.setShowText(true);
              
              barcode.drawBarcode("d://bc2.png");

         }
         catch(Exception e)
         {
             e.printStackTrace();
         }

	}

}
