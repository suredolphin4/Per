package com.performance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Examine;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.service.PersonServiceI;
import com.performance.service.ThesisServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.UserAware;
import com.performances.model.TPerson;
import com.performances.model.TUser;

/**
 * 论文
 * 
 * @author peng
 * 
 */
@Action(value = "thesisAction", results = { @Result(name = "toThesis", location = "/performance/modules/thesis/thesis.jsp"), @Result(name = "toThesisEdit", location = "/performance/jsglEdit.jsp"), })
@Namespace("/")
@ParentPackage("basePackage")
public class ThesisAction extends BaseAction<Lw> implements SessionAware,UserAware, ModelDriven<Lw> {
	private User userObj;
	private Map<String, Object> sessionMap;
	private Lw lw = new Lw();
	
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
	public ThesisServiceI thesisService;

	public ThesisServiceI getThesisService() {
		return thesisService;
	}

	@Autowired
	public void setThesisService(ThesisServiceI thesisService) {
		this.thesisService = thesisService;
	}
	
	public String toThesis() {

		return "toThesis";
	}

	public String toThesisEdit() {

		return "toThesisEdit";
	}

	public void add() {
		Json j = new Json();
		try {
			//设置新增论文记录时的时间
			lw.setSavetime(new java.util.Date());
			Lw r = thesisService.save(lw);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		}catch (DataIntegrityViolationException e) {
			j.setSuccess(false);
			j.setMsg("duplicate row");
			e.printStackTrace();
		} 
		super.writeJson(j);
	}
	
	/**
	 * 论文属性编辑
	 * @author zhengxb
	 */
	public void edit(){
		Json j = new Json();
		try {
			lw.setSavetime(new java.util.Date());
			Lw r = thesisService.edit(lw);
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
	
	/**
	 * 提交论文给管理员审核
	 * @author zhengxb
	 */
	public void submit(){
		Json j = new Json();
		try{			
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("lwlist");  //接收lwlist参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        JSONArray jsonArray = jsonobj.getJSONArray("lwList");     //json数组对应的键
	        String submitUser = jsonobj.getString("submitUser");
	        List<Lw> lwList = JSONArray.toList(jsonArray, new Lw(), new JsonConfig());
	        //持久化到数据库
	        thesisService.submit(lwList, submitUser);
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
	 * 撤销提交论文
	 * @author zheng
	 */
	public void revoke(){
		Json j = new Json();
		try{			
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("lwlist");  //接收lwlist参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        JSONArray jsonArray = jsonobj.getJSONArray("lwList");     //json数组对应的键
	     	List<Lw> lwList = JSONArray.toList(jsonArray, new Lw(), new JsonConfig());
	     	
	        //持久化到数据库
	        thesisService.revoke(lwList);
	        j.setSuccess(true);
			j.setMsg("撤销提交成功！");
		}catch(Exception e){
			j.setMsg("撤销提交出现异常！请联系管理员！");
			e.printStackTrace();
		}
		
		super.writeJson(j);
	}
	
	/**
	 * 论文审核
	 * @author zheng
	 */
	public void audit(){
		Json j = new Json();
		
		try{
			ActionContext context = ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("audit");  //接收audit参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        String auditStatus = jsonobj.getString("audit");
	        String auditOpinion = jsonobj.getString("auditOpinion");
	        String auditUser = jsonobj.getString("auditUser");
	        JSONArray jsonArray = jsonobj.getJSONArray("lwList");     //json数组对应的键
	        List<Lw> lwList = JSONArray.toList(jsonArray, new Lw(), new JsonConfig());
	        
	        thesisService.audit(auditStatus, auditUser, auditOpinion, lwList);
	        
	        j.setSuccess(true);
			j.setMsg("审核成功！");
		}catch(Exception e){
			j.setMsg("审核出现异常！请联系管理员！");
			e.printStackTrace();
		}
		
		super.writeJson(j);
	}
	
	/**
	 * 论文读取
	 */
	public void datagrid() {
		List<Role> list=(List) sessionMap.get("userRoles" + getUserCode());
		DataGrid dataGrid=null;
		for (int i = 0; i < list.size(); i++) {
			Role role=list.get(i);
			if("秘书".equals(role.getRolename())){
				String domainString=userService.findUser(getUserCode()).getDomain();
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
					dataGrid=thesisService.datagrid(lw, "", usernames);
//					dataGrid=thesisService.datagrid(lw,usernames);
				}
				
			}else if("管理员".equals(role.getRolename())){
				dataGrid=thesisService.datagrid(lw, "",null);
//				dataGrid=thesisService.datagrid(lw, userObj.getName());
				
			}else if("科研人员".equals(role.getRolename())){
				dataGrid=thesisService.datagrid(lw, getUserCode(),null);
//				dataGrid=thesisService.datagrid(lw, userObj.getName(),null);
				
			}
		}
		super.writeJson(dataGrid);
	}
	
	/**
	 * 删除
	 */
	public void remove() {
		//thesisService.remove(lw.getIds());
		Json j = new Json();
		try{
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("remove");  //接收remove参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        String ids = jsonobj.getString("lwids");
	        
	        thesisService.remove(ids);
	        
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch(Exception e){
			
			j.setSuccess(false);
			j.setMsg("服务端错误，删除失败！");
		}
		super.writeJson(j);
	}


	@Override
	public Lw getModel() {
		return lw;
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
			thesisService.ThesisByPoi(fis);
			j.setSuccess(true);
			j.setMsg("导入成功！");
			super.writeJson(j);
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void downloadExcelIncludingScore() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fileName = thesisService.exportExcelIncludingScore();

		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);

	}

	public void downloadExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fileName = thesisService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
		
//		InputStream is = new FileInputStream(file);
//		response.setHeader("Content-Disposition", "attachment; filename=\"test.xls\"");
//		//response.setHeader("Content-Length", Long.toString(file.getUsableSpace()));
//		response.setCharacterEncoding("GBK");
//		response.setContentType( "application/vnd.ms-excel");	//application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//		FileCopyUtils.copy(is, response.getOutputStream());
//		// close stream and return to view
//	    response.flushBuffer();
	    
		
		//// delete file on server file system
		//file.delete();
	}
	
	@Override
	public void setUser(User user) {
		this.userObj=user;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap=session;
	}
	
}

