package com.performance.action;

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
import com.performance.service.UserServiceI;

/**
 * 专著类别
 * @author peng
 *
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "zzCateAction")
public class ZzCateAction extends BaseAction implements ModelDriven<User> {
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	private static final Logger logger = Logger.getLogger(ZzCateAction.class);

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
		logger.info("hi");
//		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
//		
//		UserServiceI userServiceI=(UserServiceI) ac.getBean("userService");
//		userServiceI.saver();
		User user=new User();
		user.setUserid(0023);
	user.setPwd("dddddd");
		userService.save(user);
	}
}
