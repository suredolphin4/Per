package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Xm;
import com.performances.model.TXm;

public interface ProjectServiceI {
	public Xm save(Xm graduate);

	public DataGrid datagrid(Xm graduate);

	public DataGrid datagrid(Xm graduate, String username, List<String> usernames);

	public void remove(String ids);

	public Xm edit(Xm graduate);

	public List<TXm> findRoleInRange(String[] ids);

	List<TXm> findEntityByHQL(String hql, Object[] objects);

	public void ThesisByPoi(FileInputStream fis);

	public String exportExcel();

	boolean removeAll();
}
