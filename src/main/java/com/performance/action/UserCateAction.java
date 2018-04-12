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
import com.performance.pagemodel.Json;
import com.performance.pagemodel.User;
import com.performance.pagemodel.UserCate;
import com.performance.service.UserCateServiceI;
import com.performance.service.UserServiceI;

/**
 * 
 * @author peng
 * 
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "userCateAction")
public class UserCateAction extends BaseAction<UserCate> implements ModelDriven<UserCate> {
	private UserCate userCate = new UserCate();

	@Override
	public UserCate getModel() {
		return userCate;
	}

	private static final Logger logger = Logger.getLogger(UserCateAction.class);

	private UserCateServiceI userCateService;

	public UserCateServiceI getUserCateService() {
		return userCateService;
	}

	@Autowired
	public void setUserCateService(UserCateServiceI userCateService) {
		this.userCateService = userCateService;
	}

	public void datagrid() {
		try {
			super.writeJson(userCateService.datagrid(userCate));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public UserCate edit(){
		UserCate r=userCateService.edit(userCate);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(r);
		super.writeJson(j); 
		return r;
	}
	
public void remove() {
		
	try {
		userCateService.remove(userCate.getIds());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	
	public void add() {
		Json j = new Json();
		try {
			UserCate u = userCateService.save(userCate);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}

	
	
	
	public void upLoadExcel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String filenameString = userCate.getUrl();
		Json j = new Json();
			boolean compareVal = this.compareTitle(filenameString);
			try {
				if (!compareVal) {
					j.setSuccess(false);
					j.setMsg("导入失败！导入数据有误！");
					return;
				} else {
					FileInputStream fis=FileUtils.openInputStream(new File(filenameString));
					List<UserCate> userlist=userCateService.ThesisByPoi(fis);
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

		String fileName = userCateService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}
	

}
