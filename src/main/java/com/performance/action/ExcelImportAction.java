package com.performance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.PubPart;
import com.performance.service.ImportExcelServiceI;
import com.performances.model.TLw;

/**
 * 文件上传类
 * 
 * @author zheng
 * 
 */
public class ExcelImportAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File upload;
	private String contentType; //上传文件类型
	private String uploadFileName;    //上传文件名称
	private String result;

	
	
    private ImportExcelServiceI<TLw> _importExcel;
	
	public ImportExcelServiceI<TLw> get_importExcel() {
		return _importExcel;
	}
	
	@Autowired
	public void set_importExcel(ImportExcelServiceI<TLw> _importExcel) {
		this._importExcel = _importExcel;
	}
	public void setUpload(File file) {
		this.upload = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.uploadFileName = filename;
	}
	
	public String getResult() {
		return result;
	}

	@Override
	public String execute(){
		//获取上传文件路径，应在配置文件中定义
		/*String path = ServletActionContext.getServletContext().getRealPath("/appends");
		File file = new File(path);
		if(!file.exists()){
			file.mkdir();
		}*/
		Json j = new Json();
		try{
			
			FileInputStream fis = FileUtils.openInputStream(upload);
			
			_importExcel.ImportExcelToDB("t_lw", fis);
			j.setSuccess(true);
			j.setMsg("导入成功！");
		//super.writeJson(j);
		//	FileUtils.copyFile(upload, new File(file, uploadFileName));
		}catch(Exception e){
			e.printStackTrace();
		}
		result = "上传成功";
		
		return SUCCESS;
	}
}
