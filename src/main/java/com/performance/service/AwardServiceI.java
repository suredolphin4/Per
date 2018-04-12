package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Award;
import com.performances.model.TAward;;

public interface AwardServiceI {
	public Award save(Award awd);

	public DataGrid datagrid(Award awd);

	public DataGrid datagrid(Award awd, String usercode, List<String> usercodes);

	public void remove(String ids);

	public Award edit(Award wt);

	List<TAward> findEntityByHQL(String hql, Object[] objects);
	
	void importExcel(FileInputStream fis);
	
	String exportExcel();
	
	List<Award> submit(List<Award> awdList, String submitUser);
	
	List<Award> revoke(List<Award> awdList);

	List<Award> audit(String auditStatus, String auditOpinion, List<Award> awdList);
}
