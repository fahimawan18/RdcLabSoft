/**
 * 
 */
package com.lab.dal;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lab.bll.wf.RegisterClientBll;
import com.lab.dal.dao.ApplicationUsers;
import com.lab.dal.dao.WfClient;
import com.lab.dal.dao.WfClientFinance;
import com.lab.utils.HibernateUtilsAnnot;
import com.lab.utils.MessageConstants;

/**
 * @author Analysis
 *
 */
public class ResetClientDal {

	public boolean resetDirectorStatus(WfClient toUpdate, ApplicationUsers currentUser)
	{
		System.out.println("in update cash bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
				
			toUpdate.setUpdateBy(currentUser);
			toUpdate.setUpdateDate(new Date());
			toUpdate.setClientStatus(MessageConstants.Constants.ClientStatus.REGISTERED);
			//toUpdate.setRepeatStatus(MessageConstants.Constants.RepeatStatus.REPEAT);
			toUpdate.setFinalDeclaredBy(null);
			toUpdate.setFinalDeclaredDate(null);
							
			session.update(toUpdate);
			session.flush();
			
			
			new RegisterClientBll().saveTrackReport(MessageConstants.Constants.TrackActions.RESET_ADMIN_DIRECTOR, toUpdate, session);
		
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
	
	public boolean resetPathologyStatus(WfClient toUpdate, ApplicationUsers currentUser)
	{
		System.out.println("in update cash bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
				
			toUpdate.setUpdateBy(currentUser);
			toUpdate.setUpdateDate(new Date());
			toUpdate.setClientStatus(MessageConstants.Constants.ClientStatus.REGISTERED);
			//toUpdate.setRepeatStatus(MessageConstants.Constants.RepeatStatus.REPEAT);
			toUpdate.getProgress().setPathologist(null);
			toUpdate.setFinalDeclaredBy(null);
			toUpdate.setFinalDeclaredDate(null);
							
			session.update(toUpdate);
			session.update(toUpdate.getProgress());
			session.flush();
			
			
			new RegisterClientBll().saveTrackReport(MessageConstants.Constants.TrackActions.RESET_ADMIN_PATHOLOGY, toUpdate, session);
		
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
	
	public boolean resetGpeStatus(WfClient toUpdate, ApplicationUsers currentUser)
	{
		System.out.println("in update cash bll method");
		boolean flag = true;
		
		Session session = null;
		Transaction tx = null;
		
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
				
			toUpdate.setUpdateBy(currentUser);
			toUpdate.setUpdateDate(new Date());
			toUpdate.setClientStatus(MessageConstants.Constants.ClientStatus.REGISTERED);
			//toUpdate.setRepeatStatus(MessageConstants.Constants.RepeatStatus.REPEAT);
//			toUpdate.getProgress().setPathologist(null);
			toUpdate.getProgress().setGpe(null);
			toUpdate.setFinalDeclaredBy(null);
			toUpdate.setFinalDeclaredDate(null);
							
			session.update(toUpdate);
			session.update(toUpdate.getProgress());
			session.flush();
			
			
			new RegisterClientBll().saveTrackReport(MessageConstants.Constants.TrackActions.RESET_ADMIN_GPE, toUpdate, session);
		
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
	
	
}
