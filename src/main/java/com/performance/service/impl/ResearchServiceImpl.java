package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
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
import com.performance.pagemodel.Examine;
import com.performance.pagemodel.Gh;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Research;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.ResearchServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TResearch;

@Transactional
@Service("researchService")
public class ResearchServiceImpl extends BaseServiceImpl implements
		ResearchServiceI {

	private static final  String tableName = "t_achievement";
	private String hsql;
	private Map<String, Object> params;

	private BaseDaoI<TResearch> zxDao;

	public BaseDaoI<TResearch> getZxDao() {
		return zxDao;
	}

	@Autowired
	public void setZxDao(BaseDaoI<TResearch> zxDao) {
		this.zxDao = zxDao;
	}

	@Autowired
	private ImportExcelServiceI<TResearch> _importExcel;
	
	@Override
	public Research save(Research zx) {
		// TODO Auto-generated method stub
		TResearch t = new TResearch();
		BeanUtils.copyProperties(zx, t);
		zxDao.save(t);
		BeanUtils.copyProperties(t, zx);
		return zx;
	}

	@Override
	public DataGrid datagrid(Research zx) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();
		String hql = "from TResearch t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(zx, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(zx, hql);
		List<TResearch> l = zxDao.find(hql, params, zx.getPage(), zx.getRows());
		List<Research> nl = new ArrayList<Research>();
		changeModel(l, nl);
		dg.setTotal(zxDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TResearch> l, List<Research> nl) {
		// TODO Auto-generated method stub
		if (l != null && l.size() > 0) {
			for (TResearch t : l) {
				Research r = new Research();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	/**
	 * 排序  默认所有按照保存时间排序
	 * @param
	 * @param hql
	 * @return
	 */
	private String addOrder(Research zx, String hql) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder(hql);
		//sqlStr.append(" order by savetime desc");
		if (zx.getSort() != null) {
			sqlStr.append(" order by ");
			sqlStr.append(zx.getSort() + " " + zx.getOrder());
		}
		
		return sqlStr.toString();
	}

	@Override
	public DataGrid datagrid(Research zx, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();

		String hql = "from TResearch t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(zx, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(zx, hql);
		try {
			this.hsql = hql;
			this.params = params;
	        
			List<TResearch> l = zxDao.find(hql, params, zx.getPage(), zx.getRows());
			List<Research> nl = new ArrayList<Research>();
			changeModel(l, nl);
			dg.setTotal(zxDao.count(totalHql, params));
			dg.setRows(nl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}

	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		if (ids == null)
			throw new java.lang.NullPointerException();
		String[] nids = ids.split(",");
		String hql = "delete TResearch t where t.id in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		// roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		zxDao.executeHql(hql);
	}

	@Override
	public Research edit(Research zx) {
		TResearch tzx = zxDao.get(TResearch.class, zx.getId());
		BeanUtils.copyProperties(zx, tzx, new String[] { "id" });
		// 持久化至数据库
		zxDao.saveOrUpdate(tzx);
		return zx;
	}

	@Override
	public List<TResearch> findRoleInRange(String[] ids) {

		return null;
	}

	@Override
	public List<TResearch> findEntityByHQL(String hql, Object[] objects) {

		return null;
	}

	@Override
	public void ReportByPoi(FileInputStream fis) {
		try {
			ReportImportEvent<TResearch> importEvent = new ReportImportEvent<TResearch>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}
	}

	private String addWhere(Research zx, String hql, Map<String, Object> params) {
//		if (zx.getTitle() != null && !zx.getTitle().trim().equals("")) {
//
//			hql += " where  t.title like :title";
//			params.put("title", "%%" + zx.getTitle().trim() + "%%");
//		}

		return hql;
	}


	private String addWhere(Research zx, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" where 1=1 ");
		// 管理员
		if (usercode.equals("") && usercodes == null) {
		} else  if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and (");
			sqlStr.append(" t.usercode = :username ");
			sqlStr.append(")");
			params.put("username", usercode);
		}else {
			// 是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
				sqlStr.append(" and (( ");
				sqlStr.append(" t.usercode = :username0");
				sqlStr.append(") ");
				for (int i = 1; i < usercodes.size(); i++) {
					sqlStr.append(" or ( ");
					sqlStr.append(" t.usercode = :username" + i);
					sqlStr.append(")");
				}
				sqlStr.append(") ");
				for (int i = 0; i < usercodes.size(); i++) {
                	params.put("username" + i, usercodes.get(i));
                }
			} else if (usercode != null) {
				sqlStr.append(" and (");
				sqlStr.append(" t.usercode = :username ");
				sqlStr.append(")");
				params.put("username", usercode);
			}
		}

		if (zx.getDomain() != null && !zx.getDomain().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.domain like :domain");
				params.put("domain", "%%" + zx.getDomain().trim() + "%%");
			} else {
				sqlStr.append(" and t.domain like :domain");
				params.put("domain", "%%" + zx.getDomain().trim() + "%%");
			}
		}


		// 3.论文作者
		String zx_author = zx.getUsername();
		if(zx_author != null && !zx_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.username like :username ");
			sqlStr.append(")");
			params.put("username", "%%" + zx_author.trim() + "%%");
		}
//4. 用户编号(高级检索)
		String search_usercode = zx.getSearch_usercode();
		if (search_usercode != null && !search_usercode.isEmpty()) {
			sqlStr.append(" and (");
			sqlStr.append(" t.usercode = :search_usercode ");
			sqlStr.append(")");
			params.put("search_usercode",  search_usercode);
		}


		return sqlStr.toString();
	}
	
	@Override
	public String exportExcel(){
		return _importExcel.exportExcel("Statistics", tableName, hsql, params);
	}
	
	public class ReportImportEvent<T> implements ImportExcelEventI<T>{

		@Override
		public void AfterSaveModel(T model) {
		}

		@Override
		public void AfterUpdateModel(T model) {
		}

		@Override
		public T BeforeSaveModel(T model) {
			Timestamp now = new Timestamp(System.currentTimeMillis()); 
			try {
				JavaClass.setFieldValue(model, "savetime", now);
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return model;
		}
		
		@Override
		public boolean IsValidModel(T model) {
			try
			{
				if(JavaClass.getFieldValue(model, "title").equals(""))
					return false;
				return true;
			}catch(Exception e){
				return false;
			}
			
		}
		
	}


}
