package com.performance.action;

import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.*;
import com.performance.service.ProjectStatisticServiceI;
import com.performance.util.UserAware;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by zxbing on 15-11-11.
 * 项目统计
 */
@Action(value = "projectstatisticAction", results = {
		@Result(name = "toProjectstatistic", location = "/performance/modules/projectstatistic/projectstatistic.jsp")
})
@Namespace("/")
@ParentPackage("basePackage")
public class ProjectStatisticAction extends BaseAction implements SessionAware, ModelDriven<ProjectStatistic>{

	@Autowired
	private ProjectStatisticServiceI projectStaService;

	private ProjectStatistic projectStatistic = new ProjectStatistic();

	private Map<String, Object> sessionMap;

	@Override
	public ProjectStatistic getModel() {
		return projectStatistic;
	}

	public String toProjectstatistic(){
		return "toProjectstatistic";
	}

	public void datagrid(){
		List<Role> list= (List<Role>)sessionMap.get("userRoles" +  getUserCode());
		Role role = list.get(0);
		DataGrid dataGrid = null;

		//TODO 硬编码,低级错误
		if("管理员".equals(role.getRolename())){
			dataGrid = projectStaService.datagrid(this.projectStatistic);
		}else if("科研人员".equals(role.getRolename())){
			dataGrid = projectStaService.datagrid(this.projectStatistic, getUserCode());
		}

		super.writeJson(dataGrid);
	}

	public void downloadExcel(){
		HttpServletResponse response = ServletActionContext.getResponse();
		String fileName = projectStaService.exportExcel();
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}

	/**
	 * 计算项目统计
	 * @added by zhangshuo 2016.04.05
	 */
	public void calculateProject(){
		Json j = new Json();
		boolean calResult = projectStaService.calculateProject();
		if(calResult){
			j.setSuccess(true);
			j.setMsg("项目统计计算完成");
		}else {
			j.setSuccess(false);
			j.setMsg("项目统计计算失败！");
		}

		super.writeJson(j);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}
}
