package com.performance.action;

import java.io.*;
import java.util.UUID;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件上传类
 * 
 * @author zheng
 * 
 */
public class FileUploadAction extends ActionSupport {
	private File upload;
	private String contentType; //上传文件类型
	private String uploadFileName;    //上传文件名称
	private boolean success;
	private String saveFileName;
	private String message;
	
	public void setUpload(File file) {
		this.upload = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.uploadFileName = filename;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String execute(){
		//获取上传文件路径，应在配置文件中定义
		Properties prop = new Properties();
		String propFileName = "config.properties";

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			try{
				prop.load(inputStream);
				inputStream.close();
			}catch(IOException e){
				this.success = false;
				this.message = "加载config.property文件出错！";
				return ERROR;
			}
		} else {
			//throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			this.success = false;
			this.message = "未发现配置文件config.property";
			return ERROR;
		}

		File file = new File(prop.getProperty("uploadDirectory"));
		if(!file.exists()){
			file.mkdir();
		}
		
		saveFileName = this.uploadFileName;
		File uploadFile = new File(file, saveFileName);
		//相同名称附件已存在
		int increment = 1;
		while(uploadFile.exists() && !uploadFile.isDirectory()){
			String fileName = this.uploadFileName.substring(0, this.uploadFileName.lastIndexOf("."));
			String fileEx =  this.uploadFileName.substring(this.uploadFileName.lastIndexOf("."));
			fileName = fileName + String.valueOf(increment);
			increment++;
			saveFileName = fileName + fileEx;
			uploadFile = new File(file, saveFileName);
		}
		
		try{
			FileUtils.copyFile(upload, uploadFile);
		}catch(IOException e){
			this.success = false;
			this.message = "服务器保存附件时发生错误,请联系管理员处理!";
			return ERROR;
		}

		this.success = true;
		return SUCCESS;
	}
}
