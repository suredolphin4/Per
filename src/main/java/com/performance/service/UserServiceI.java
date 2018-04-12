package com.performance.service;

import java.io.FileInputStream;
import java.util.List;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.User;
import com.performances.model.TPerson;
import com.performances.model.TUser;

public interface UserServiceI {

	public User save(User user);

	public User login(User user);

	public DataGrid datagrid(User user);

	public void remove(String ids);

	public void saver();

	public User findValidateUser(String usercode, String md5) throws Exception;;

	public TUser findUser(String name);

	public List<User> ThesisByPoi(FileInputStream fis);

	public User edit(User user);

	public User findUserById(String userid);

	//public List<User> resetPasswd(List<User> userList);

	public List<TUser> findUsersByHQL(String domainStr);

	public TUser resetPasswd(TUser user) throws Exception;


	public TUser findTUserById(Integer userid);

	public TUser changePasswd(Integer UserID, String password) throws Exception;

	public String exportExcel();

	public User userCheck(User user);

}
