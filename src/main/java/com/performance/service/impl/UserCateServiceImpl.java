package com.performance.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.performance.pagemodel.PubPart;
import com.performance.pagemodel.UserCate;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.UserCateServiceI;
import com.performances.model.TUserCate;
import com.performances.model.TUserType;

@Transactional
@Service("userCateService")
public class UserCateServiceImpl extends BaseServiceImpl implements UserCateServiceI {

	@Autowired
	private ImportExcelServiceI<TUserCate> _importExcel;

	private BaseDaoI<TUserCate> userCateDao;

	public BaseDaoI<TUserCate> getUserCateDao() {
		return userCateDao;
	}

	@Autowired
	public void setUserCateDao(BaseDaoI<TUserCate> userCateDao) {
		this.userCateDao = userCateDao;
	}

	@Override
	public UserCate save(UserCate userCate) throws Exception {
		TUserCate t = new TUserCate();

		BeanUtils.copyProperties(userCate, t);
		userCateDao.save(t);
		BeanUtils.copyProperties(t, userCate);
		return userCate;
	}

	@Override
	public DataGrid datagrid(UserCate userCate) throws Exception {
		DataGrid dg = new DataGrid();
		String hql = "from TUserCate t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(userCate, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(userCate, hql);

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
		session.put("QUERY_TABLE", "t_usercate");
		session.put("QUERY_HSQL", hql);
		session.put("QUERY_PARAMS", params);

		List<TUserCate> l = userCateDao.find(hql, params, userCate.getPage(), userCate.getRows());
		List<UserCate> nl = new ArrayList<UserCate>();
		changeModel(l, nl);
		dg.setTotal(userCateDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TUserCate> l, List<UserCate> nl) {
		if (l != null && l.size() > 0) {
			for (TUserCate t : l) {
				UserCate r = new UserCate();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private String addOrder(UserCate userCate, String hql) {
		if (userCate.getSort() != null) {
			hql += " order by " + userCate.getSort() + " " + userCate.getOrder();
		}
		return hql;
	}

	private String addWhere(UserCate userCate, String hql, Map<String, Object> params) {
		if (userCate.getName() != null && !userCate.getName().trim().equals("")) {
			hql += " where  t.name like :title";
			params.put("title", "%%" + userCate.getName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) throws Exception {
		String[] nids = ids.split(",");
		String hql = "delete TUserCate t where t.usercateid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		userCateDao.executeHql(hql);

	}

	@Override
	public List<UserCate> UserCateByPoi(FileInputStream fis) throws Exception {
		List<UserCate> infos = new ArrayList<UserCate>();

		try {
			_importExcel.ImportExcelToDB("t_usercate", fis);
		} catch (XPathExpressionException | InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return infos;
	}

	@Override
	public String exportExcel() {
		// TODO Auto-generated method stub
		return _importExcel.exportExcel("UserCate");
	}

	@Override
	public UserCate edit(UserCate userCate) {
		TUserCate tPubPart = userCateDao.get(TUserCate.class, userCate.getUsercateid());
		BeanUtils.copyProperties(userCate, tPubPart, new String[] { "usercateid" });
		userCateDao.saveOrUpdate(tPubPart);
		return userCate;
	}

	@Override
	public List<UserCate> ThesisByPoi(FileInputStream fis) {
		// TODO Auto-generated method stub
		List<UserCate> infos = new ArrayList<UserCate>();

		try {
			if (fis != null) {
				_importExcel.setEventProxy(null);
				_importExcel.ImportExcelToDB("t_usercate", fis);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return infos;
	}

}
