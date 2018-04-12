package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Zl;
import com.performances.model.TZl;

public interface PatentServiceI {
	public Zl save(Zl graduate);

	public DataGrid datagrid(Zl graduate);

	public DataGrid datagrid(Zl graduate, String username, List<String> usernames);

	public void remove(String ids);

	public Zl edit(Zl graduate);

	public List<TZl> findRoleInRange(String[] ids);

	List<TZl> findEntityByHQL(String hql, Object[] objects);

	public void ThesisByPoi(FileInputStream fis);

	public String exportExcel();

	public boolean removeAll();
}
