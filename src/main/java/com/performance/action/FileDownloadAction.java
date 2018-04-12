package com.performance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.opensymphony.xwork2.ActionSupport;
import com.performance.pagemodel.User;
import com.performance.util.UserAware;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * 文件下载
 * 
 * @author zheng
 * 
 */
public class FileDownloadAction extends ActionSupport {
	private static final Logger logger = Logger.getLogger(FileDownloadAction.class);
	private String fileName; // 文件名称
	private String inputPath; // 文件路径

	public InputStream getInputStream() throws IOException {
		//获取上传文件路径，应在配置文件中定义
		Properties prop = new Properties();
		String propFileName = "config.properties";

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			try{
				prop.load(inputStream);
				inputStream.close();
			}catch(IOException e){
//				this.success = false;
//				this.message = "加载config.property文件出错！";
//				return ERROR;
			}
		} else {
			//throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
//			this.success = false;
//			this.message = "未发现配置文件config.property";
//			return ERROR;
		}
		
		String uploadPath = prop.getProperty("uploadDirectory");
		File file = new File(uploadPath, this.fileName);
		FileInputStream fis;
		try{
		 	fis = new FileInputStream(file);
		}catch (Exception ex){
			ex.printStackTrace();
			fis = null;
		}finally {

		}

		return fis;
		//return new java.io.FileInputStream(file);
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 提供转换编码后的供下载用的文件名
	 * @return
	 */
	public String getFileName() {
		String downFileName = fileName;
		try {
			downFileName = new String(downFileName.getBytes(), "ISO8859-1");
		} catch (java.io.UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downFileName;
	}

	public void setFileName(String fileName) {
		try {
			this.fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
		}catch(java.io.UnsupportedEncodingException ex){
			//TODO handle exceptions

		}
	}

	public String getInputpath() {
		return inputPath;
	}

	public void setInputpath(String inputpath) {
		this.inputPath = inputpath;
	}
}
