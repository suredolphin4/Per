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
import com.performance.pagemodel.Xm;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.ProjectServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TXm;

@Transactional
@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl implements ProjectServiceI{
	private BaseDaoI<TXm> yjDao;

	public BaseDaoI<TXm> getYjDao() {
		return yjDao;
	}

	private static final  String tableName = "t_xm";
	private String hsql;
	private Map<String, Object> params;

	@Autowired
	public void setYjDao(BaseDaoI<TXm> lwDao) {
		this.yjDao = lwDao;
	}

	@Autowired
	private ImportExcelServiceI<TXm> _importExcel;
	
	@Override
	public Xm save(Xm lw) {
		TXm t = new TXm();
		BeanUtils.copyProperties(lw, t);
		t.setSavetime(new Timestamp(new java.util.Date().getTime()));
		yjDao.save(t);
		BeanUtils.copyProperties(t, lw);
		return lw;
	}

	@Override
	public DataGrid datagrid(Xm lw) {
		DataGrid dg = new DataGrid();
		String hql = "from TXm t ";
		Map<String, Object> params = new HashMap<>();
		hql = addWhere(lw, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lw, hql);
		List<TXm> l = yjDao.find(hql, params, lw.getPage(), lw.getRows());
		List<Xm> nl = new ArrayList<Xm>();
		changeModel(l, nl);
		dg.setTotal(yjDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TXm> l, List<Xm> nl) {
		if (l != null && l.size() > 0) {
			for (TXm t : l) {
				Xm r = new Xm();
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
	private String addOrder(Xm lw, String hql) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" order by ");
		if (lw.getSort() != null &&!lw.getSort().equals("beginTime")) {
			//sqlStr.append(" order by savetime desc ");
			sqlStr.append(lw.getSort() + " " + lw.getOrder());
		}else{
			sqlStr.append("  beginTime desc");
		}
		
		return sqlStr.toString();

	}

	private String addWhere(Xm lw, String hql, Map<String, Object> params) {
//		if (lw.getTitle() != null && !lw.getTitle().trim().equals("")) {
//
//			hql += " where  t.title like :title";
//			params.put("title", "%%" + lw.getTitle().trim() + "%%");
//		}
		return hql;
	}

	private String addWhere(Xm lw, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {

		StringBuilder sqlStr = new StringBuilder(hql);
		
		sqlStr.append(" where 1=1 ");
		
		// 管理员
//		if (usercode.equals("") && usercodes == null) {
//
//		}else

		if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and (");
			sqlStr.append(" t.leaderCode = :username ");
			sqlStr.append(" or t.subLeaderCode = :username ");
			sqlStr.append(")");
			params.put("username", usercode);
			//params.put("otherusername", ","+usercode+",");
		} else {
			// 是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
				sqlStr.append(" and (( ");
                sqlStr.append(" t.leaderCode = :username0");
                sqlStr.append(" or t.subLeaderCode = :username0 ");
                sqlStr.append(") ");
                for (int i = 1; i < usercodes.size(); i++) {
                    sqlStr.append(" or ( ");
                    sqlStr.append(" t.leaderCode = :username" + i);
                    sqlStr.append(" or t.subLeaderCode = :username" + i);
                    sqlStr.append(")");
                }
                sqlStr.append(" ) ");
                
                for (int i = 0; i < usercodes.size(); i++) {
                	params.put("username" + i, usercodes.get(i));
                }
			} else if (usercode != null) {
//				sqlStr.append(" and (");
//				sqlStr.append(" t.leaderCode = :usercode ");
//				sqlStr.append(" or t.subLeaderCode = :usercode ");
//				sqlStr.append(")");
//				params.put("usercode", usercode);
			}
		}
		
		if (lw.getS_lw_name() != null && !lw.getS_lw_name().trim().equals("")) {
			sqlStr.append(" and ( t.name like :name or t.subName like :name ) ");
			params.put("name", "%%" + lw.getS_lw_name().trim() + "%%");
		}

		// 高级过滤功能
		// 1.年份
		String begin_year = lw.getS_begin_year(), end_year = lw.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			params.put("beginyear", begin_year);
			sqlStr.append(" and ( t.end >= :beginyear");
		}
		if (end_year != null && !end_year.isEmpty()) {
			params.put("endyear", end_year);
			sqlStr.append(" and t.begin <= :endyear ) ");
		}

		// 3.专利作者
		String lw_author = lw.getS_lw_author();
		if(lw_author != null && !lw_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.leader like :username ");
			sqlStr.append(" or t.subLeader like :username ");
			sqlStr.append(")");
			params.put("username", "%%" + lw_author.trim() + "%%");
		}

		// 4 添加根据作者编号过滤
		String lw_authorcode = lw.getS_lw_authorcode();
		if(lw_authorcode != null && !lw_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.leaderCode = :authorcode ");
			sqlStr.append(" or t.subLeaderCode = :authorcode ");
			sqlStr.append(")");
			params.put("authorcode", lw_authorcode);
		}

		//5、添加课题编号过滤
		String xm_projectID = lw.getS_xm_projectID();
		if(xm_projectID != null && !xm_projectID.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.projectid = :projectid ");

			sqlStr.append(")");
			params.put("projectid", xm_projectID);
		}

		return sqlStr.toString();
	}

	@Override
	public DataGrid datagrid(Xm lw, String username, List<String> usernames) {
		DataGrid dg = new DataGrid();

		String hql = "from TXm t ";
		Map<String, Object> params = new HashMap<>();
		hql = addWhere(lw, hql, params, username, usernames);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lw, hql);
		try {
			this.hsql = hql;
			this.params = params;
	        
			List<TXm> l = yjDao.find(hql, params, lw.getPage(), lw.getRows());
			List<Xm> nl = new ArrayList<>();
			changeModel(l, nl);
			dg.setTotal(yjDao.count(totalHql, params));
			dg.setRows(nl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}

	@Override
	public boolean  removeAll() {
		String hql = "delete TXm t ";
		try{
			yjDao.executeHql(hql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void remove(String ids) {
		if(ids == null)
			throw new java.lang.NullPointerException();
		String[] nids = ids.split(",");
		String hql = "delete TXm t where t.id in (";
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
	public Xm edit(Xm lw) {
		// TODO zhengxuebing
		TXm tlw = yjDao.get(TXm.class, lw.getId());
		BeanUtils.copyProperties(lw, tlw, new String[] { "id" });
		//持久化至数据库
		tlw.setSavetime(new Timestamp(new java.util.Date().getTime()));
		yjDao.saveOrUpdate(tlw);
		return lw;
	}

	@Override
	public List<TXm> findRoleInRange(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TXm> findEntityByHQL(String hql, Object[] objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ThesisByPoi(FileInputStream fis) {

		try {
			ProjectImportEvent<TXm> importEvent = new ProjectImportEvent<>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel(){
		return _importExcel.exportExcel("Project", tableName, hsql, params);
	}
	
	public class ProjectImportEvent<T> implements ImportExcelEventI<T>{

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
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			return model;
		}
		
		@Override
		public boolean IsValidModel(T model) {
			return true;
		}
		
	}
}
