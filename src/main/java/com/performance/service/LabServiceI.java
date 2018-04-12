package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.BzLevel;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Domain;
import com.performance.pagemodel.Lab;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Role;
import com.performance.pagemodel.User;
import com.performance.util.ValidateUtil;
import com.performances.model.TBzLevel;
import com.performances.model.TDomain;
import com.performances.model.TLab;
import com.performances.model.TRight;
import com.performances.model.TRole;

public interface LabServiceI {

	public Lab save(Lab lab);

	public DataGrid datagrid(Lab lab);

	public void remove(String ids);

	public Lab edit(Lab lab);

	public List<TLab> findEntityByHQL(String hql, Object[] objects);

	public List<Lab> ThesisByPoi(FileInputStream fis);

	public String exportExcel();

}
