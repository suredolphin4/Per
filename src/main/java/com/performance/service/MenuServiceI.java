package com.performance.service;

import java.util.List;

import com.performance.pagemodel.Menu;

public interface MenuServiceI {

	public List<Menu> getTreeNode(String id);

	public List<Menu> getAllTreeNode();

	public void saveMenu();

}
