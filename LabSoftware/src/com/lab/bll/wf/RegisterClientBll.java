package com.lab.bll.wf;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.persistence.JoinTable;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Join;
import org.primefaces.model.UploadedFile;

import com.iac.web.util.FacesUtils;
import com.lab.dal.dao.ApplicationUsers;
import com.lab.dal.dao.WfClientFinance;
import com.lab.dal.dao.WfClientGpe;
import com.lab.dal.dao.WfClientProgress;
import com.lab.dal.dao.WfClient;
import com.lab.dal.dao.WfClientScannedFiles;
import com.lab.dal.dao.WfClientXray;
import com.lab.dal.dao.WfTrackReport;
import com.lab.ui.beans.FileUploadView;
import com.lab.ui.beans.UserBean;
import com.lab.ui.beans.admin.AdminBean;
import com.lab.ui.beans.admin.CriteriaBean;
import com.lab.utils.Environment;
import com.lab.utils.HibernateUtilsAnnot;
import com.lab.utils.MessageConstants;
import com.lab.utils.ViewScannedFilesUtils;


public class RegisterClientBll 
{	
	private UserBean ub = ((UserBean)FacesUtils.getManagedBean("userBean"));
	private CriteriaBean cb = ((CriteriaBean)FacesUtils.getManagedBean("crit"));
	
