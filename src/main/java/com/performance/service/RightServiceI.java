package com.performance.service;

import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Right;
import com.performances.model.TRight;

public interface RightServiceI {

	public Right save(Right right);

	public DataGrid datagrid(Right right);

	public void remove(String ids);

	public Right saveOrUpdateRight(Right right);

	public Object uniqueResult(String hql, Object... objects);

	public void appendRightByURL(String url);

	public Right edit(Right right);

	public List<TRight> findRightsInRange(String[] ids);

	public int getMaxRightPos();

	public List<Right> findAllEntities();

	public String exportExcel();

}
