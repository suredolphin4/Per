package com.performance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.service.UserServiceI;
import com.performances.model.TUser;
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "userAction")
public class UserAction extends BaseAction<User> implements ModelDriven<User> {
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	private static final Logger logger = Logger.getLogger(UserAction.class);

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	public void reg() {
		
		Json j = new Json();
		try {
			userService.save(user);
			j.setSuccess(true);
			j.setMsg("注册成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}

	public void add() {
		Json j = new Json();
		try {
			User u = userService.save(user);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}

	public void login() {
		User u = userService.login(user);
		Json j = new Json();
		if (u != null) {
			j.setSuccess(true);
			j.setMsg("登陆成功！");
		} else {
			j.setMsg("登录失败，用户名或密码错误！");
		}

		super.writeJson(j);
	}

	
	public  void userCheck(){
		Json j = new Json();
		User u=userService.userCheck(user);
		if(u!=null){
			j.setSuccess(true);
			j.setObj(u);
			j.setMsg("userexsit");
		}else{
			j.setSuccess(false);
			j.setMsg("notexsit");
		}
		super.writeJson(j);
	}
	public void datagrid() {
		super.writeJson(userService.datagrid(user));
	}

	public void remove() {
		
		userService.remove(user.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	
	public void resetPWD(){
		Json j = new Json();
		
		try{
			String[] nids = user.getIds().split(",");
			
			
		for (int i = 0; i < nids.length; i++) {
				//User user=userService.findUserById(nids[i]);
				TUser user=userService.findTUserById(Integer.valueOf(nids[i]));
				userService.resetPasswd(user);
			}
			
			j.setSuccess(true);
			j.setMsg("重置成功！");
		}catch(Exception e){
			j.setMsg("提交出现异常！请联系管理员！");
			e.printStackTrace();
		}
		

		super.writeJson(j);
	}
	
	public User edit(){
		User r=userService.edit(user);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(r);
		super.writeJson(j); 
		return r;
	}
	
	public void downloadExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fileName = userService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}
	
	
	public void upLoadExcel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String filenameString = user.getUrl();
		Json j = new Json();
			boolean compareVal = this.compareTitle(filenameString);
			try {
				if (!compareVal) {
					j.setSuccess(false);
					j.setMsg("导入失败！导入数据有误！");
					return;
				} else {
					FileInputStream fis=FileUtils.openInputStream(new File(filenameString));
					List<User> userlist=userService.ThesisByPoi(fis);
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