	public RegisterClientBll() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public boolean addCient(WfClient toAdd)
	{
		System.out.println("in add client bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			ApplicationUsers currentUser = new ApplicationUsers(); 
			currentUser = ub.getCurrentUser();
			
			if(toAdd.getId() == null || toAdd.getId()<1)
			{
				toAdd.setClientStatus(MessageConstants.Constants.ClientStatus.REGISTERED);				
				toAdd.setInsertBy(currentUser);				
				session.save(toAdd);
				
//				Adding null scanned files data 
				WfClientScannedFiles scannedFilesObj = new WfClientScannedFiles();
				scannedFilesObj.setClientId(toAdd);
				session.save(scannedFilesObj);
				
//				Adding cash payment data
				WfClientFinance fin = new WfClientFinance();
				fin.setClientId(toAdd);
				session.save(fin);
				
//				Adding progress status of regn
				WfClientProgress progress = new WfClientProgress();
				progress.setClientId(toAdd);
				progress.setRegn(MessageConstants.Constants.YES_STRING);
				session.save(progress);
				
//				Adding track report
				saveTrackReport(MessageConstants.Constants.TrackActions.REGN_SAVED, toAdd, session);
				
			}
			else if(toAdd.getId() != null && toAdd.getId()>=1)
			{
//				Adding track report
				saveTrackReport(MessageConstants.Constants.TrackActions.REGN_UPDATED, toAdd, session);
				
				
				toAdd.setUpdateBy(currentUser);
				toAdd.setUpdateDate(new Date());
				session.update(toAdd);
				
			}
			
			
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

	public List<WfClient> searchClients(WfClient toSearchClient)
	{
		Session session = null;
		List<WfClient> list = new ArrayList<WfClient>();
		System.out.println("In search client Method bll");
		try
		{
			session = HibernateUtilsAnnot.currentSession();			
			Criteria cr = session.createCriteria(WfClient.class);
			if(toSearchClient!=null)
			{
				if(toSearchClient.getId()!=null && toSearchClient.getId()>0)
				{
					cr.add(Restrictions.eq("id", toSearchClient.getId()));
				}
				if(toSearchClient.getRdcTokenNo()!=null && toSearchClient.getRdcTokenNo().trim().length()>0)
				{
					cr.add(Restrictions.ilike("rdcTokenNo", toSearchClient.getRdcTokenNo()));
				}
				if(toSearchClient.getGamcaSlipNo()!=null && toSearchClient.getGamcaSlipNo().trim().length()>0)
				{
					cr.add(Restrictions.ilike("gamcaSlipNo", toSearchClient.getGamcaSlipNo()));
				}
				if(toSearchClient.getCnicNo()!=null && toSearchClient.getCnicNo().trim().length()>0)
				{
					cr.add(Restrictions.eq("cnicNo", toSearchClient.getCnicNo()));
				}
				if(toSearchClient.getPassportNo()!=null && toSearchClient.getPassportNo().trim().length()>0)
				{
					cr.add(Restrictions.ilike("passportNo", toSearchClient.getPassportNo()));
				}
				
				if(toSearchClient.getClientStatus()!=null && 
						toSearchClient.getClientStatus().contains(MessageConstants.Constants.ClientStatus.REPEATER))
				{
					cr.add(Restrictions.ilike("clientStatus", MessageConstants.Constants.ClientStatus.REPEATER+"%"));
				}
				else if(toSearchClient.getClientStatus()!=null && toSearchClient.getClientStatus().trim().length()>0)
				{
					cr.add(Restrictions.eq("clientStatus", toSearchClient.getClientStatus()));
				}
				else
				{
					
					cr.add(Restrictions.disjunction()
//	                        .add(Restrictions.ilike("clientStatus", MessageConstants.Constants.ClientStatus.REPEATER+"%"))
	                        .add(Restrictions.eq("clientStatus", MessageConstants.Constants.ClientStatus.REGISTERED))
	                        );
				}
				if(toSearchClient.getCashPayment()!=null 
						)
				{
					Criteria cr2 = cr.createCriteria("cashPayment",Criteria.LEFT_JOIN);
					
					if(toSearchClient.getCashPayment().getId()!=null && 
							toSearchClient.getCashPayment().getId()>0)
					{
						cr2.add(Restrictions.eq("id", toSearchClient.getCashPayment().getId()));
						
					}
					if(toSearchClient.getCashPayment().getCashPaidStatus()!=null &&
							toSearchClient.getCashPayment().getCashPaidStatus().trim().length()>0)
					{
						if(toSearchClient.getCashPayment().getCashPaidStatus().equals(MessageConstants.Constants.CashPaymentStatus.PAID))
						{
							cr2.add(Restrictions.like("cashPaidStatus", MessageConstants.Constants.CashPaymentStatus.PAID));
							cr2.add(Restrictions.isNotNull("cashAmount"));
						}
						else
						{
							cr2.add(Restrictions.like("cashPaidStatus", MessageConstants.Constants.CashPaymentStatus.UNPAID));
						}
						
		                        
					}
					
				}
				
				if(toSearchClient.getProgress()!=null 
						)
				{
					Criteria cr2 = cr.createCriteria("progress",Criteria.LEFT_JOIN);
					
					if(toSearchClient.getProgress().getId()!=null && 
							toSearchClient.getProgress().getId()>0)
					{
						cr2.add(Restrictions.eq("id", toSearchClient.getProgress().getId()));
					}
					if(toSearchClient.getProgress().getPathologist()!=null &&
							toSearchClient.getProgress().getPathologist().trim().length()>0)
					{
						cr2.add(Restrictions.ilike("pathologist", toSearchClient.getProgress().getPathologist()+"%"));
					}
					
				}
			}
			list = cr.list();
			
			for(WfClient c:list)
			{
				Hibernate.initialize(c.getTrackReport());
			}
	
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		return list;
	}
	
	public boolean uploadFiles(WfClient toUpdate, FileUploadView fileView)
	{
		System.out.println("in upload gamca bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		AdminBean adb = (AdminBean)FacesUtils.getManagedBean("adminBean");
		
		File files = new File(Environment.getScannedFilesStoragePath());
	    if(!files.exists())
	    {
	    	files.mkdirs();
	    }		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			ApplicationUsers currentUser = new ApplicationUsers(); 
			currentUser = ub.getCurrentUser();
			toUpdate.setUpdateBy(currentUser);
			toUpdate.setUpdateDate(new Date());
			String path = Environment.getScannedFilesStoragePath();
			String fileName="";
			
			if(toUpdate.getScannedFiles()!=null && toUpdate.getScannedFiles().getId()>0)
			{
				if(fileView.getFileBinary1()!=null)
				{			
					
					toUpdate.getScannedFiles().setScannedGamca(fileView.getFileBinary1());
					toUpdate.getScannedFiles().setGamcaMime(fileView.getFile1Mime());
					
					fileName=toUpdate.getId().toString()+Environment.getGamcaNameFormat()+fileView.getFile1Mime();
					adb.makeFileFromByte(path+fileName, fileView.getFileBinary1());
				}
				if(fileView.getFileBinary2()!=null)
				{
					toUpdate.getScannedFiles().setScannedPassport(fileView.getFileBinary2());
					toUpdate.getScannedFiles().setPassportMime(fileView.getFile2Mime());
					fileName=toUpdate.getId().toString()+Environment.getPassportNameFormat()+fileView.getFile2Mime();
					adb.makeFileFromByte(path+fileName, fileView.getFileBinary2());
				}
				if(fileView.getFileBinary3()!=null)
				{
					toUpdate.getScannedFiles().setScannedPhoto(fileView.getFileBinary3());
					toUpdate.getScannedFiles().setPhotoMime(fileView.getFile3Mime());
					fileName=toUpdate.getId().toString()+Environment.getPhotoNameFormat()+fileView.getFile3Mime();
					adb.makeFileFromByte(path+fileName, fileView.getFileBinary3());
				}
			}
			else
			{
				WfClientScannedFiles scannedFilesObj = new WfClientScannedFiles();
				scannedFilesObj.setClientId(toUpdate);
				if(fileView.getFileBinary1()!=null)
				{
					scannedFilesObj.setScannedGamca(fileView.getFileBinary1());
					scannedFilesObj.setGamcaMime(fileView.getFile1Mime());
					fileName = fileName+Environment.getGamcaNameFormat()+fileView.getFile1Mime();
					adb.makeFileFromByte(path+fileName, fileView.getFileBinary1());
				}
				if(fileView.getFileBinary2()!=null)
				{
					scannedFilesObj.setScannedPassport(fileView.getFileBinary2());
					scannedFilesObj.setPassportMime(fileView.getFile2Mime());
					fileName = fileName+Environment.getPassportNameFormat()+fileView.getFile2Mime();
					adb.makeFileFromByte(path+fileName, fileView.getFileBinary2());
				}
				if(fileView.getFileBinary3()!=null)
				{
					scannedFilesObj.setScannedPhoto(fileView.getFileBinary3());
					scannedFilesObj.setPhotoMime(fileView.getFile3Mime());
					fileName = fileName+Environment.getPhotoNameFormat()+fileView.getFile3Mime();
					adb.makeFileFromByte(path+fileName, fileView.getFileBinary3());
				}
				session.save(scannedFilesObj);
				
			}
			
			session.update(toUpdate);
			saveTrackReport(MessageConstants.Constants.TrackActions.SCANNED_UPDATED, toUpdate, session);
			
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
	
	public boolean resetRepeaterList(List<WfClient> toUpdateList)
	{
		System.out.println("in update cash bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			ApplicationUsers currentUser = new ApplicationUsers(); 
			currentUser = ub.getCurrentUser();
			for(WfClient toUpdate:toUpdateList)
			{
				
				toUpdate.setUpdateBy(currentUser);
				toUpdate.setUpdateDate(new Date());
				toUpdate.setClientStatus(MessageConstants.Constants.ClientStatus.REGISTERED);
				toUpdate.setFinalDeclaredBy(null);
				toUpdate.setFinalDeclaredDate(null);
								
				initCashObj(toUpdate.getCashPayment(), session);
				
				session.update(toUpdate);
				
				
				
				
				saveTrackReport(MessageConstants.Constants.TrackActions.RESET_REPEATER, toUpdate, session);
			}
			
			
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
	
	public boolean updateCashStatus(List<WfClient> toUpdateList)
	{
		System.out.println("in update cash bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			ApplicationUsers currentUser = new ApplicationUsers(); 
			currentUser = ub.getCurrentUser();
			for(WfClient toUpdate:toUpdateList)
			{
				toUpdate.setUpdateBy(currentUser);
				toUpdate.setUpdateDate(new Date());
				if(toUpdate.getCashPayment()==null || toUpdate.getCashPayment().getId()==null
						|| toUpdate.getCashPayment().getId()<1)
				{
					WfClientFinance finObj = new WfClientFinance();
					finObj.setClientId(toUpdate);
					finObj.setCashAmount(toUpdate.getCashPayment().getCashAmount());
					finObj.setCashPaidStatus(toUpdate.getCashPayment().getCashPaidStatus());
					finObj.setCashPaidDate(new Date());
					session.save(finObj);
				}
				else
				{			
					toUpdate.getCashPayment().setCashPaidDate(new Date());
					session.update(toUpdate.getCashPayment());
				}
				
				if(toUpdate.getProgress() == null || toUpdate.getProgress().getId()==null)
				{
					WfClientProgress progress = new WfClientProgress();
					progress.setClientId(toUpdate);
					progress.setCash(toUpdate.getCashPayment().getCashPaidStatus());
					session.save(progress);					
				}
				else
				{
					toUpdate.getProgress().setCash(toUpdate.getCashPayment().getCashPaidStatus());
					session.update(toUpdate.getProgress());
				}
				
				saveTrackReport(MessageConstants.Constants.TrackActions.CASH, toUpdate, session);
			}
			
			
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
	
	public boolean addGpe(WfClient toAdd)
	{
		System.out.println("in add client GPE bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			if(toAdd.getGpe().getId()==null
					|| toAdd.getGpe().getId()<1)
			{
				System.out.println("saving new GPE ");
				toAdd.getGpe().setClientId(toAdd);
				session.save(toAdd.getGpe());				
			}
			else
			{
				System.out.println("updating GPE ");
				session.update(toAdd.getGpe());
			}
			
			if(toAdd.getProgress() == null || toAdd.getProgress().getId()==null)
			{
				WfClientProgress progress = new WfClientProgress();
				progress.setClientId(toAdd);
				progress.setGpe(toAdd.getGpe().getMedicalStatus());
				session.save(progress);					
			}
			else
			{
				toAdd.getProgress().setGpe(toAdd.getGpe().getMedicalStatus());
				session.update(toAdd.getProgress());
			}		
			
			saveTrackReport(MessageConstants.Constants.TrackActions.GPE, toAdd, session);			
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
	
	public boolean updateXrayStatus(List<WfClient> toUpdateList)
	{
		System.out.println("in update xray bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			for(WfClient toUpdate:toUpdateList)
			{
				if(toUpdate.getXray()==null || toUpdate.getXray().getId()==null)
//						|| toUpdate.getXray().getId()<1)

				{
					WfClientXray xrayObj = new WfClientXray();
					xrayObj.setClientId(toUpdate);
					xrayObj.setXrayStatus(toUpdate.getXray().getXrayStatus());
					session.save(xrayObj);
				}
				else
				{
					session.update(toUpdate.getXray());
				}
				
				if(toUpdate.getProgress() == null || toUpdate.getProgress().getId()==null)
				{
					WfClientProgress progress = new WfClientProgress();
					progress.setClientId(toUpdate);
					progress.setXray(toUpdate.getXray().getXrayStatus());
					session.save(progress);					
				}
				else
				{
					toUpdate.getProgress().setXray(toUpdate.getXray().getXrayStatus());
					session.update(toUpdate.getProgress());
				}
				
				saveTrackReport(MessageConstants.Constants.TrackActions.XRAY, toUpdate, session);
				
			}
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
	
	public boolean saveRadiology(WfClient toUpdate)
	{
		System.out.println("in saveRadiology bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			if(toUpdate.getXray().getId()==null
					|| toUpdate.getXray().getId()<1)
			{
				System.out.println("saving new Radiology");
				toUpdate.getXray().setClientId(toUpdate);
				session.save(toUpdate.getXray());				
			}
			else
			{
				System.out.println("updating Radiology ");
				session.update(toUpdate.getXray());
			}
			
			saveTrackReport(MessageConstants.Constants.TrackActions.RADIOLOGY, toUpdate, session);
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
	
	
	public boolean addSamples(WfClient toAdd)
	{
		System.out.println("in add client Samples bll method");
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
			
			saveTrackReport(MessageConstants.Constants.TrackActions.SAMPLES, toAdd, session);
						
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
	
	
	
	public boolean addLabResults(WfClient toAdd)
	{
		System.out.println("in add client Lab Results bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			if(toAdd.getUrine().getId()==null
					|| toAdd.getUrine().getId()<1)
			{
				
				toAdd.getUrine().setClientId(toAdd);
				session.save(toAdd.getUrine());				
			}
			else
			{
				System.out.println("updating lab results (urine r/e");
				session.update(toAdd.getUrine());
			}
			
			if(toAdd.getMicro().getId()==null
					|| toAdd.getMicro().getId()<1)
			{
				
				toAdd.getMicro().setClientId(toAdd);
				session.save(toAdd.getMicro());
			}
			else
			{
				System.out.println("updating lab results (micro");
				session.update(toAdd.getMicro());
			}
			
			if(toAdd.getBlood().getId()==null
					|| toAdd.getBlood().getId()<1)
			{
				
				toAdd.getBlood().setClientId(toAdd);
				session.save(toAdd.getBlood());
			}
			else
			{
				System.out.println("updating lab results (blood");
				session.update(toAdd.getBlood());
			}
			
			if(toAdd.getSputum().getId()==null
					|| toAdd.getSputum().getId()<1)
			{
				
				toAdd.getSputum().setClientId(toAdd);
				session.save(toAdd.getSputum());
			}
			else
			{
				System.out.println("updating lab results (afb");
				session.update(toAdd.getSputum());
			}
			
			if(toAdd.getStool().getId()==null
					|| toAdd.getStool().getId()<1)
			{
				
				toAdd.getStool().setClientId(toAdd);
				session.save(toAdd.getStool());
			}
			else
			{
				System.out.println("updating lab results (stool)");
				session.update(toAdd.getStool());
			}
					
			saveTrackReport(MessageConstants.Constants.TrackActions.LAB, toAdd, session);
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
	
	public boolean verifyResults(WfClient toAdd)
	{
		System.out.println("in verify Results bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			if(toAdd.getProgress() == null || toAdd.getProgress().getId()==null)
			{
				WfClientProgress progress = new WfClientProgress();
				progress.setClientId(toAdd);
				progress.setPathologist(MessageConstants.Constants.VERIFIED_STRING);
				session.save(progress);					
			}
			else
			{
				toAdd.getProgress().setPathologist(MessageConstants.Constants.VERIFIED_STRING);
				session.update(toAdd.getProgress());
			}	
			
			saveTrackReport(MessageConstants.Constants.TrackActions.PATHOLOGIST, toAdd, session);			
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
	
	public boolean repeatWithFreshSamples(WfClient toAdd)
	{
		System.out.println("in repaet with fresh samples bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			if(toAdd.getProgress() == null || toAdd.getProgress().getId()==null)
			{
				WfClientProgress progress = new WfClientProgress();
				progress.setClientId(toAdd);
				progress.setPathologist(toAdd.getClientStatus());
				session.save(progress);					
			}
			else
			{
				toAdd.getProgress().setPathologist(toAdd.getClientStatus());
				session.update(toAdd.getProgress());
			}	
			saveTrackReport(MessageConstants.Constants.TrackActions.PATHOLOGIST, toAdd, session);
						
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
	
	public boolean declareFinalStatus(WfClient toAdd)
	{
		System.out.println("in declare final status bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			ApplicationUsers currentUser = new ApplicationUsers(); 
			currentUser = ub.getCurrentUser();
			
			toAdd.setFinalDeclaredBy(currentUser);
			toAdd.setFinalDeclaredDate(new Date());
			
			session.update(toAdd);
				
			saveTrackReport(MessageConstants.Constants.TrackActions.DIRECTOR, toAdd, session);
						
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
	
	
	public List<WfClient> advSearchClients(WfClient toSearchClient)
	{
		Session session = null;
		List<WfClient> list = new ArrayList<WfClient>();
		System.out.println("In search client Method bll");
		try
		{
			session = HibernateUtilsAnnot.currentSession();			
			Criteria cr = session.createCriteria(WfClient.class);
			if(toSearchClient!=null)
			{
				if(toSearchClient.getId()!=null && toSearchClient.getId()>0)
				{
					cr.add(Restrictions.eq("id", toSearchClient.getId()));
				}
				if(toSearchClient.getRdcTokenNo()!=null && toSearchClient.getRdcTokenNo().trim().length()>0)
				{
					cr.add(Restrictions.ilike("rdcTokenNo", toSearchClient.getRdcTokenNo()));
				}
				if(toSearchClient.getGamcaSlipNo()!=null && toSearchClient.getGamcaSlipNo().trim().length()>0)
				{
					cr.add(Restrictions.ilike("gamcaSlipNo", toSearchClient.getGamcaSlipNo()));
				}
				if(toSearchClient.getCnicNo()!=null && toSearchClient.getCnicNo().trim().length()>0)
				{
					cr.add(Restrictions.eq("cnicNo", toSearchClient.getCnicNo()));
				}
				if(toSearchClient.getPassportNo()!=null && toSearchClient.getPassportNo().trim().length()>0)
				{
					cr.add(Restrictions.eq("passportNo", toSearchClient.getPassportNo()));
				}
				if(toSearchClient.getAppliedForCountry()!=null && toSearchClient.getAppliedForCountry().trim().length()>0)
				{
					cr.add(Restrictions.eq("appliedForCountry", toSearchClient.getAppliedForCountry()));
				}
				if(toSearchClient.getClientStatus()!=null && toSearchClient.getClientStatus().trim().length()>0)
				{
					cr.add(Restrictions.ilike("clientStatus", toSearchClient.getClientStatus()+"%"));
				}
//				else
//				{
//					
//					cr.add(Restrictions.disjunction()
//	                        .add(Restrictions.eq("clientStatus", MessageConstants.Constants.ClientStatus.REPEATER))
//	                        .add(Restrictions.eq("clientStatus", MessageConstants.Constants.ClientStatus.REGISTERED))
//	                        );
//				}
				if(toSearchClient.getCashPayment()!=null 
						)
				{
					Criteria cr2 = cr.createCriteria("cashPayment",Criteria.LEFT_JOIN);
					
					if(toSearchClient.getCashPayment().getId()!=null && 
							toSearchClient.getCashPayment().getId()>0)
					{
						cr2.add(Restrictions.eq("id", toSearchClient.getCashPayment().getId()));						
					}
					if(toSearchClient.getCashPayment().getCashPaidStatus()!=null &&
							toSearchClient.getCashPayment().getCashPaidStatus().trim().length()>0)
					{
						cr2.add(Restrictions.like("cashPaidStatus", toSearchClient.getCashPayment().getCashPaidStatus()));
		                        
					}
					
				}
				
				if(toSearchClient.getProgress()!=null 
						)
				{
					Criteria cr2 = cr.createCriteria("progress",Criteria.LEFT_JOIN);				
					
					if(toSearchClient.getProgress().getPathologist()!=null &&
							toSearchClient.getProgress().getPathologist().trim().length()>0)
					{
						cr2.add(Restrictions.ilike("pathologist", toSearchClient.getProgress().getPathologist()+"%"));
					}
					
				}
			}
			list = cr.list();
	
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		return list;
	}
	
	public void printMedicalReportNew(Integer clientId)
	{
		System.out.println("in print medical report bll method");
		boolean flag = true;
		
		Session session = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			Connection connection = session.connection();
//			JasperReport jasperReport = JasperCompileManager.compileReport("d:/reports/rdcMedicalReport.jrxml");
//			JasperReport jasperReport = JasperCompileManager.compileReport("/resources/reportTemplates/rdcMedicalReport.jrxml");
			
			InputStream template = JasperReport.class
				    .getResourceAsStream(Environment.getReportsTemplatePath()+Environment.getMedicalReportTemplateFile());//"/resources/rdcMedicalReport.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(template);
			
			BufferedImage image = ImageIO.read(getClass().getResource(Environment.getReportIconFile()));
			
			Date referenceDate = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(referenceDate); 
			c.add(Calendar.MONTH, +3);
			
			
			Map<String, Object> parameters = new HashMap<String, Object>();
		    parameters.put("clientId", clientId);
		    parameters.put("imgLogo", image );
		    parameters.put("expiryDate", c.getTime());
		    
		     
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, connection);
//		    JasperViewer.viewReport(jasperPrint);
		    
		    File files = new File(Environment.getMedicalReportsStoragePath());
		    if(files.exists())
		    {
		    	
		    }
		    else
		    {
		    	files.mkdirs();
		    }
		    
		    
		   
//		    Export to PDF.
		    JasperExportManager.exportReportToPdfFile(jasperPrint,
		    		Environment.getMedicalReportsStoragePath()+clientId+
		    		Environment.getMedicalReportsNameFormat());
		    
		    System.out.println("Done!");		
		    connection.close();
		    
//		    Going to display generated report in another browser window
		    ViewScannedFilesUtils fu = new ViewScannedFilesUtils();
		    fu.viewScannedFile("application/pdf", Environment.getMedicalReportsStoragePath(), clientId+
		    		Environment.getMedicalReportsNameFormat());
		    
		}
		catch(JRException e)
		{
			e.printStackTrace();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			flag = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			flag = false;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
	}
	
	public void printCashReceiptNew(Integer clientId)
	{
		System.out.println("in printCahReceipt bll method");
		
		Session session = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			Connection connection = session.connection();
//			JasperReport jasperReport = JasperCompileManager.compileReport(cb.getCashReceiptTemplateFile().getPath());
			
			InputStream template = JasperReport.class
				    .getResourceAsStream(Environment.getReportsTemplatePath()+
				    		Environment.getCashReceiptTemplateFile());// "/resources/rdcCashReceipt.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(template);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
		    parameters.put("clientId", clientId);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, connection);

		    File files = new File(Environment.getCashReceiptsStoragePath());
		    if(files.exists())
		    {
		    	
		    }
		    else
		    {
		    	files.mkdirs();
		    }
		    		 
//		    Export to PDF.
		    JasperExportManager.exportReportToPdfFile(jasperPrint,
		    		Environment.getCashReceiptsStoragePath()+clientId+
		    		Environment.getCashReceiptNameFormat());
		    System.out.println("Done!");		
		    connection.close();
		    
//		    Going to display generated report in another browser window
		    ViewScannedFilesUtils fu = new ViewScannedFilesUtils();
		    fu.viewScannedFile("application/pdf", Environment.getCashReceiptsStoragePath(), clientId+
		    		Environment.getCashReceiptNameFormat());
		    
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
		
	}
	
	public void printOnlyBarCodes(Integer clientId)
	{
		System.out.println("in printOnlyBarCodes bll method");
		
		Session session = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			Connection connection = session.connection();
//			JasperReport jasperReport = JasperCompileManager.compileReport(cb.getCashReceiptTemplateFile().getPath());
			
			InputStream template = JasperReport.class
				    .getResourceAsStream(Environment.getReportsTemplatePath()+
				    		Environment.getBarCodesTemplateFile());
			
			Map<String, Object> parameters = new HashMap<String, Object>();
		    parameters.put("clientId", clientId);
		    
			JasperReport jasperReport = JasperCompileManager.compileReport(template);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);
			PrinterJob job = PrinterJob.getPrinterJob();
			PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
			int selectedService = 0;
			for(int i = 0; i < services.length;i++)
		    {
				
//		        if(services[i].getName().toUpperCase().contains(Environment.getBarCodePrinterName()))
				if(services[i].getName().contains(Environment.getBarCodePrinterName()))
		        {
		            /*If the service is named as what we are querying we select it */
					System.out.println(services[i].getName());
		                 selectedService = i;
		        }
		    }
			
			
			job.setPrintService(services[selectedService]);
		    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//		    MediaSizeName mediaSizeName = MediaSize.findMedia(2,1,Size2DSyntax.INCH);
		    
		    System.out.println("Closet media size is ="+MediaSize.findMedia(2,1,MediaPrintableArea.INCH));
		    System.out.println("Closet media size is ="+MediaSize.findMedia(2,1,Size2DSyntax.INCH));
//		    printRequestAttributeSet.add(mediaSizeName);
		    printRequestAttributeSet.add(MediaSizeName.ISO_A10);
		    printRequestAttributeSet.add(new Copies(1));
//		    JRPrintServiceExporter exporter;
//		    exporter = new JRPrintServiceExporter();
//		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//		    /* We set the selected service and pass it as a paramenter */
//		    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]);
//		    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes());
//		    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
//		    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
//		    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
		    
//		    exporter.exportReport();
		    JasperViewer.viewReport(print);
		    
		    System.out.println("Done!");		
		    connection.close();
		    
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
		
	}
	
	public void printOnlyBarCodesNew(Integer clientId)
	{
		System.out.println("in printOnlyBarCodesNew bll method");
		
		Session session = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			Connection connection = session.connection();
//			JasperReport jasperReport = JasperCompileManager.compileReport(cb.getCashReceiptTemplateFile().getPath());
			
			InputStream template = JasperReport.class
				    .getResourceAsStream(Environment.getReportsTemplatePath()+
				    		Environment.getBarCodesTemplateFile());
			 String sourceFileName = "d://rdc_docs/templates/rdcSamplesBarCodes.jasper";
			         
			String printFileName = null;
			
			Map<String, Object> parameters = new HashMap<String, Object>();
		    parameters.put("clientId", clientId);
		    
//			JasperReport jasperReport = JasperCompileManager.compileReport(template);
//			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);
			printFileName = JasperFillManager.fillReportToFile( 
		            sourceFileName, parameters, connection);
			if(printFileName != null){
	            JasperPrintManager.printReport( printFileName, true);
			}
			
			
		    
		    System.out.println("Done!");		
		    connection.close();
		    
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
		
	}
	
	public void printBarCodes(Integer clientId)
	{
		System.out.println("in printBarCodes bll method");
		
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
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, connection);

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
		    connection.close();
		    
//		    Going to display generated report in another browser window
		    ViewScannedFilesUtils fu = new ViewScannedFilesUtils();
		    fu.viewScannedFile("application/pdf", Environment.getBarCodesStoragePath(), clientId+
		    		Environment.getBarCodesNameFormat());
		    
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
		
	}
	
	public void printMedicalReport(Integer clientId)
	{
		System.out.println("in print medical report bll method");
		boolean flag = true;
		
		Session session = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			Connection connection = session.connection();
//			JasperReport jasperReport = JasperCompileManager.compileReport("d:/reports/rdcMedicalReport.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(cb.getMedReportTemplateFile().getPath());
			
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
	        ExternalContext externalContext = facesContext.getExternalContext();
	        	
			Map<String, Object> parameters = new HashMap<String, Object>();
		    parameters.put("clientId", clientId);
		    
		     
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, connection);
//		    JasperViewer.viewReport(jasperPrint);
		    

		 
//		    Export to PDF.
		    JasperExportManager.exportReportToPdfFile(jasperPrint,
		    		Environment.getMedicalReportsStoragePath()+clientId+
		    		Environment.getMedicalReportsNameFormat());
		    System.out.println("Done!");		
		    connection.close();
		    
//		    Going to display generated report in another browser window
		    ViewScannedFilesUtils fu = new ViewScannedFilesUtils();
		    fu.viewScannedFile("application/pdf", Environment.getMedicalReportsStoragePath(), clientId+
		    		Environment.getMedicalReportsNameFormat());
		    
		}
		catch(JRException e)
		{
			e.printStackTrace();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			flag = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			flag = false;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
	}
	
	
	
	public void printCashReceipt(Integer clientId)
	{
		System.out.println("in printCahReceipt bll method");
		
		Session session = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			Connection connection = session.connection();
			JasperReport jasperReport = 
					JasperCompileManager.compileReport(cb.getCashReceiptTemplateFile().getPath());
			Map<String, Object> parameters = new HashMap<String, Object>();
		    parameters.put("clientId", clientId);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, connection);

		    		 
//		    Export to PDF.
		    JasperExportManager.exportReportToPdfFile(jasperPrint,
		    		Environment.getCashReceiptsStoragePath()+clientId+
		    		Environment.getCashReceiptNameFormat());
		    System.out.println("Done!");		
		    connection.close();
		    
//		    Going to display generated report in another browser window
		    ViewScannedFilesUtils fu = new ViewScannedFilesUtils();
		    fu.viewScannedFile("application/pdf", Environment.getCashReceiptsStoragePath(), clientId+
		    		Environment.getCashReceiptNameFormat());
		    
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
		
	}
	
	public void downloadPDF(Integer clientId) throws IOException 
	{

        // Prepare.
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        

//        File file = new File(getFilePath(), getFileName());
        System.out.println("Appl name is "+Environment.getApplName());
        File file = new File(Environment.getMedicalReportsStoragePath(), clientId+
        		Environment.getMedicalReportsNameFormat());
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
        	int fileMaxSize = Integer.valueOf(Environment.getFileMaxSize());
            // Open file.
            input = new BufferedInputStream(new FileInputStream(file), 
            		fileMaxSize);

            // Init servlet response.
            response.reset();
            response.setHeader("Content-Type", "application/pdf");
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + clientId+
            		Environment.getMedicalReportsNameFormat()+ "\"");
            output = new BufferedOutputStream(response.getOutputStream(), fileMaxSize);

            // Write file contents to response.
            byte[] buffer = new byte[fileMaxSize];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Finalize task.
            output.flush();
        } finally {
            // Gently close streams.
            close(output);
            close(input);
        }

        // Inform JSF that it doesn't need to handle response.
        // This is very important, otherwise you will get the following exception in the logs:
        // java.lang.IllegalStateException: Cannot forward after response has been committed.
        facesContext.responseComplete();
    }

    // Helpers (can be refactored to public utility class) ----------------------------------------

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it. It may be useful to 
                // know that this will generally only be thrown when the client aborted the download.
                e.printStackTrace();
            }
        }
    }
	
	private Blob saveBlob(UploadedFile file)throws Exception
	{
		
		byte[] fileContents = new byte[(int) file.getContents().length];
		
		Path folder = Paths.get("d:\temp");
		
		InputStream fileInputStream = file.getInputstream();
		OutputStream output = new FileOutputStream(new File("d:\temp", ub.getCurrentUser().getId()+file.getFileName()));
		try {
	        IOUtils.copy(fileInputStream, output);
	    } finally {
	        IOUtils.closeQuietly(fileInputStream);
	        IOUtils.closeQuietly(output);
	    }
		
//		fileInputStream.read(fileContents);
//        fileInputStream.close();
//        Blob blob = Hibernate.createBlob(fileContents);
        return null;
	}
	
	public void saveTrackReport(String activity, WfClient client, Session session)throws HibernateException
	{
		WfTrackReport report = new WfTrackReport();
		report.setOperator(ub.getCurrentUser());
		report.setActivity(activity);
		report.setClientId(client);
		
		session.save(report);
	}
	
	private void initCashObj(WfClientFinance obj, Session session)throws HibernateException, Exception
	{
		obj.setCashAmount(null);
		obj.setCashPaidDate(null);
		obj.setCashPaidStatus(MessageConstants.Constants.CashPaymentStatus.UNPAID);
		session.update(obj);
	}
	
}
