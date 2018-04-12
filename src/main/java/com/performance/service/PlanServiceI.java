package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Gh;
import com.performance.pagemodel.Lw;
import com.performances.model.TGh;
import com.performances.model.TLw;

public interface PlanServiceI {

	public Gh save(Gh gh);

	public DataGrid datagrid(Gh gh);

//	public DataGrid datagrid(Gh gh, String username, List<String> usernames);
	
	public DataGrid datagrid(Gh gh, String usercode, List<String> usercodes);

	public void remove(String ids);

	public Gh edit(Gh gh);

	public List<TGh> findRoleInRange(String[] ids);

	List<TGh> findEntityByHQL(String hql, Object[] objects);

	public void PlanByPoi(FileInputStream fis);

	List<Gh> submit(List<Gh> ghList, String submitUser);
	
	List<Gh> revoke(List<Gh> planList);

	List<Gh> audit(String audit_status, String auditOpinion, List<Gh> ghList);

	public String exportExcel();
}
