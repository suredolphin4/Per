package com.performance.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.performance.pagemodel.Role;
import com.performance.pagemodel.Sk;
import com.performance.pagemodel.User;
import com.performance.pagemodel.Yj;
import com.performance.service.GraduateServiceI;
import com.performance.service.LessonsServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.UserAware;
import com.performances.model.TUser;

/**
 * 授课
 * @author peng
 *
 */

@Action(value = "lessonsAction", results = { 
@Result(name = "toLessons", location = "/performance/modules/lessons/lessons.jsp") ,
@Result(name = "toLessonsEdit", location = "/performace/jsglEdit.jsp") ,
})
@Namespace("/")
@ParentPackage("basePackage")
public class LessonsAction extends BaseAction<Sk> implements SessionAware,UserAware, ModelDriven<Sk> {

	private  User userObj;
	private Map<String, Object> sessionMap;
	private Sk graduate = new Sk();
	
	private Map parameters; //接收参数
	public void setParameters(Map parameters) {
	    this.parameters = parameters;
	}
	
	public UserServiceI userService;
	public UserServiceI getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
	public LessonsServiceI graduateService;
	public LessonsServiceI getLessonsService() {
		return graduateService;
	}

	@Autowired
	public void setLessonsService(LessonsServiceI graduateService) {
		this.graduateService = graduateService;
	}
	
	public String toLessons(){
		
		return "toLessons";
	}
	public String toThesisEdit(){
		
		return "toLessonsEdit";
	}

	public void add() {

		Json j = new Json();
		try {
			//设置新增论文记录时的时间
			//graduate.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Sk r = graduateService.save(graduate);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		}catch(DataIntegrityViolationException e){
		    j.setSuccess(false);
		    j.setMsg(e.getLocalizedMessage());
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		} 
		super.writeJson(j);
	}
	
	/**
	 * 论文属性编辑
	 * @author Linyh
	 */
	public void edit(){
		Json j = new Json();
		
		try {
			Sk r = graduateService.edit(graduate);
			j.setSuccess(true);
			j.setMsg("修改成功！");
			j.setObj(r);
		}catch(DataIntegrityViolationException e){
		    j.setSuccess(false);//todo 编辑前端需要处理“重复记录”问题
		    j.setMsg(e.getLocalizedMessage());
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		
		//TODO 状态需要更改为已保存
		super.writeJson(j);
	}
	
	/**
	 * 论文读取
	 */
	public void datagrid() {
		List<Role> list=(List) sessionMap.get("userRoles" +  getUserCode());
		DataGrid dataGrid=null;
		for (int i = 0; i < list.size(); i++) {
			Role role=list.get(i);
			if("秘书".equals(role.getRolename())){
				String domainString=userService.findUser(getUserCode()).getDomain();
				List<TUser> tuserList=userService.findUsersByHQL(domainString);
				List<String> usernames=new  ArrayList<String>();
				for(TUser tu:tuserList){
					if(tu!=null|| !tu.equals("")){
						usernames.add(tu.getUsercode());
					}
				}
				//user list
				if(usernames.size()!=0){
					dataGrid=graduateService.datagrid(graduate, "", usernames);
				}
				
			}else if("管理员".equals(role.getRolename())){
				dataGrid=graduateService.datagrid(graduate, "",null);
			}else{
				dataGrid=graduateService.datagrid(graduate, getUserCode(),null);
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
	        
	        flag= graduateService.removeAll();
	        
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
		//graduateService.remove(lw.getIds());
		Json j = new Json();
		try{
			ActionContext context=ActionContext.getContext();     
		    Map  parameterMap=context.getParameters();  
			String[] params = (String[]) parameterMap.get("remove");  //接收remove参数
			String json = new String(params[0]);
	        JSONObject jsonobj = JSONObject.fromObject(json);
	        String ids = jsonobj.getString("ids");
	        
	        graduateService.remove(ids);
	        
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch(Exception e){
			
			j.setSuccess(false);
			j.setMsg("服务端错误，删除失败！");
		}
		super.writeJson(j);
	}

	@Override
	public Sk getModel() {
		// TODO Auto-generated method stub
		return graduate;
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
			graduateService.ThesisByPoi(fis);
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

		String fileName = graduateService.exportExcel();
		
		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
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
