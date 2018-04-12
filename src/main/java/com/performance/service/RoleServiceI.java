package com.performance.service;

import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.util.ValidateUtil;
import com.performances.model.TRight;
import com.performances.model.TRole;

public interface RoleServiceI {

	public Role save(Role role);

	public DataGrid datagrid(Role role);

	public void remove(String ids);

	public Role edit(Role role);

	public List<TRole> findRoleInRange(String[] ids);

	List<TRole> findEntityByHQL(String hql, Object[] objects);

	public String exportExcel();
}
