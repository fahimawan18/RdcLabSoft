/**
 * 
 */
package com.lab.dal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.lab.dal.dao.ApplicationUsers;
import com.lab.dal.dao.WfClient;
import com.lab.dal.dao.WfClientScannedFiles;
import com.lab.utils.HibernateUtilsAnnot;

/**
 * @author Analysis
 *
 */
public class AdminDal {
	
	public WfClientScannedFiles getClientScannedFiles(WfClient selectedClient){
		Session session = null;
		WfClientScannedFiles scannedFiles = null;
		System.out.println("In Admin dal method");
		try
		{
			session = HibernateUtilsAnnot.currentSession();			
			scannedFiles = (WfClientScannedFiles) session.createQuery(
					"from WfClientScannedFiles where clientId.id = :clientID ")
					.setParameter("clientID", selectedClient.getId())
					
					.uniqueResult();

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
	
		return scannedFiles;
	}
}
