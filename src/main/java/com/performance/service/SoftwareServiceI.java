package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Dt;
import com.performance.pagemodel.Rj;
import com.performances.model.TRj;


public interface SoftwareServiceI {

	public Rj save(Rj rj);

	public DataGrid datagrid(Rj rj);

//	public DataGrid datagrid(Rj rj, String username, List<String> usernames);
	
	public DataGrid datagrid(Rj rj, String usercode, List<String> usercodes);

	public void remove(String ids);

	public Rj edit(Rj rj);

	public List<TRj> findRoleInRange(String[] ids);

	List<TRj> findEntityByHQL(String hql, Object[] objects);

	public void SoftByPoi(FileInputStream fis);

	List<Rj> submit(List<Rj> rjList);

	List<Rj> audit(String audit_status, String auditOpinion, List<Rj> rjList);

	public String exportExcel();

	public boolean removeAll();
}
