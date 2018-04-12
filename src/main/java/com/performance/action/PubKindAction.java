package com.performance.action;

import com.performance.pagemodel.PubKind;
import com.performance.pagemodel.PubPart;
import com.performance.service.PubKindServiceI;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 刊物类别
 * 
 * @author zheng
 * 
 */
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "pubKindAction")
public class PubKindAction extends BaseAction implements ModelDriven<PubKind> {
	private static final Logger logger = Logger.getLogger(PubKindAction.class);
	private PubKindServiceI pubKindService;
	private PubKind pubKind = new PubKind();

	public PubKindServiceI getPubKindService() {
		return pubKindService;
	}
	
	@Autowired
	public void setPubKindService(PubKindServiceI pubKindService) {
		this.pubKindService = pubKindService;
	}
	
	public void datagrid() {
		super.writeJson(pubKindService.datagrid(this.pubKind));
	}
	
	@Override
	public PubKind getModel() {
		return this.pubKind;
	}
	
}
