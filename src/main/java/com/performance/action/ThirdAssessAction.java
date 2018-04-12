package com.performance.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.*;
import com.performance.service.PersonServiceI;
import com.performance.service.ThirdAssessServiceI;
import com.performance.service.UserServiceI;
import com.performance.util.UserAware;
import com.performances.model.TPerson;
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

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 计划
 * 
 * @author peng
 * 
 */

@Action(value = "thirdAssessAction", results = {
		@Result(name = "toThirdAssess", location = "/performance/modules/thirdAssess/thirdAssess.jsp"),
		@Result(name = "toThirdAssessEdit", location = "/performance/jsglEdit.jsp"), })
@Namespace("/")
@ParentPackage("basePackage")
public class ThirdAssessAction extends BaseAction<Ta> implements SessionAware, UserAware,
		ModelDriven<Ta> {
	private User userObj;
	private Map<String, Object> sessionMap;
	private Ta ta = new Ta();

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

	public ThirdAssessServiceI thirdAssessService;

	public ThirdAssessServiceI getThirdAssessService() {
		return thirdAssessService;
	}

	@Autowired
	public void setThirdAssessService(ThirdAssessServiceI thirdAssessService) {
		this.thirdAssessService = thirdAssessService;
	}

	public String toThirdAssess() {

		return "toThirdAssess";
	}

	public String toThirdAssessEdit() {

		return "toThirdAssessEdit";
	}

	public void add() {

		Json j = new Json();
		try {
			// 设置新增论文记录时的时间
			ta.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Ta r = thirdAssessService.save(ta);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(r);
		} catch (DataIntegrityViolationException e) {
			j.setSuccess(false);
			j.setMsg(e.getLocalizedMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		super.writeJson(j);
	}

	/**
	 * 论文属性编辑
	 * 
	 * @author zhengxb
	 */
	public void edit() {
		Json j = new Json();
		try {
			ta.setSavetime(new Timestamp(new java.util.Date().getTime()));
			Ta r = thirdAssessService.edit(ta);
			j.setSuccess(true);
			j.setMsg("修改成功！");
			j.setObj(r);
		} catch (DataIntegrityViolationException e) {
			j.setSuccess(false);// todo 编辑前端需要处理“重复记录”问题
			j.setMsg(e.getLocalizedMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		// TODO 状态需要更改为已保存
		super.writeJson(j);
	}

	/**
	 * 提交论文给管理员审核
	 * 
	 * @author zhengxb
	 */
	public void submit() {
		Json j = new Json();

		try {
			ActionContext context = ActionContext.getContext();
			Map parameterMap = context.getParameters();
			String[] params = (String[]) parameterMap.get("talist"); // 接收lwlist参数
			String json = new String(params[0]);
			JSONObject jsonobj = JSONObject.fromObject(json);
			JSONArray jsonArray = jsonobj.getJSONArray("taList"); // json数组对应的键
			String submitUser = jsonobj.getString("submitUser");
			List<Ta> taList = JSONArray.toList(jsonArray, new Ta(), new JsonConfig());

			// 持久化到数据库
			thirdAssessService.submit(taList, submitUser);

			j.setSuccess(true);
			j.setMsg("提交成功！");
		} catch (Exception e) {
			j.setMsg("提交出现异常！请联系管理员！");
			e.printStackTrace();
		} finally {

		}

		super.writeJson(j);
	}
	
	/**
	 * 撤回已提交规划
	 * @author zheng
	 */
	public void revoke(){
		Json j = new Json();

		try {
			ActionContext context = ActionContext.getContext();
			Map parameterMap = context.getParameters();
			String[] params = (String[]) parameterMap.get("thirdAssesslist"); // 接收lwlist参数
			String json = new String(params[0]);
			JSONObject jsonobj = JSONObject.fromObject(json);
			JSONArray jsonArray = jsonobj.getJSONArray("thirdAssessList"); // json数组对应的键
			List<Ta> thirdAssessList = JSONArray.toList(jsonArray, new Ta(), new JsonConfig());

			// 持久化到数据库
			thirdAssessService.revoke(thirdAssessList);

			j.setSuccess(true);
			j.setMsg("撤回成功！");
		} catch (Exception e) {
			j.setMsg("撤回出现异常！请联系管理员！");
			e.printStackTrace();
		} finally {

		}

		super.writeJson(j);
	}

	/**
	 * 论文审核
	 */
	public void audit() {
		Json j = new Json();

		try {
			ActionContext context = ActionContext.getContext();
			Map parameterMap = context.getParameters();
			String[] params = (String[]) parameterMap.get("audit"); // 接收audit参数
			String json = new String(params[0]);
			JSONObject jsonobj = JSONObject.fromObject(json);
			String auditStatus = jsonobj.getString("audit");
			String auditOpinion = jsonobj.getString("auditOpinion");
			JSONArray jsonArray = jsonobj.getJSONArray("taList"); // json数组对应的键
			List<Ta> taList = JSONArray.toList(jsonArray, new Ta(),
					new JsonConfig());

			thirdAssessService.audit(auditStatus, auditOpinion, taList);

			j.setSuccess(true);
			j.setMsg("审核成功！");
		} catch (Exception e) {
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
					dataGrid=thirdAssessService.datagrid(ta, "", usernames);
				}
				
			}else if("管理员".equals(role.getRolename())){
				dataGrid=thirdAssessService.datagrid(ta, "",null);
				
			}else{
				dataGrid=thirdAssessService.datagrid(ta, getUserCode(),null);
				
			}
		}
		super.writeJson(dataGrid);	
	}

	/**
	 * 删除
	 */
	public void remove() {
		// thesisService.remove(lw.getIds());
		Json j = new Json();
		try {
			ActionContext context = ActionContext.getContext();
			Map parameterMap = context.getParameters();
			String[] params = (String[]) parameterMap.get("remove"); // 接收remove参数
			String json = new String(params[0]);
			JSONObject jsonobj = JSONObject.fromObject(json);
			String ids = jsonobj.getString("taids");

			thirdAssessService.remove(ids);

		j.setSuccess(true);
		j.setMsg("删除成功！");
		} catch (Exception e) {

			j.setSuccess(false);
			j.setMsg("服务端错误，删除失败！");
		}
		super.writeJson(j);
	}

	@Override
	public Ta getModel() {
		// TODO Auto-generated method stub
		return ta;
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
			FileInputStream fis = FileUtils.openInputStream(upload);
			thirdAssessService.ThirdAssessByPoi(fis);
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

		String fileName = thirdAssessService.exportExcel();

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
