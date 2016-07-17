package com.lab.ui.beans.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.iac.web.util.FacesUtils;
import com.lab.bll.admin.AdminBll;
import com.lab.dal.dao.ApplicationUsers;
import com.lab.dal.dao.WfClient;
import com.lab.ui.beans.UserBean;
import com.lab.utils.Environment;
import com.lab.utils.MessageConstants;
import com.lab.utils.MessageUtils;
import com.lab.utils.NavigationConstants;
import com.lab.utils.ViewScannedFilesUtils;

@ManagedBean(name="adminBean")
@SessionScoped
public class AdminBean 
{
	
	private ApplicationUsers toAddUser;
	private ApplicationUsers toSearchUser;
	private List<ApplicationUsers> usersList;
	private ApplicationUsers currentUser;
	private String newPassword = "";
	private String newPasswordAgain = "";
	
	private WfClient selectedClient;
	private String scannedFileType;
	
	
	
	public AdminBean() 
	{		
		// TODO Auto-generated constructor stub
		toAddUser = new ApplicationUsers();
		toSearchUser = new ApplicationUsers();
		this.usersList = new ArrayList<ApplicationUsers>();
		this.selectedClient = new WfClient();
	}
	
	public String addNewUser()
	{
		
		AdminBll bll =new AdminBll();
		if(bll.addNewUser(toAddUser))
		{
//			FacesUtils.addInfoMessage("Login credentials", MessageConstants.Messages.SAVE_SUCCESS);
			MessageUtils.info(MessageConstants.Messages.SAVE_SUCCESS);
			this.toAddUser = new ApplicationUsers();
		}
		else
		{
//			FacesUtils.addErrorMessage("Login credentials", MessageConstants.Messages.SAVE_FAILURE);
			MessageUtils.error(MessageConstants.Messages.SAVE_FAILURE);
		}
		
		return "";
	}

	public String searchUsers()
	{
//		if(this.usersList.size()==0)
		{
			AdminBll bll =new AdminBll();
			this.usersList = bll.searchAllUser(toSearchUser);			
		}
		return "";
	}
	
	public String updateUsers()
	{

		AdminBll bll =new AdminBll();
		if(usersList.size()>0 &&  bll.updateUsers(usersList))
		{
//			FacesUtils.addInfoMessage(MessageConstants.Messages.UPDATE_SUCCESS);
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			this.usersList = bll.searchAllUser(toSearchUser);			
		}
		else
		{
//			FacesUtils.addErrorMessage(MessageConstants.Messages.UPDATE_FAILURE);
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}

		return "";
	}
	
	public String resetPassword()
	{
		AdminBll bll =new AdminBll();
		newPassword = MessageConstants.Constants.DEFAULT_PASSWORD;
		if(bll.changePassword(toSearchUser,newPassword))
		{
//			FacesUtils.addInfoMessage("Login credentials", MessageConstants.Messages.UPDATE_SUCCESS);
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			return NavigationConstants.ADMIN_MANAGE_USERS_NAVIGATION;			
		}
		else
		{
//			FacesUtils.addErrorMessage("Login credentials", MessageConstants.Messages.UPDATE_FAILURE);
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}

		return "";
	}
	
	public String changePassword()
	{
		currentUser = ((UserBean)FacesUtils.getManagedBean("userBean")).getCurrentUser();
		AdminBll bll =new AdminBll();
		
		if(newPassword==null || newPasswordAgain==null || 
				newPassword.trim().length()==0 	|| newPasswordAgain.trim().length()==0
				)
		{
//			FacesUtils.addErrorMessage("Login credentials", "Invalid Password");
			MessageUtils.error(MessageConstants.Messages.INVALID_PASSWORD);
			return "";
		}
		
		if(!newPassword.equals(newPasswordAgain))
		{
//			FacesUtils.addErrorMessage("Login credentials", "Passwords do not match");
			MessageUtils.error("Passwords do not match");
			return "";
		}
		
		if(bll.changePassword(currentUser,newPassword))
		{
//			FacesUtils.addInfoMessage("Login credentials", MessageConstants.Messages.UPDATE_SUCCESS);
			MessageUtils.info(MessageConstants.Messages.UPDATE_SUCCESS);
			return NavigationConstants.HOME_NAVIGATION;			
		}
		else
		{
//			FacesUtils.addErrorMessage("Login credentials", MessageConstants.Messages.UPDATE_FAILURE);
			MessageUtils.error(MessageConstants.Messages.UPDATE_FAILURE);
		}

		return "";
	}
	
	
	public String viewScannedFile()
	{
		String contentType="application/pdf", filePath, fileName="";
		String mime="pdf";
		
		try
		{
			File files = new File(Environment.getScannedFilesStoragePath());
		    if(files.exists())
		    {
		    	
		    }
		    else
		    {
		    	files.mkdirs();
		    }
			filePath = Environment.getScannedFilesStoragePath();
			if(this.scannedFileType.equals(MessageConstants.Constants.ScannedFileTypes.GAMCA))
			{
				mime = this.selectedClient.getScannedFiles().getGamcaMime();
				fileName = this.selectedClient.getId()+"gamca."+mime;
				makeFileFromByte(filePath+fileName,this.selectedClient.getScannedFiles().getScannedGamca());
				 
			}
			else if (this.scannedFileType.equals(MessageConstants.Constants.ScannedFileTypes.PASSPORT))
			{
				mime = this.selectedClient.getScannedFiles().getPassportMime();
				fileName = this.selectedClient.getId()+"passport."+mime;
				makeFileFromByte(filePath+fileName,this.selectedClient.getScannedFiles().getScannedPassport());
			}
			else if (this.scannedFileType.equals(MessageConstants.Constants.ScannedFileTypes.PHOTO))
			{
				mime = this.selectedClient.getScannedFiles().getPhotoMime();
				fileName = this.selectedClient.getId()+"photo."+mime;
				makeFileFromByte(filePath+fileName,this.selectedClient.getScannedFiles().getScannedPhoto());
			}
			
			if(mime.equals("pdf"))
			{
				contentType = "application/pdf";
			}
			else if(mime.equals("jpeg") || mime.equals("jpg"))
			{
				contentType = "image/jpeg";
			}
			
			ViewScannedFilesUtils fu = new ViewScannedFilesUtils();
			fu.viewScannedFile(contentType, filePath, fileName);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
			
		
		return "";
	}
	
	
	private void makeFileFromByte(String path, byte[] bytes)throws FileNotFoundException, Exception
	{
		FileOutputStream stream = new FileOutputStream(path);
		stream.write(bytes);
		stream.close();
		
	}

	
	public ApplicationUsers getToAddUser() {
		return toAddUser;
	}

	public void setToAddUser(ApplicationUsers toAddUser) {
		this.toAddUser = toAddUser;
	}

	public List<ApplicationUsers> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<ApplicationUsers> usersList) {
		this.usersList = usersList;
	}

	public ApplicationUsers getToSearchUser() {
		return toSearchUser;
	}

	public void setToSearchUser(ApplicationUsers toSearchUser) {
		this.toSearchUser = toSearchUser;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}

	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}
	public String getScannedFileType() {
		return scannedFileType;
	}

	public void setScannedFileType(String scannedFileType) {
		this.scannedFileType = scannedFileType;
	}

	public WfClient getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(WfClient selectedClient) {
		this.selectedClient = selectedClient;
	}

}
