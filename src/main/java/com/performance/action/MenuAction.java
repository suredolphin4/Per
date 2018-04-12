package com.performance.action;

import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.performance.pagemodel.Menu;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.service.MenuServiceI;
import com.performance.util.UserAware;
@ParentPackage("basePackage")
@Namespace("/")
@Action(value = "menuAction", results = { @Result(name = "toMenu", location = "/sysform.jsp")})
public class MenuAction extends BaseAction implements  UserAware,ModelDriven<Menu> {

	private static final Logger logger = Logger.getLogger(MenuAction.class);
	private User user;
	private Menu menu = new Menu();

	private MenuServiceI menuService;

	public MenuServiceI getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}

	@Override
	public Menu getModel() {
		return menu;
	}
	
	public String toMenu() {

		return "toMenu";
	}

	/**
	 * 异步获取树节点
	 */
	public void getTreeNode() {
		super.writeJson(menuService.getTreeNode(menu.getId()));
	}

	public void getAllTreeNode() {
		super.writeJson(menuService.getAllTreeNode());
	}
	public void saveTreeNode() {
		menuService.saveMenu();
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		this.user=user;
	}

	
}
