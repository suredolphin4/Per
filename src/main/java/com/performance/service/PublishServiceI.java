package com.performance.service;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Publish;
import com.performances.model.TPublish;

import java.io.FileInputStream;
import java.util.List;

public interface PublishServiceI {

	public Publish save(Publish publish);

	public DataGrid datagrid(Publish publish);
	
	public DataGrid datagrid(Publish publish, String usercode, List<String> usercodes);

	public void remove(String ids);

	public Publish edit(Publish publish);

	public List<TPublish> findRoleInRange(String[] ids);

	List<TPublish> findEntityByHQL(String hql, Object[] objects);

	public void PublishByPoi(FileInputStream fis);

	List<Publish> submit(List<Publish> publishList, String submitUser);
	
	List<Publish> revoke(List<Publish> publishList);

	List<Publish> audit(String audit_status, String auditOpinion, List<Publish> publishList);

	public String exportExcel();
}
