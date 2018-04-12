package com.performance.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Research;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.service.PersonServiceI;
import com.performance.service.ResearchServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.UserAware;
import com.performances.model.TPerson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Action(value = "researchAction", results = { 
@Result(name = "toResearch", location = "/performance/modules/research/research.jsp") ,
@Result(name = "toResearchEdit", location = "/performace/jsglEdit.jsp") ,
})
@Namespace("/")
@ParentPackage("basePackage")
public class ResearchAction extends BaseAction<Research> implements SessionAware,UserAware, ModelDriven<Research> {
	private  User userObj;
	private Map<String, Object> sessionMap;
	private Research zx = new Research();
	
	private Map parameters; //接收参数
	public void setParameters(Map parameters) {
	    this.parameters = parameters;
	}
	
	public PersonServiceI personService;
	public PersonServiceI getPersonService() {
		return personService;
	}
	@Autowired
	public void setPersonService(PersonServiceI personService) {
		this.personService = personService;
	}
	
	public UserServiceI userService;
	public UserServiceI getUserService() {
		return userService;
	}
	
	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	public ResearchServiceI researchService;

	public ResearchServiceI getResearchService() {
		return researchService;
	}

	@Autowired
	public void setResearchService(ResearchServiceI researchService) {
		this.researchService = researchService;
	}
	
	public String toResearch(){
		
		return "toResearch";
	}
	public String toResearchEdit(){
		
		return "toResearchEdit";
	}
	
	
	/**
	 * 咨询报告读取
	 */
	public void datagrid() {
		List<Role> list=(List) sessionMap.get("userRoles" +  getUserCode());
		DataGrid dataGrid=null;
		for (int i = 0; i < list.size(); i++) {
			Role role=list.get(i);
			if("秘书".equals(role.getRolename())){
				String domainString=userService.findUser(getUserCode()).getDomain();
				List<TPerson> tuserList=personService.findPersonsByHQL(domainString);
				List<String> usernames=new  ArrayList<String>();
				for(TPerson tu:tuserList){
					if(tu!=null|| !tu.equals("")){					
						usernames.add(tu.getUsercode());
					}
				}
				//user list
				if(usernames.size()!=0){
					dataGrid=researchService.datagrid(zx, "", usernames);				
				}
				
			}else if("管理员".equals(role.getRolename())){
				dataGrid=researchService.datagrid(zx, "",null);
				
			}else{
				dataGrid=researchService.datagrid(zx, getUserCode(),null);
				
			}
		}
		super.writeJson(dataGrid);	
	}
	
	
	public void upLoadExcel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
			boolean compareVal = this.compareTitle(upload.getPath());
			try {
				if (!compareVal) {
					j.setSuccess(false);
					j.setMsg("导入失败！导入数据有误！");
					return;
				} else {
			FileInputStream fis=FileUtils.openInputStream(upload);
			researchService.ReportByPoi(fis);
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

		String fileName = researchService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
		
	}
	
	
	@Override
	public Research getModel() {
		// TODO Auto-generated method stub
		return zx;
	}
	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		this.userObj=user;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.sessionMap=session;
	}
}
