package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Sk;
import com.performances.model.TSk;

public interface LessonsServiceI {
	public Sk save(Sk lessons);

	public DataGrid datagrid(Sk lessons);

	public DataGrid datagrid(Sk lessons, String username, List<String> usernames);

	public void remove(String ids);

	public Sk edit(Sk lessons);

	public List<TSk> findRoleInRange(String[] ids);

	List<TSk> findEntityByHQL(String hql, Object[] objects);

	public void ThesisByPoi(FileInputStream fis);

	public String exportExcel();

	public boolean removeAll();
}
