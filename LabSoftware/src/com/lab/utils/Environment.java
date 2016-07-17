package com.lab.utils;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class Environment 
{
	public Environment() 
	{
		
	}
	
	private static ResourceBundle resources;
	
	static
	{
		loadResources();
	}
	
	private static void loadResources()
	{
		resources = ResourceBundle.getBundle( "bundle" );
	}
	
	public static String getApplName()
	{
		return getProperty("applName");
	}
	
	public static String getBarCodesStoragePath()
	{
		return getProperty("barCodesStoragePath");
	}
	
	
	public static String getBarCodesNameFormat()
	{
		return getProperty("barCodesNameFormat");
	}
	
	public static String getMedicalReportsStoragePath()
	{
		return getProperty("medicalReportsStoragePath");
	}
	
	public static String getReportsTemplatePath()
	{
		return getProperty("reportTemplatePath");
	}
	
	public static String getMedicalReportsNameFormat()
	{
		return getProperty("medicalReportsNameFormat");
	}
	
	public static String getCashReceiptNameFormat()
	{
		return getProperty("cashReceiptNameFormat");
	}
	
	public static String getFileMaxSize()
	{
		return getProperty("fileMaxSize");
	}
	
	public static String getCashReceiptsStoragePath()
	{
		return getProperty("cashReceiptsStoragePath");
	}
	
	public static String getScannedFilesStoragePath()
	{
		return getProperty("scannedFilesStoragePath");
	}
	public static String getMedicalReportTemplateFile()
	{
		return getProperty("medReportTemplateFile");
	}
	public static String getCashReceiptTemplateFile()
	{
		return getProperty("cashReceiptTemplateFile");
	}
	
	public static String getBarCodesTemplateFile()
	{
		return getProperty("barCodesTemplateFile");
	}
	
	public static String getProperty(String key) {
		String value = resources.getString(key);
		return value;
	}
	
	
	//only for testing
		private static void printResources( ResourceBundle resources )
		{
			Enumeration printEnum = resources.getKeys();
			while( printEnum.hasMoreElements() )
			{
				String key = printEnum.nextElement().toString();
				//Console.printlnPrompt( key + " : " + resources.getString(key) );
			}
		}

}
