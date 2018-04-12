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
import com.performance.pagemodel.UserType;
import com.performance.service.UserTypeServiceI;
import com.performance.service.UserServiceI;

/**
 * 
 * @author peng
 * 
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "userTypeAction")
public class UserTypeAction extends BaseAction<UserType> implements ModelDriven<UserType> {
	private static final Logger logger = Logger.getLogger(UserTypeAction.class);
	private UserType userType = new UserType();

	public UserTypeServiceI getUserTypeService() {
		return userTypeService;
	}

	@Autowired
	public void setUserTypeService(UserTypeServiceI userTypeService) {
		this.userTypeService = userTypeService;
	}

	@Override
	public UserType getModel() {
		return userType;
	}


	private UserTypeServiceI userTypeService;

	public void datagrid() {
		try {
			super.writeJson(userTypeService.datagrid(userType));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public UserType edit(){
		UserType r=userTypeService.edit(userType);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(r);
		super.writeJson(j); 
		return r;
	}
	
public void remove() {
		
	try {
		userTypeService.remove(userType.getIds());
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
			UserType u = userTypeService.save(userType);
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
		String filenameString = userType.getUrl();
		Json j = new Json();
			boolean compareVal = this.compareTitle(filenameString);
			try {
				if (!compareVal) {
					j.setSuccess(false);
					j.setMsg("导入失败！导入数据有误！");
					return;
				} else {
					FileInputStream fis=FileUtils.openInputStream(new File(filenameString));
					List<UserType> userlist=userTypeService.ThesisByPoi(fis);
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

		String fileName = userTypeService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}

}
