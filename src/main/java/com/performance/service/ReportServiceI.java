package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Zx;
import com.performances.model.TZx;

public interface ReportServiceI {

	public Zx save(Zx zx);

	public DataGrid datagrid(Zx zx);

	public DataGrid datagrid(Zx zx, String username, List<String> usernames);

	public void remove(String ids);

	public Zx edit(Zx zx);

	public List<TZx> findRoleInRange(String[] ids);

	List<TZx> findEntityByHQL(String hql, Object[] objects);

	public void ReportByPoi(FileInputStream fis);

	List<Zx> submit(List<Zx> zxList, String submitUser);
	
	List<Zx> revoke(List<Zx> zxList);

	List<Zx> audit(String audit_status, String auditOpinion, List<Zx> zxList);

	public String exportExcel();
	// public DataGrid datagrid(Lw lw, List<String> usernames);
}
