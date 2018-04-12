package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.UserCate;

public interface UserCateServiceI {
	public DataGrid datagrid(UserCate userCate) throws Exception;

	public UserCate save(UserCate userCate) throws Exception;

	public void remove(String ids) throws Exception;

	public List<UserCate> UserCateByPoi(FileInputStream fis) throws Exception;

	public String exportExcel();

	public UserCate edit(UserCate userCate);

	public List<UserCate> ThesisByPoi(FileInputStream fis);
}
