package com.performance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Lab;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.PubPart;
import com.performance.service.LabServiceI;

/**
 * 研究室
 * @author peng
 *
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "labAction")
public class LabAction extends BaseAction<Lab> implements ModelDriven<Lab> {
	private Lab lab = new Lab();

	@Override
	public Lab getModel() {
		return lab;
	}

	private static final Logger logger = Logger.getLogger(LabAction.class);

	private LabServiceI labService;

	

	public LabServiceI getLabService() {
		return labService;
	}

	@Autowired
	public void setLabService(LabServiceI labService) {
		this.labService = labService;
	}


	public void add() {
		Json j = new Json();
		try {
			Lab u = labService.save(lab);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}


	public void datagrid() {
		super.writeJson(labService.datagrid(lab));
	}

	
	public Lab edit(){
		Lab r=labService.edit(lab);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(r);
		super.writeJson(j); 
		return r;
	}
	public void remove() {
		
		labService.remove(lab.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	
	public void downloadExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fileName = labService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}
	
	
	public void upLoadExcel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String filenameString = lab.getUrl();
		Json j = new Json();
			boolean compareVal = this.compareTitle(filenameString);
			try {
				if (!compareVal) {
					j.setSuccess(false);
					j.setMsg("导入失败！导入数据有误！");
					return;
				} else {
			
					FileInputStream fis=FileUtils.openInputStream(new File(filenameString));
					List<Lab> lwlist=labService.ThesisByPoi(fis);
					j.setSuccess(true);
					j.setMsg("导入成功！");
					super.writeJson(j);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
