package com.performance.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Rj;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.util.ValidateUtil;
import com.performances.model.TLw;
import com.performances.model.TRight;
import com.performances.model.TRj;
import com.performances.model.TRole;

public interface ThesisServiceI {

	public Lw save(Lw lw);

	public DataGrid datagrid(Lw lw);

	public DataGrid datagrid(Lw lw, String usercode, List<String> usercodes);

	public void remove(String ids);

	public Lw edit(Lw lw);

	public List<TLw> findRoleInRange(String[] ids);

	List<TLw> findEntityByHQL(String hql, Object[] objects);

	public void ThesisByPoi(FileInputStream fis);

	List<Lw> submit(List<Lw> lwList, String submitUser);
	
	List<Lw> revoke(List<Lw> lwList);

	List<Lw> audit(String auditStatus, String auditUser, String auditOpinion, List<Lw> lwList);

	public String exportExcel();

	public String exportExcelIncludingScore();
	// public DataGrid datagrid(Lw lw, List<String> usernames);
}
