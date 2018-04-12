package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.PubPart;
import com.performances.model.TPubPart;

public interface PubPartServiceI {

	public PubPart save(PubPart pubPart);

	public DataGrid datagrid(PubPart pubPart);

	public void remove(String ids);

	public PubPart edit(PubPart pubPart);

	public List<TPubPart> findEntityByHQL(String hql, Object[] objects);

	public List<PubPart> ThesisByPoi(FileInputStream fis);

	public String exportExcel();

	public PubPart pubPartCheck(PubPart pubPart);
	
	public DataGrid getPubListByKind(PubPart pubPart);
}
