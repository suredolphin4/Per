package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.BzLevel;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.LwStutas;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.util.ValidateUtil;
import com.performances.model.TBzLevel;
import com.performances.model.TLwStutas;
import com.performances.model.TRight;
import com.performances.model.TRole;

public interface LwStutasServiceI {

	public LwStutas save(LwStutas lwStutas);

	public DataGrid datagrid(LwStutas lwStutas);

	DataGrid getLwStatusList();

	public void remove(String ids);

	public LwStutas edit(LwStutas lwStutas);

	public List<TLwStutas> findEntityByHQL(String hql, Object[] objects);

	public List<LwStutas> ThesisByPoi(FileInputStream fis);

	public String exportExcel();
}
