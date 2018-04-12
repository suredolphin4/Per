package com.performance.service;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Ta;
import com.performances.model.TTa;

import java.io.FileInputStream;
import java.util.List;

public interface ThirdAssessServiceI {

	public Ta save(Ta ta);

	public DataGrid datagrid(Ta ta);

//	public DataGrid datagrid(Gh gh, String username, List<String> usernames);
	
	public DataGrid datagrid(Ta ta, String usercode, List<String> usercodes);

	public void remove(String ids);

	public Ta edit(Ta ta);

	public List<TTa> findRoleInRange(String[] ids);

	List<TTa> findEntityByHQL(String hql, Object[] objects);

	public void ThirdAssessByPoi(FileInputStream fis);

	List<Ta> submit(List<Ta> taList, String submitUser);
	
	List<Ta> revoke(List<Ta> thirdAssessList);

	List<Ta> audit(String audit_status, String auditOpinion, List<Ta> taList);

	public String exportExcel();
}
