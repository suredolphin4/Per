package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Writing;
import com.performances.model.Twriting;

public interface WritingServiceI {

	public Writing save(Writing wt);

	public DataGrid datagrid(Writing wt);

	public DataGrid datagrid(Writing wt, String usercode, List<String> usercodes);

	public void remove(String ids);

	public Writing edit(Writing wt);

	List<Twriting> findEntityByHQL(String hql, Object[] objects);
	
	void importExcel(FileInputStream fis);
	
	String exportExcel();
	
	List<Writing> submit(List<Writing> wtList, String submitUser);
	
	List<Writing> revoke(List<Writing> wtList);

	List<Writing> audit(String audit_status, String auditOpinion, List<Writing> wtList);
}
