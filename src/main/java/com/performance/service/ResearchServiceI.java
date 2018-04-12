package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Research;
import com.performances.model.TResearch;

public interface ResearchServiceI {
	public Research save(Research zx);

	public DataGrid datagrid(Research zx);

	public DataGrid datagrid(Research zx, String username, List<String> usernames);

	public void remove(String ids);

	public Research edit(Research zx);

	public List<TResearch> findRoleInRange(String[] ids);

	List<TResearch> findEntityByHQL(String hql, Object[] objects);

	public void ReportByPoi(FileInputStream fis);

	public String exportExcel();
}
