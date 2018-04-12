package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Dt;
import com.performance.pagemodel.Gh;
import com.performance.pagemodel.Rj;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.util.ValidateUtil;
import com.performances.model.TDt;
import com.performances.model.TRight;
import com.performances.model.TRj;
import com.performances.model.TRole;

public interface MapServiceI {

	public Dt save(Dt dt);

	public DataGrid datagrid(Dt dt);

//	public DataGrid datagrid(Dt dt, String username, List<String> usernames);
	
	public DataGrid datagrid(Dt dt, String usercode, List<String> usercodes);


	public void remove(String ids);

	public Dt edit(Dt dt);

	public List<TDt> findRoleInRange(String[] ids);

	List<TDt> findEntityByHQL(String hql, Object[] objects);

	public void MapByPoi(FileInputStream fis);

	List<Dt> submit(List<Dt> dtList, String submitUser);
	
	List<Dt> revoke(List<Dt> mapList);

	List<Dt> audit(String audit_status, String auditOpinion, List<Dt> dtList);

	public String exportExcel();
}
