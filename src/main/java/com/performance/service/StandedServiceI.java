package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.Bz;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Gh;
import com.performances.model.TBz;


public interface StandedServiceI {

	public Bz save(Bz bz);

	public DataGrid datagrid(Bz bz);

//	public DataGrid datagrid(Bz bz, String username, List<String> usernames);
	
	public DataGrid datagrid(Bz bz, String usercode, List<String> usercodes);

	public void remove(String ids);

	public Bz edit(Bz bz);

	public List<TBz> findRoleInRange(String[] ids);

	List<TBz> findEntityByHQL(String hql, Object[] objects);

	public void StandedByPoi(FileInputStream fis);

	List<Bz> submit(List<Bz> bzList, String submitUser);
	
	List<Bz> revoke(List<Bz> standardList);

	List<Bz> audit(String audit_status, String auditOpinion, List<Bz> zxList);
	
	public String exportExcel();

	// public DataGrid datagrid(Lw lw, List<String> usernames);
}
