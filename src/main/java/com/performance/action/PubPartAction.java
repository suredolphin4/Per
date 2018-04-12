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
import com.performance.pagemodel.Domain;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Person;
import com.performance.pagemodel.PubPart;
import com.performance.pagemodel.User;
import com.performance.service.DomainServiceI;
import com.performance.service.PubPartServiceI;

/**
 * 期刊分区
 * 
 * @author peng
 * 
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "pubPartAction")
public class PubPartAction extends BaseAction<PubPart> implements ModelDriven<PubPart> {

	private static final Logger logger = Logger.getLogger(PubPartAction.class);
	private PubPart pubPart = new PubPart();
	
	@Override
	public PubPart getModel() {
		return pubPart;
	}

	private PubPartServiceI pubPartService;

	public PubPartServiceI getPubPartService() {
		return pubPartService;
	}

	@Autowired
	public void setPubPartService(PubPartServiceI pubPartService) {
		this.pubPartService = pubPartService;
	}

	public void add() {
		Json j = new Json();
		try {
			PubPart u = pubPartService.save(pubPart);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);
	}

	public void datagrid() {
		super.writeJson(pubPartService.datagrid(pubPart));
	}

	public void getPubListByKind(){
		super.writeJson(pubPartService.getPubListByKind(pubPart));
	}
	
	public  void pubPartCheck(){
		Json j = new Json();
		PubPart u=pubPartService.pubPartCheck(pubPart);
		if(u!=null){
			j.setSuccess(true);
			j.setObj(u);
		}else{
			j.setSuccess(false);
		}
		super.writeJson(j);
	}
	
	
	public void remove() {
		pubPartService.remove(pubPart.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	public void upLoadExcel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String filenameString = pubPart.getUrl();
		Json j = new Json();
			try {
				boolean compareVal = this.compareTitle(filenameString);
				if (!compareVal) {
					j.setSuccess(false);
					j.setMsg("导入失败！导入数据有误！");
					return;
				} else {
			FileInputStream fis = FileUtils.openInputStream(new File(
					filenameString));
			List<PubPart> pubParts = pubPartService.ThesisByPoi(fis);
			j.setSuccess(true);
			j.setMsg("导入成功！");
			super.writeJson(j);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("导入失败！导入数据有误！");
		}
	}
	
	
	public void downloadExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fileName = pubPartService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}
	
	
	public PubPart edit(){
		PubPart r=pubPartService.edit(pubPart);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(r);
		super.writeJson(j); 
		return r;
	}
}
