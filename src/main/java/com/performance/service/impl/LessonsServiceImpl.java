package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
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
import com.performance.pagemodel.Sk;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.LessonsServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TSk;

@Transactional
@Service("lessonsService")
public class LessonsServiceImpl extends BaseServiceImpl implements LessonsServiceI{
	private BaseDaoI<TSk> yjDao;

	private static final  String tableName = "t_sk";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TSk> getYjDao() {
		return yjDao;
	}

	@Autowired
	public void setYjDao(BaseDaoI<TSk> lwDao) {
		this.yjDao = lwDao;
	}

	@Autowired
	private ImportExcelServiceI<TSk> _importExcel;
	
	@Override
	public Sk save(Sk lw) {
		TSk t = new TSk();
		BeanUtils.copyProperties(lw, t);
		t.setSavetime(new Timestamp(new java.util.Date().getTime()));
		yjDao.save(t);
		BeanUtils.copyProperties(t, lw);
		return lw;
	}

	@Override
	public DataGrid datagrid(Sk lw) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();
		String hql = "from TSk t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(lw, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lw, hql);
		List<TSk> l = yjDao.find(hql, params, lw.getPage(), lw.getRows());
		List<Sk> nl = new ArrayList<Sk>();
		changeModel(l, nl);
		dg.setTotal(yjDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TSk> l, List<Sk> nl) {
		if (l != null && l.size() > 0) {
			for (TSk t : l) {
				Sk r = new Sk();
				try
				{
					BeanUtils.copyProperties(t, r);
					nl.add(r);
				}catch(Exception e){
					Field [] fields = t.getClass().getDeclaredFields();
					for (int j=0; j < fields.length; j++) {
						Field field = fields[j];
						try{
							JavaClass.setFieldValue(r, field.getName(), JavaClass.getFieldValue(t, field.getName())) ;
						} catch(Exception er){
							//e.printStackTrace();
						}
					}
					nl.add(r);
				}
			}
		}
	}
	
	
	/**
	 * 排序  默认所有按照保存时间排序
	 * @param lw
	 * @param hql
	 * @return
	 */
	private String addOrder(Sk lw, String hql) {
		StringBuilder sqlStr = new StringBuilder(hql);
		//sqlStr.append(" order by savetime desc");
		if (lw.getSort() != null) {
			sqlStr.append(" order by ");
			sqlStr.append(lw.getSort() + " " + lw.getOrder());
		}
		
		return sqlStr.toString();
	}

	private String addWhere(Sk lw, String hql, Map<String, Object> params) {
//		if (lw.getTitle() != null && !lw.getTitle().trim().equals("")) {
//
//			hql += " where  t.title like :title";
//			params.put("title", "%%" + lw.getTitle().trim() + "%%");
//		}
		return hql;
	}

	private String addWhere(Sk lw, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {

		StringBuilder sqlStr = new StringBuilder(hql);
		
		sqlStr.append(" where 1=1 ");
		// 管理员
		if (usercode.equals("") && usercodes == null) {
			
		} else  if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and (");
			sqlStr.append(" t.teacherCode = :username ");
			sqlStr.append(")");
			params.put("username", usercode);
			//params.put("otherusername", ","+usercode+",");
		}else {
			// 是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
				sqlStr.append(" and (( ");
                sqlStr.append(" t.teacherCode = :username0");
                sqlStr.append(") ");
                for (int i = 1; i < usercodes.size(); i++) {
                    sqlStr.append(" or ( ");
                    sqlStr.append(" t.teacherCode = :username" + i);
                    sqlStr.append(")");
                }
                sqlStr.append(" ) ");
                
                for (int i = 0; i < usercodes.size(); i++) {
                	params.put("username" + i, usercodes.get(i));
                }
			} else if (usercode != null) {
				sqlStr.append(" and (");
				sqlStr.append(" t.teacherCode = :usercode ");
				sqlStr.append(")");
				params.put("usercode", usercode);
			}
		}
		
		if (lw.getS_lw_name() != null && !lw.getS_lw_name().trim().equals("")) {
			sqlStr.append(" and t.name like :name");
			params.put("name", "%%" + lw.getS_lw_name().trim() + "%%");
		}

		// 高级过滤功能
		// 1.年份
		String begin_year = lw.getS_begin_year(), end_year = lw.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			params.put("beginyear", begin_year);
			sqlStr.append(" and t.year >= :beginyear");
		}
		if (end_year != null && !end_year.isEmpty()) {
			params.put("endyear", end_year);
			sqlStr.append(" and t.year <= :endyear");
		}

		// 3.专利作者
		String lw_author = lw.getS_lw_author();
		if(lw_author != null && !lw_author.isEmpty()){
			sqlStr.append(" and t.teacher like :name");
			params.put("name", "%%" + lw_author.trim() + "%%");
		}

		// 4 添加根据作者编号过滤
		String lw_authorcode = lw.getS_lw_authorcode();
		if(lw_authorcode != null && !lw_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.teacherCode = :authorcode ");
			sqlStr.append(")");
			params.put("authorcode", lw_authorcode);
		}

		return sqlStr.toString();
	}

	@Override
	public DataGrid datagrid(Sk lw, String username, List<String> usernames) {
		DataGrid dg = new DataGrid();

		String hql = "from TSk t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(lw, hql, params, username, usernames);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lw, hql);
		try {

			this.hsql = hql;
			this.params = params;

			List<TSk> l = yjDao.find(hql, params, lw.getPage(), lw.getRows());
			List<Sk> nl = new ArrayList<Sk>();
			changeModel(l, nl);
			dg.setTotal(yjDao.count(totalHql, params));
			dg.setRows(nl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}


	@Override
	public void remove(String ids) {
		if(ids == null)
			throw new java.lang.NullPointerException();
		String[] nids = ids.split(",");
		String hql = "delete TSk t where t.id in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		// roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		yjDao.executeHql(hql);
	}

	@Override
	public Sk edit(Sk lw) {
		TSk tlw = yjDao.get(TSk.class, lw.getId());
		BeanUtils.copyProperties(lw, tlw, new String[] { "id" });
		tlw.setSavetime(new Timestamp(new java.util.Date().getTime()));
		//持久化至数据库
		yjDao.saveOrUpdate(tlw);
		return lw;
	}

	@Override
	public List<TSk> findRoleInRange(String[] ids) {
		return null;
	}

	@Override
	public List<TSk> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}

	@Override
	public void ThesisByPoi(FileInputStream fis) {
		
		try {
			LessonsImportEvent<TSk> importEvent = new LessonsImportEvent<TSk>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel(){
		return _importExcel.exportExcel("Lessons", tableName, hsql, params);
	}
	
	public class LessonsImportEvent<T> implements ImportExcelEventI<T>{

		@Override
		public void AfterSaveModel(T model) {
		}

		@Override
		public void AfterUpdateModel(T model) {
		}

		@Override
		public T BeforeSaveModel(T model) {
//			Timestamp now = new Timestamp(System.currentTimeMillis()); 
//			try {
//				JavaClass.setFieldValue(model, "savetime", now);
//			} catch (NoSuchFieldException e) {
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			}
			
			return model;
		}
		
		@Override
		public boolean IsValidModel(T model) {
			return true;
		}
		
	}

	@Override
	public boolean  removeAll() {
		String hql = "delete TSk t ";
		try{
			yjDao.executeHql(hql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
