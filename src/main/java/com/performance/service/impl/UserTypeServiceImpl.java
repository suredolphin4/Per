package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.UserCate;
import com.performance.pagemodel.UserType;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.UserTypeServiceI;
import com.performances.model.TPubPart;
import com.performances.model.TUserType;

@Transactional
@Service("userTypeService")
public class UserTypeServiceImpl extends BaseServiceImpl implements UserTypeServiceI {

	@Autowired
	private ImportExcelServiceI<TUserType> _importExcel;

	private BaseDaoI<TUserType> userTypeDao;

	public BaseDaoI<TUserType> getUserTypeDao() {
		return userTypeDao;
	}

	@Autowired
	public void setUserTypeDao(BaseDaoI<TUserType> userTypeDao) {
		this.userTypeDao = userTypeDao;
	}

	@Override
	public UserType save(UserType userType) throws Exception {
		TUserType t = new TUserType();

		BeanUtils.copyProperties(userType, t);
		userTypeDao.save(t);
		BeanUtils.copyProperties(t, userType);
		return userType;
	}

	@Override
	public DataGrid datagrid(UserType userType) throws Exception {
		DataGrid dg = new DataGrid();
		String hql = "from TUserType t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(userType, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(userType, hql);
		/**
		 * 保存导出时会使用到3个参数
		 */
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		if(session.containsKey("QUERY_HSQL")){
       	 session.remove("QUERY_HSQL");
       }
       if(session.containsKey("QUERY_TABLE")){
       	session.remove("QUERY_TABLE");
       }
       if(session.containsKey("QUERY_PARAMS")){
       	session.remove("QUERY_PARAMS");
       }
		session.put("QUERY_TABLE", "t_usertype");
		session.put("QUERY_HSQL", hql);
		session.put("QUERY_PARAMS", params);

		List<TUserType> l = userTypeDao.find(hql, params, userType.getPage(), userType.getRows());
		List<UserType> nl = new ArrayList<UserType>();
		changeModel(l, nl);
		dg.setTotal(userTypeDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TUserType> l, List<UserType> nl) {
		if (l != null && l.size() > 0) {
			for (TUserType t : l) {
				UserType r = new UserType();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private String addOrder(UserType userType, String hql) {
		if (userType.getSort() != null) {
			hql += " order by " + userType.getSort() + " " + userType.getOrder();
		}
		return hql;
	}

	private String addWhere(UserType userType, String hql, Map<String, Object> params) {
		if (userType.getName() != null && !userType.getName().trim().equals("")) {
			hql += " where  t.name like :title";
			params.put("title", "%%" + userType.getName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) throws Exception {
		String[] nids = ids.split(",");
		String hql = "delete TUserType t where t.usertypeid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		userTypeDao.executeHql(hql);

	}

	@Override
	public List<UserType> UserTypeByPoi(FileInputStream fis) throws Exception {
		List<UserType> infos = new ArrayList<UserType>();

		try {
			_importExcel.ImportExcelToDB("t_usertype", fis);
		} catch (XPathExpressionException | InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return infos;
	}

	@Override
	public String exportExcel() {
		// TODO Auto-generated method stub
		return _importExcel.exportExcel("UserType");
	}

	@Override
	public UserType edit(UserType userType) {
		TUserType tPubPart = userTypeDao.get(TUserType.class, userType.getUsertypeid());
		BeanUtils.copyProperties(userType, tPubPart, new String[] { "usertypeid" });
		userTypeDao.saveOrUpdate(tPubPart);
		return userType;
	}

	@Override
	public List<UserType> ThesisByPoi(FileInputStream fis) {
		// TODO Auto-generated method stub
		List<UserType> infos = new ArrayList<UserType>();

		try {
			if (fis != null) {
				_importExcel.setEventProxy(null);
				_importExcel.ImportExcelToDB("t_usertype", fis);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return infos;
	}
}
