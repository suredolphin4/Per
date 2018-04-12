package com.performance.service;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.ProjectStatistic;

/**
 * Created by zxbing on 15-11-11.
 * 项目表统计服务
 */
public interface ProjectStatisticServiceI {
	DataGrid datagrid(ProjectStatistic proSta);
	DataGrid datagrid(ProjectStatistic proSta, String usercode);
	String exportExcel();
	boolean calculateProject();
}
