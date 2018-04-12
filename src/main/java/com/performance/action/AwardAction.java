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
import com.performance.pagemodel.Award;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.pagemodel.Writing;
import com.performance.service.AwardServiceI;
import com.performance.service.PersonServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.UserAware;
import com.performances.model.TPerson;

/**
 * 论文
 * @author peng
 *
 */
@Action(value = "awardAction", results = { 
@Result(name = "toAward", location = "/performance/modules/award/award.jsp") ,
})
@Namespace("/")
@ParentPackage("basePackage")
public class AwardAction extends BaseAction<Award> implements SessionAware, UserAware, ModelDriven<Award> {
	private User userObj;
	private Map<String, Object> sessionMap;
	private Award awd = new Award();
	
	@Autowired
	private AwardServiceI awardService;
	
	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private PersonServiceI personService;
	
	public String toAward(){
		return "toAward";
	}

	@Override
	public Award getModel() {
		// TODO Auto-generated method stub
		return this.awd;
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
				j.setMsg("导入失败！导入数据有误！");
				return;
			} else {
			FileInputStream fis=FileUtils.openInputStream(upload);
			awardService.importExcel(fis);
			j.setSuccess(true);
			j.setMsg("导入成功！");
			super.writeJson(j);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获奖项读取
	 */
	public void datagrid() {
//		User userObj = (User) sessionMap.get("user" +  getUserCode());
//		// rj.setSort("title");
//		super.writeJson(writingService.datagrid(wt, userObj.getUsercode()));
		List<Role> list=(List) sessionMap.get("userRoles" + getUserCode());
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
					dataGrid = awardService.datagrid(awd, "", usernames); //TODO
				}
				
			}else if("管理员".equals(role.getRolename())){
				dataGrid = awardService.datagrid(awd, "", null);//TODO
			}else{
				dataGrid = awardService.datagrid(awd, getUserCode(), null);//TODO	
			}
		}
		super.writeJson(dataGrid);
	}
	
	/**
	 * 增加获奖项
	 */
	public void append(){
		Json j = new Json();
		try {
			//设置新增论文记录时的时间
			awd.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Award r = awardService.save(awd);
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
	 * 删除获奖项
	 */
	public void remove(){
		Json j = new Json();
		try{
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("remove");  //接收remove参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        String ids = jsonobj.getString("ids");
	        
	        awardService.remove(ids);
	        
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch(Exception e){
			j.setSuccess(false);
			j.setMsg("服务端错误，删除失败！");
		}
		super.writeJson(j);
	}
	
	/**
	 * 编辑获奖项
	 */
	public void edit(){
		Json j = new Json();
		try {
			awd.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Award r = awardService.edit(awd);
			j.setSuccess(true);
			j.setMsg("修改成功！");
			j.setObj(r);
		}catch(DataIntegrityViolationException e){
		    j.setSuccess(false);
		    j.setMsg("duplicate row");
		}
		super.writeJson(j);
	}
	
	/**
	 * 提交获奖给管理员审核
	 * @author zhengxb
	 */
	public void submit(){
		Json j = new Json();
		try{			
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("awdlist");  //接收lwlist参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        JSONArray jsonArray = jsonobj.getJSONArray("awdList");     //json数组对应的键
	        String submitUser = jsonobj.getString("submitUser");
	        List<Award> awdList = JSONArray.toList(jsonArray, new Award(), new JsonConfig());
	        //持久化到数据库
	        awardService.submit(awdList, submitUser);
	        j.setSuccess(true);
			j.setMsg("提交成功！");
		}catch(Exception e){
			j.setMsg("提交出现异常！请联系管理员！");
			e.printStackTrace();
		}
		
		super.writeJson(j);
	}
	
	/**
	 * 撤回已提交奖励
	 * @author zheng
	 */
	public void revoke(){
		Json j = new Json();
		try{			
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("awdlist");  //接收lwlist参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        JSONArray jsonArray = jsonobj.getJSONArray("awdList");     //json数组对应的键
	        List<Award> awdList = JSONArray.toList(jsonArray, new Award(), new JsonConfig());
	        //持久化到数据库
	        awardService.revoke(awdList);
	        j.setSuccess(true);
			j.setMsg("撤回成功！");
		}catch(Exception e){
			j.setMsg("撤回已提交奖励出现异常！请联系管理员！");
			e.printStackTrace();
		}
		
		super.writeJson(j);
	}
	
	/**
	 * 获奖项审核
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
	        JSONArray jsonArray = jsonobj.getJSONArray("awdList");     //json数组对应的键
	        List<Award> awdList = JSONArray.toList(jsonArray, new Award(), new JsonConfig());
	        
	        awardService.audit(auditStatus, auditOpinion, awdList);
	        
	        j.setSuccess(true);
			j.setMsg("审核成功！");
		}catch(Exception e){
			j.setMsg("审核出现异常！请联系管理员！");
			e.printStackTrace();
		}
		
		super.writeJson(j);
	}
	
	
	public void downloadExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fileName = awardService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}
}
