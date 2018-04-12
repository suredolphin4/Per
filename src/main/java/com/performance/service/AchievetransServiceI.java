package com.performance.service;

import com.performance.pagemodel.Achievetrans;
import com.performance.pagemodel.DataGrid;
import com.performances.model.TAchievetrans;

import java.io.FileInputStream;
import java.util.List;

public interface AchievetransServiceI {
	public Achievetrans save(Achievetrans achievetrans);

	public DataGrid datagrid(Achievetrans achievetrans);

	public DataGrid datagrid(Achievetrans achievetrans, String username, List<String> usernames);

	public void remove(String ids);

	public Achievetrans edit(Achievetrans achievetrans);

	public List<TAchievetrans> findRoleInRange(String[] ids);

	List<TAchievetrans> findEntityByHQL(String hql, Object[] objects);

	public void ThesisByPoi(FileInputStream fis);

	public String exportExcel();

	public boolean removeAll();
}
