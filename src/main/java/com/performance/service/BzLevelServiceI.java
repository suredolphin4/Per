package com.performance.service;

import java.util.List;

import com.performance.pagemodel.BzLevel;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.util.ValidateUtil;
import com.performances.model.TBzLevel;
import com.performances.model.TRight;
import com.performances.model.TRole;

public interface BzLevelServiceI {

	public BzLevel save(BzLevel bzlevel);

	public DataGrid datagrid(BzLevel bzlevel);

	public void remove(String ids);

	public BzLevel edit(BzLevel bzlevel);

	public List<TBzLevel> findEntityByHQL(String hql, Object[] objects);
}
