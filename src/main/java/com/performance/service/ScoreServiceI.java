package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Gh;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Score;
import com.performances.model.TGh;
import com.performances.model.TLw;

public interface ScoreServiceI {

	public DataGrid datagrid(Score gh, String usercode, List<String> usercodes);

	public String exportExcel();
}
