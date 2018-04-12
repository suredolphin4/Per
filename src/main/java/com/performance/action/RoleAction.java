package com.performance.action;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Right;
import com.performance.pagemodel.Role;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.RoleServiceI;
import com.performances.model.TUserCate;

@Action(value = "roleAction")
@Namespace("/")
@ParentPackage("basePackage")
public class RoleAction extends BaseAction implements ModelDriven<Role> {
	private static final Logger logger = Logger.getLogger(RoleAction.class);
	private Role role = new Role();
	private RoleServiceI roleService;

	@Autowired
	private ImportExcelServiceI<TUserCate> _importExcel;
	

	public RoleServiceI getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

	
	@Override
	public Role getModel() {
		// TODO Auto-generated method stub
		return role;
	}

	public void add() {
		
		Json j = new Json();
		try {
			
			Role r = roleService.save(role);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}

		super.writeJson(j);
	}
	
	
	public void datagrid() {
		
		super.writeJson(roleService.datagrid(role));
	}
	
public void remove() {
		
		roleService.remove(role.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

public Role edit(){
	Role r=roleService.edit(role);
	Json j = new Json();
	j.setSuccess(true);
	j.setMsg("修改成功！");
	j.setObj(r);
	super.writeJson(j); 
	return r;
}

public void downloadExcel() throws Exception {
	HttpServletResponse response = ServletActionContext.getResponse();

	String fileName = roleService.exportExcel();
	
	response.setContentType("text/html;charset=utf-8");
	Json j = new Json();
	j.setSuccess(true);
	j.setObj(fileName);
	super.writeJson(j);
}

}
