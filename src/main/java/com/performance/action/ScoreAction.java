package com.performance.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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
import com.performance.pagemodel.Gh;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.Score;
import com.performance.pagemodel.User;
import com.performance.service.PersonServiceI;
import com.performance.service.PlanServiceI;
import com.performance.service.ScoreServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.UserAware;
import com.performances.model.TPerson;
import com.performances.model.TUser;

/**
 * 计划
 * 
 * @author peng
 * 
 */

@Action(value = "scoreAction", results = {
		@Result(name = "toScore", location = "/performance/modules/score/score.jsp"),
		@Result(name = "toScoreEdit", location = "/performance/jsglEdit.jsp"), })
@Namespace("/")
@ParentPackage("basePackage")
public class ScoreAction extends BaseAction implements SessionAware, UserAware,
		ModelDriven<Score> {
	private User userObj;
	private Map<String, Object> sessionMap;
	private Score gh = new Score();

	private Map parameters; // 接收参数

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

	public ScoreServiceI scoreService;

	public ScoreServiceI getScoreService() {
		return scoreService;
	}

	@Autowired
	public void setScoreService(ScoreServiceI scoreService) {
		this.scoreService = scoreService;
	}

	public String toScore() {

		return "toScore";
	}

	public String toScoreEdit() {

		return "toScoreEdit";
	}


	/**
	 * 读取
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
					dataGrid=scoreService.datagrid(gh, "", usernames);				
				}
				
			}else if("管理员".equals(role.getRolename())){
				dataGrid=scoreService.datagrid(gh, "",null);
				
			}else{
				dataGrid=scoreService.datagrid(gh, getUserCode(),null);
				
			}
		}
		super.writeJson(dataGrid);	
	}

	
	@Override
	public Score getModel() {

		return gh;
	}

	public void downloadExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fileName = scoreService.exportExcel();

		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		this.userObj = user;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.sessionMap = session;
	}

}
