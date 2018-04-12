package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Yj;
import com.performances.model.TYj;

public interface GraduateServiceI {
	public Yj save(Yj graduate);

	public DataGrid datagrid(Yj graduate);

	public DataGrid datagrid(Yj graduate, String username, List<String> usernames);

	public void remove(String ids);

	public Yj edit(Yj graduate);

	public List<TYj> findRoleInRange(String[] ids);

	List<TYj> findEntityByHQL(String hql, Object[] objects);

	public void ThesisByPoi(FileInputStream fis);

	public String exportExcel();

	public boolean removeAll();
}
