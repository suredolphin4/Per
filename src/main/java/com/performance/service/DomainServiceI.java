package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.BzLevel;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Domain;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.util.ValidateUtil;
import com.performances.model.TBzLevel;
import com.performances.model.TDomain;
import com.performances.model.TRight;
import com.performances.model.TRole;

public interface DomainServiceI {

	public Domain save(Domain domain);

	public DataGrid datagrid(Domain domain);

	public void remove(String ids);

	public Domain edit(Domain domain);

	public List<TDomain> findEntityByHQL(String hql, Object[] objects);

	public List<Domain> ThesisByPoi(FileInputStream fis);

	public String exportExcel();
}
