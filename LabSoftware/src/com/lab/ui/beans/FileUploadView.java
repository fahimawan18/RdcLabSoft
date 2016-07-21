package com.lab.ui.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean(name = "fileUploadView")
public class FileUploadView 
{
	private UploadedFile file1;
	private byte[] fileBinary1;
	private String file1Mime;
	

	private UploadedFile file2;
	private byte[] fileBinary2;
	private String file2Mime;
	
	private UploadedFile file3;
	private byte[] fileBinary3;
	private String file3Mime;
	 
    
    
    public void uploadEvent(FileUploadEvent event) {
		
		System.out.println("in upload file listener");
		FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        file1=event.getFile();
        fileBinary1 = file1.getContents();
        file1Mime =file1.getFileName().substring((file1.getFileName().lastIndexOf('.')+1));
        System.out.println("file 1 mime ="+file1Mime);

	}
    
    public void uploadEvent2(FileUploadEvent event) {
		
		System.out.println("in upload file listener 2");
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        file2=event.getFile();
        fileBinary2 = file2.getContents();
        file2Mime =file2.getFileName().substring((file2.getFileName().lastIndexOf('.')+1));
        System.out.println("file 2 mime ="+file2Mime);

	}
    
    public void uploadEvent3(FileUploadEvent event) 
    {
		
		System.out.println("in upload file listener 3");
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        file3=event.getFile();
        fileBinary3 = file3.getContents();
        file3Mime =file3.getFileName().substring((file3.getFileName().lastIndexOf('.')+1));
        System.out.println("file 3 mime ="+file3Mime);

	}

	public UploadedFile getFile1() {
		return file1;
	}

	public void setFile1(UploadedFile file1) {
		this.file1 = file1;
	}

	public byte[] getFileBinary1() {
		return fileBinary1;
	}

	public void setFileBinary1(byte[] fileBinary1) {
		this.fileBinary1 = fileBinary1;
	}

	public UploadedFile getFile2() {
		return file2;
	}

	public void setFile2(UploadedFile file2) {
		this.file2 = file2;
	}

	public byte[] getFileBinary2() {
		return fileBinary2;
	}

	public void setFileBinary2(byte[] fileBinary2) {
		this.fileBinary2 = fileBinary2;
	}

	public UploadedFile getFile3() {
		return file3;
	}

	public void setFile3(UploadedFile file3) {
		this.file3 = file3;
	}

	public byte[] getFileBinary3() {
		return fileBinary3;
	}

	public void setFileBinary3(byte[] fileBinary3) {
		this.fileBinary3 = fileBinary3;
	}

	public String getFile1Mime() {
		return file1Mime;
	}

	public void setFile1Mime(String file1Mime) {
		this.file1Mime = file1Mime;
	}

	public String getFile2Mime() {
		return file2Mime;
	}

	public void setFile2Mime(String file2Mime) {
		this.file2Mime = file2Mime;
	}

	public String getFile3Mime() {
		return file3Mime;
	}

	public void setFile3Mime(String file3Mime) {
		this.file3Mime = file3Mime;
	}
    
//    public void fileUploadListener(FileUploadEvent event)
//	{
//		System.out.println("in upload file listener");
//		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        uploadedFile=event.getFile();
//    }
//
//    public void insert(){
//        if(uploadedFile!=null){
//            System.out.println("Got the file for upload ="+uploadedFile.getFileName());
//        }
//        else{
//            System.out.println("The file object is null.");
//        }
//    }

}
