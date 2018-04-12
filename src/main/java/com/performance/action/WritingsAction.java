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
import com.performance.pagemodel.BaseModel;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Rj;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.pagemodel.Writing;
import com.performance.service.PersonServiceI;
import com.performance.service.UserServiceI;
import com.performance.service.WritingServiceI;
import com.performance.util.UserAware;
import com.performances.model.TPerson;

/**
 * 论文
 * 
 * @author peng
 * 
 */
@Action(value = "writingsAction", results = {
		@Result(name = "toWritings", location = "/performance/modules/writings/writings.jsp"),
		@Result(name = "toThesisEdit", location = "/performance/jsglEdit.jsp"), })
@Namespace("/")
@ParentPackage("basePackage")
public class WritingsAction extends BaseAction<Writing> implements SessionAware, UserAware,
		ModelDriven<Writing> {
	private User userObj;
	private Map<String, Object> sessionMap;

	private Writing wt = new Writing();

	public WritingServiceI writingService;

	public WritingServiceI getWritingService() {
		return writingService;
	}

	@Autowired
	public void setWritingService(WritingServiceI writingService) {
		this.writingService = writingService;
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

	public String toWritings() {
		return "toWritings";
	}

	public String toThesisEdit() {
		return "toThesisEdit";
	}

	public void add() {
		Json j = new Json();
		try {
			wt.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Writing r = writingService.save(wt);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		} catch (DataIntegrityViolationException e) {
			j.setSuccess(false);
			j.setMsg("duplicate row");
			e.printStackTrace();
		}

		super.writeJson(j);
	}
	
	public void edit(){
		Json j = new Json();
		try {
			wt.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Writing r = writingService.edit(wt);
			j.setSuccess(true);
			j.setMsg("修改成功！");
			j.setObj(r);
		}catch(DataIntegrityViolationException e){
		    j.setSuccess(false);
		    j.setMsg("duplicate row");
			e.printStackTrace();
		}
		super.writeJson(j);

	}

	public void datagrid() {
//		User userObj = (User) sessionMap.get("user" +  getUserCode());
//		// rj.setSort("title");
//		super.writeJson(writingService.datagrid(wt, userObj.getUsercode()));
		List<Role> list=(List) sessionMap.get("userRoles" +  getUserCode());
		DataGrid dataGrid=null;
		for (int i = 0; i < list.size(); i++) {
			Role role=list.get(i);
			if("秘书".equals(role.getRolename())){
				String domainString = userService.findUser(getUserCode()).getDomain();
				List<TPerson> tuserList=personService.findPersonsByHQL(domainString);
//				List<TUser> tuserList=userService.findUsersByHQL(domainString);
				List<String> usernames=new  ArrayList<String>();
				for(TPerson tu:tuserList){
					if(tu!=null|| !tu.equals("")){
						//usernames.add(tu.getName());
						usernames.add(tu.getUsercode());
					}
				}
				//user list
				if(usernames.size()!=0){
					dataGrid = writingService.datagrid(wt, "", usernames); //TODO
				}
				
			}else if("管理员".equals(role.getRolename())){
				dataGrid = writingService.datagrid(wt, "", null);//TODO
			}else{
				dataGrid = writingService.datagrid(wt, getUserCode(), null);//TODO	
			}
		}
		super.writeJson(dataGrid);
	}

	public void remove() {
		Json j = new Json();
		try{
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("remove");  //接收remove参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        String ids = jsonobj.getString("wtids");
	        
	        writingService.remove(ids);
	        
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch(Exception e){
			
			j.setSuccess(false);
			j.setMsg("服务端错误，删除失败！");
		}
		super.writeJson(j);
	}

	@Override
	public Writing getModel() {
		return wt;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap = arg0;
	}
	
	/*
	 * 导入著作Excel接口
	 */
	public void upLoadExcel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
			boolean compareVal = this.compareTitle(upload.getPath());
			try {
				if (!compareVal) {
					j.setSuccess(false);
					j.setMsg("导入失败！导入数据有误！!");
					super.writeJson(j);
					return;
				} else {
			FileInputStream fis=FileUtils.openInputStream(upload);
			writingService.importExcel(fis);
			j.setSuccess(true);
			j.setMsg("导入成功！");
			super.writeJson(j);
				}
		} catch (IOException e) {
			j.setSuccess(false);
			j.setMsg("导入失败！导入数据有误！!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 提交著作给管理员审核
	 * @author zhengxb
	 */
	public void submit(){
		Json j = new Json();
		try{			
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("wtlist");  //接收lwlist参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        JSONArray jsonArray = jsonobj.getJSONArray("wtList");     //json数组对应的键
	        String submitUser = jsonobj.getString("submitUser");
	        List<Writing> wtList = JSONArray.toList(jsonArray, new Writing(), new JsonConfig());
	        //持久化到数据库
	        writingService.submit(wtList, submitUser);
	        j.setSuccess(true);
			j.setMsg("提交成功！");
		}catch(Exception e){
			j.setMsg("提交出现异常！请联系管理员！");
			e.printStackTrace();
		}finally{
			
		}
		
		super.writeJson(j);
	}
	
	/**
	 * 撤回已提交著作
	 * @author zheng
	 */
	public void revoke(){
		Json j = new Json();
		try{			
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("wtlist");  //接收lwlist参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        JSONArray jsonArray = jsonobj.getJSONArray("wtList");     //json数组对应的键
	        List<Writing> wtList = JSONArray.toList(jsonArray, new Writing(), new JsonConfig());
	        //持久化到数据库
	        writingService.revoke(wtList);
	        j.setSuccess(true);
			j.setMsg("撤回成功！");
		}catch(Exception e){
			j.setMsg("撤回出现异常！请联系管理员！");
			e.printStackTrace();
		}
		
		super.writeJson(j);
	}
	
	/**
	 * 著作审核
	 */
	public void audit(){
		Json j = new Json();
		
		try{
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("audit");  //接收audit参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        String auditStatus = jsonobj.getString("audit");
	        String auditOpinion = jsonobj.getString("auditOpinion");
	        JSONArray jsonArray = jsonobj.getJSONArray("wtList");     //json数组对应的键
	        List<Writing> lwList = JSONArray.toList(jsonArray, new Writing(), new JsonConfig());
	        
	        writingService.audit(auditStatus, auditOpinion, lwList);
	        
	        j.setSuccess(true);
			j.setMsg("审核成功！");
		}catch(Exception e){
			j.setMsg("审核出现异常！请联系管理员！");
			e.printStackTrace();
		}
		
		super.writeJson(j);
	}

	@Override
	public void setUser(User user) {
		this.userObj=user;
	}
	
	
	public void downloadExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fileName = writingService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}
}
