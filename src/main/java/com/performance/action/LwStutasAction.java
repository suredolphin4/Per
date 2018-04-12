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
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.Domain;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.LwStutas;
import com.performance.pagemodel.User;
import com.performance.service.LwStutasServiceI;
import com.performance.service.UserServiceI;

/**
 * 论文状态
 * @author peng
 *
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "lwStutasAction")
public class LwStutasAction extends BaseAction<LwStutas> implements ModelDriven<LwStutas> {
	private LwStutas lwStutas = new LwStutas();

	@Override
	public LwStutas getModel() {
		return lwStutas;
	}

	private static final Logger logger = Logger.getLogger(LwStutasAction.class);

	private LwStutasServiceI LwStutasService;

	
	public LwStutasServiceI getLwStutasService() {
		return LwStutasService;
	}

	@Autowired
	public void setLwStutasService(LwStutasServiceI lwStutasService) {
		LwStutasService = lwStutasService;
	}


	public void add() {
		Json j = new Json();
		try {
			LwStutas u = LwStutasService.save(lwStutas);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}


	public void datagrid() {
		super.writeJson(LwStutasService.datagrid(lwStutas));
	}

	public void getLwStatusList(){
		super.writeJson(LwStutasService.getLwStatusList());
	}

	public void remove() {
		
		LwStutasService.remove(lwStutas.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	public void upLoadExcel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String filenameString = lwStutas.getUrl();
		Json j = new Json();
		boolean compareVal = this.compareTitle(filenameString);
		try {
			if (!compareVal) {
				j.setSuccess(false);
				j.setMsg("导入失败！导入数据有误！");
				return;
			} else {
		FileInputStream fis=FileUtils.openInputStream(new File(filenameString));
					List<LwStutas> lwStutases=LwStutasService.ThesisByPoi(fis);
					j.setSuccess(true);
					j.setMsg("导入成功！");
					super.writeJson(j);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void downloadExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fileName = LwStutasService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}
	
	
	public LwStutas edit(){
		LwStutas r=LwStutasService.edit(lwStutas);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(r);
		super.writeJson(j); 
		return r;
	}
}
