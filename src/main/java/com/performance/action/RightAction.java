package com.performance.action;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

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
import com.performance.service.RightServiceI;
import com.performance.service.RoleServiceI;
import com.performance.util.ExtractAllRightsUtil;

@Action(value = "rightAction")
@Namespace("/")
@ParentPackage("basePackage")
public class RightAction extends BaseAction implements ModelDriven<Right> {
	private static final Logger logger = Logger.getLogger(RightAction.class);
	private RightServiceI rightService;
	private List<Right> rights;
	
	

	public List<Right> getRights() {
		return rights;
	}
	public void setRights(List<Right> rights) {
		this.rights = rights;
	}
	public RightServiceI getRightService() {
		return rightService;
	}
	@Autowired
	public void setRightService(RightServiceI rightService) {
		this.rightService = rightService;
	}

	private Right right=new Right();
	@Override
	public Right getModel() {
		// TODO Auto-generated method stub
		return right;
	}

	public void add() {
		Json j = new Json();
		try {
			Right r = rightService.saveOrUpdateRight(right);
//			Right r = rightService.save(right);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);
	}
	
	
	public void datagrid() {
		
		super.writeJson(rightService.datagrid(right));
	}

	
public void remove() {
		
	rightService.remove(right.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}


public Right edit(){
	Right r=rightService.edit(right);
	Json j = new Json();
	j.setSuccess(true);
	j.setMsg("修改成功！");
	j.setObj(r);
	super.writeJson(j); 
	return r;
}

public String saveOrUpdateRight(){
	rightService.saveOrUpdateRight(right);
	return "findAllRightAction";
}


public void downloadExcel() throws Exception {
	HttpServletResponse response = ServletActionContext.getResponse();

	String fileName = rightService.exportExcel();
	
	response.setContentType("text/html;charset=utf-8");
	Json j = new Json();
	j.setSuccess(true);
	j.setObj(fileName);
	super.writeJson(j);
}






}
