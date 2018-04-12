package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.UserCate;
import com.performance.pagemodel.UserType;

public interface UserTypeServiceI {
	public DataGrid datagrid(UserType userType) throws Exception;

	UserType save(UserType userType) throws Exception;

	void remove(String ids) throws Exception;

	List<UserType> UserTypeByPoi(FileInputStream fis) throws Exception;

	public String exportExcel();

	public UserType edit(UserType userType);

	public List<UserType> ThesisByPoi(FileInputStream fis);

}
