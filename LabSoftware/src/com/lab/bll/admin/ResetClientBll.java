/**
 * 
 */
package com.lab.bll.admin;

import com.lab.dal.ResetClientDal;
import com.lab.dal.dao.ApplicationUsers;
import com.lab.dal.dao.WfClient;

/**
 * @author Analysis
 *
 */
public class ResetClientBll {

	public void resetDirectorStatus(WfClient selectedClient, ApplicationUsers currentUser){
		new ResetClientDal().resetDirectorStatus(selectedClient, currentUser);
	}
	
	public void resetPathologyStatus(WfClient selectedClient, ApplicationUsers currentUser){
		new ResetClientDal().resetPathologyStatus(selectedClient, currentUser);
	}
	public void resetGpeStatus(WfClient selectedClient, ApplicationUsers currentUser){
		new ResetClientDal().resetGpeStatus(selectedClient, currentUser);
	}
	
}
