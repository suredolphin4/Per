package com.performance.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.*;
import com.performance.service.AchievetransServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.UserAware;
import com.performances.model.TUser;
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

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 专利
 * @author zhangshuo
 *
 */

@Action(value = "achievetransAction", results = {
@Result(name = "toAchievetrans", location = "/performance/modules/achievetrans/achievetrans.jsp") ,
@Result(name = "toAchievetransEdit", location = "/performance/jsglEdit.jsp") ,
})
@Namespace("/")
@ParentPackage("basePackage")
public class AchievetransAction extends BaseAction<Achievetrans> implements SessionAware,UserAware, ModelDriven<Achievetrans> {

	private  User userObj;
	private Map<String, Object> sessionMap;
	private Achievetrans achievetrans = new Achievetrans();
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
	
	public AchievetransServiceI achievetransService;
	public AchievetransServiceI getAchievetransService() {
		return achievetransService;
	}

	@Autowired
	public void setAchievetransService(AchievetransServiceI achievetransService) {
		this.achievetransService = achievetransService;
	}
	
	public String toAchievetrans(){
		
		return "toAchievetrans";
	}
	public String toAchievetransEdit(){
		
		return "toAchievetransEdit";
	}

	public void add() {

		Json j = new Json();
		try {
			//设置新增记录时的时间
			//graduate.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Achievetrans r = achievetransService.save(achievetrans);
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
			Achievetrans r = achievetransService.edit(achievetrans);
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
					dataGrid= achievetransService.datagrid(achievetrans, "", usernames);
				}
				
			}else if("管理员".equals(role.getRolename())){
				dataGrid= achievetransService.datagrid(achievetrans, "",null);
			}else{
				dataGrid= achievetransService.datagrid(achievetrans, getUserCode(),null);
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
	        
	        flag= achievetransService.removeAll();
	        
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

			achievetransService.remove(ids);
	        
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch(Exception e){
			
			j.setSuccess(false);
			j.setMsg("服务端错误，删除失败！");
		}
		super.writeJson(j);
	}

	@Override
	public Achievetrans getModel() {
		// TODO Auto-generated method stub
		return achievetrans;
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
			achievetransService.ThesisByPoi(fis);
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

		String fileName = achievetransService.exportExcel();
		
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
