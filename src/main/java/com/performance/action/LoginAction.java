package com.performance.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.service.RightServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.Constants;
import com.performance.util.DataUtil;
import com.performances.model.TRole;
import com.performances.model.TUser;

@Action(value = "loginAction", results = { 
@Result(name = "toLogin", location = "/login.jsp") ,
@Result(name = "touserIndex", location = "/layout/mainFrame.jsp")
//@Result(name = "touserIndex", location = "/indexform.jsp")
})
@Namespace("/")
@ParentPackage("basePackage")
public class LoginAction extends BaseAction implements SessionAware,
		ModelDriven<User> {
	private Map<String, Object> sessionMap;

	private User user = new User();

	public UserServiceI userService;
	public RightServiceI rightService;

	public RightServiceI getRightService() {
		return rightService;
	}

	@Autowired
	public void setRightService(RightServiceI rightService) {
		this.rightService = rightService;
	}

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.sessionMap = arg0;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public String toLogin() {
		return "toLogin";
	}

	public String logoff() {
		sessionMap.clear();
		return "toLogin";
	}

	public boolean findUser(){
		boolean flag=false;
		try {
			userService.findValidateUser(user.getUsercode(), DataUtil.md5(user.getPwd()));
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag=false;
		}
		
		return flag;
	}

	/**
	 * 登陆验证
	 * @return 需要跳转的页面
	 */
	public String ValidateUser() {
		try {
			User validuser = null;
			if("".equals(user.getUsercode()) ||user.getUsercode()==null){
				if(sessionMap.get("user")!=null){
					 validuser=(User) sessionMap.get("user");
					 return progressUser(validuser);
				}else{
					addActionError("用户名或密码错误,请重新输入!");
					return "toLogin";
				}
			}else{
				validuser = userService.findValidateUser(user.getUsercode(), DataUtil.md5(user.getPwd()));
				if (validuser == null) {
					addActionError("用户名或密码错误,请重新输入!");
					return "toLogin";
				} else {
					return progressUser(validuser);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		addActionError("用户名或密码错误,请重新输入!");
		return "toLogin";
	}
	
	public void changePassword(){
		ActionContext context=ActionContext.getContext();     
	    Map  parameterMap=context.getParameters();  
	    String[] params = (String[])parameterMap.get("password");
	    String password = params[0];
	    User loginUser = (User)sessionMap.get("user" + getUserCode());
	    Json j = new Json();
	    j.setSuccess(true);
		j.setMsg("修改成功！");
		
	    try {
			userService.changePasswd(loginUser.getUserid(), password);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
	    
		
		super.writeJson(j);
	}

	private String progressUser(User validuser){
		Json j = new Json();
		
		int maxPos = rightService.getMaxRightPos();
		validuser.setRightSum(new long[maxPos + 1]);
		// Set<Role> rs=validuser.getRoles();
		TUser tUser = userService.findUser(validuser.getUsercode());
		Set<TRole> tRoles = tUser.getTRoles();
		System.out.println(tRoles.size());
		List<Role> listRoles = new ArrayList<Role>();
		// Iterator<TRole> iterable=tRoles.iterator();
		for (int i = 0; i < tRoles.size(); i++) {
			Role role = new Role();
			TRole trole = tRoles.iterator().next();
			BeanUtils.copyProperties(trole, role);
			sessionMap.put("urole", role.getRolename());
			sessionMap.put("urole" + validuser.getUsercode(), role.getRolename());
			listRoles.add(role);
		}
		
		sessionMap.put("userRoles", listRoles);
		sessionMap.put("userRoles" + validuser.getUsercode(), listRoles);
		validuser.calculateRightSum(listRoles);
		sessionMap.put("user" + validuser.getUsercode(), validuser);
		sessionMap.put("user", validuser);
		sessionMap.put(Constants.USER_SESSION, ServletActionContext.getRequest().getSession().getId());
		
		j.setMsg("登录成功");
		j.setSuccess(true);
		// super.writeJson(j);
		
		return "touserIndex";
	}
}
