package com.performance.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.BzLevel;
import com.performance.pagemodel.Json;
import com.performance.pagemodel.User;
import com.performance.service.BzLevelServiceI;
import com.performance.service.UserServiceI;
/**
 * 标准的级别
 * @author peng
 *
 */

@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "bzLevelAction")
public class BzLevelAction extends BaseAction implements ModelDriven<BzLevel> {
	private BzLevel bzLevel = new BzLevel();

	@Override
	public BzLevel getModel() {
		return bzLevel;
	}

	private static final Logger logger = Logger.getLogger(BzLevelAction.class);

	private BzLevelServiceI bzLevelService;

	

	public BzLevelServiceI getBzLevelService() {
		return bzLevelService;
	}
	@Autowired
	public void setBzLevelService(BzLevelServiceI bzLevelService) {
		this.bzLevelService = bzLevelService;
	}


	public void add() {
		Json j = new Json();
		try {
			BzLevel  u = bzLevelService.save(bzLevel);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}


	public void datagrid() {
		super.writeJson(bzLevelService.datagrid(bzLevel));
	}

	public void remove() {
		
		bzLevelService.remove(bzLevel.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
}
