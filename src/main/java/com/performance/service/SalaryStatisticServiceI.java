package com.performance.service;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.SalaryStatistic;

/**
 * Created by zyh on 2016/4/1.
 */
public interface SalaryStatisticServiceI {
    DataGrid datagrid(SalaryStatistic salSta);
    DataGrid datagrid(SalaryStatistic salSta, String usercode);
    String exportExcel();
    boolean calculateSalary();
}

