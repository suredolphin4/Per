package com.performance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Person;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.service.PersonServiceI;
import com.performance.service.UserServiceI;

@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "personAction")
public class PersonAction extends BaseAction<Person> implements
		ModelDriven<Person> {
	private Person user = new Person();

	@Override
	public Person getModel() {
		return user;
	}

	private static final Logger logger = Logger.getLogger(PersonAction.class);
	private PersonServiceI personService;

	public PersonServiceI getPersonService() {
		return personService;
	}

	@Autowired
	public void setPersonService(PersonServiceI personService) {
		this.personService = personService;
	}

	public void add() {
		Json j = new Json();
		try {
			Person u = personService.save(user);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}

	public void datagrid() {
		super.writeJson(personService.datagrid(user));
	}

	public void findPerson() {
		super.writeJson(personService.getPeronByCode(user.getUsercode()));
	}

	public void remove() {

		personService.remove(user.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	public Person edit() {
		Person r = personService.edit(user);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(r);
		super.writeJson(j);
		return r;
	}

	public void upLoadExcel() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String filenameString = user.getUrl();
		Json j = new Json();
		boolean compareVal = this.compareTitle(filenameString);
		try {
			if (!compareVal) {
				j.setSuccess(false);
				j.setMsg("导入失败！导入数据有误！");
				return;
			} else {
				FileInputStream fis = FileUtils.openInputStream(new File(
						filenameString));
				List<Person> userlist = personService.ThesisByPoi(fis);
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

		String fileName = personService.exportExcel();

		response.setContentType("text/html;charset=utf-8");
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(fileName);
		super.writeJson(j);
	}

}
