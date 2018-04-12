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
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Rj;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.service.PersonServiceI;
import com.performance.service.SoftwareServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.UserAware;
import com.performances.model.TPerson;

/**
 * 论文
 * @author peng
 *
 */

@Action(value = "softwareAction", results = { @Result(name = "toSoftware", location = "/performance/modules/software/software.jsp"), @Result(name = "toSoftwareEdit", location = "/performance/jsglEdit.jsp"), })
@Namespace("/")
@ParentPackage("basePackage")
public class SoftwareAction extends BaseAction<Rj> implements SessionAware,UserAware, ModelDriven<Rj> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  User userObj;
	private Map<String, Object> sessionMap;
	private Rj rj = new Rj();
	
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
	public SoftwareServiceI softwareService;

	public SoftwareServiceI getSoftwareService() {
		return softwareService;
	}

	@Autowired
	public void setSoftwareService(SoftwareServiceI softwareService) {
		this.softwareService = softwareService;
	}
	
	public String toSoftware() {

		return "toSoftware";
	}

	public String toSoftwareEdit() {

		return "toSoftwareEdit";
	}

	public void add() {

		Json j = new Json();
		try {
			//设置新增论文记录时的时间
			rj.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Rj r = softwareService.save(rj);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
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
			rj.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Rj r = softwareService.edit(rj);
			j.setSuccess(true);
			j.setMsg("修改成功！");
			j.setObj(r);
		}catch(DataIntegrityViolationException e){
		    j.setSuccess(false);//todo 编辑前端需要处理“重复记录”问题
		    j.setMsg(e.getLocalizedMessage());
		}
		//TODO 状态需要更改为已保存
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
			String[] params = (String[]) parameterMap.get("rjlist");  //接收lwlist参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        JSONArray jsonArray = jsonobj.getJSONArray("rjList");     //json数组对应的键
	        List<Rj> rjList = JSONArray.toList(jsonArray, new Rj(), new JsonConfig());
	        //持久化到数据库
	        softwareService.submit(rjList);
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
	 * 论文审核
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
	        JSONArray jsonArray = jsonobj.getJSONArray("rjList");     //json数组对应的键
	        List<Rj> rjList = JSONArray.toList(jsonArray, new Rj(), new JsonConfig());
	        
	        softwareService.audit(auditStatus, auditOpinion, rjList);
	        
	        j.setSuccess(true);
			j.setMsg("审核成功！");
		}catch(Exception e){
			j.setMsg("审核出现异常！请联系管理员！");
			e.printStackTrace();
		}
		
		super.writeJson(j);
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
					dataGrid=softwareService.datagrid(rj, "", usernames);				
				}
				
			}else if("管理员".equals(role.getRolename())){
				dataGrid=softwareService.datagrid(rj, "",null);
				
			}else{
				dataGrid=softwareService.datagrid(rj, getUserCode(),null);
				
			}
		}
		super.writeJson(dataGrid);	
	}
	/**
	 * 删除所有
	 */
	public void removeAll() {
		//graduateService.remove(lw.getIds());
		Json j = new Json();
		boolean flag=false;
		try{
	        
	        flag= softwareService.removeAll();
	        
			j.setSuccess(flag);
			j.setMsg("删除成功！");
		}catch(Exception e){
			
			j.setSuccess(flag);
			j.setMsg("服务端错误，删除失败！");
		}
		super.writeJson(j);
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
	        String ids = jsonobj.getString("rjids");
	        
	        softwareService.remove(ids);
	        
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch(Exception e){
			
			j.setSuccess(false);
			j.setMsg("服务端错误，删除失败！");
		}
		super.writeJson(j);
	}

	@Override
	public Rj getModel() {
		// TODO Auto-generated method stub
		return rj;
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
			softwareService.SoftByPoi(fis);
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

		String fileName = softwareService.exportExcel();
		
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
		// TODO Auto-generated method stub
		this.userObj=user;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.sessionMap=session;
	}
}
