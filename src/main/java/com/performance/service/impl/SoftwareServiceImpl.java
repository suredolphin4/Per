package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
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
import com.performance.pagemodel.Bz;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Examine;
import com.performance.pagemodel.Gh;
import com.performance.pagemodel.Rj;
import com.performance.pagemodel.Role;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.SoftwareServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TBz;
import com.performances.model.TBzScore;
import com.performances.model.TGh;
import com.performances.model.TRight;
import com.performances.model.TRj;
import com.performances.model.TRjScore;
import com.performances.model.TRole;

@Transactional
@Service("softwareService")
public class SoftwareServiceImpl extends BaseServiceImpl implements SoftwareServiceI {
	private BaseDaoI<TRj> rjDao;

	private static final  String tableName = "t_rj";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TRj> getRjDao() {
		return rjDao;
	}

	@Autowired
	public void setRjDao(BaseDaoI<TRj> rjDao) {
		this.rjDao = rjDao;
	}

	@Autowired
	private ImportExcelServiceI<TRj> _importExcel;

	@Override
	public Rj save(Rj rj) {
		TRj t = new TRj();
		BeanUtils.copyProperties(rj, t);
		rjDao.save(t);
		BeanUtils.copyProperties(t, rj);
		return rj;
	}

	@Override
	public DataGrid datagrid(Rj rj) {
		DataGrid dg = new DataGrid();
		String hql = "from TRj t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(rj, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(rj, hql);
		List<TRj> l = rjDao.find(hql, params, rj.getPage(), rj.getRows());
		List<Rj> nl = new ArrayList<Rj>();
		changeModel(l, nl);
		dg.setTotal(rjDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TRj> l, List<Rj> nl) {
		if (l != null && l.size() > 0) {
			for (TRj t : l) {
				Rj r = new Rj();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private void changeModel(List<TRj> l, List<Rj> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0) 	
				return;
			
			for (TRj t : l) {
				Rj r = new Rj();
				BeanUtils.copyProperties(t, r);
				
				//获取所有人员分值
				if(t.getScore() != null){
					StringBuilder sb = new StringBuilder();
					for( TRjScore score : t.getScore())
					{
						switch(score.getPosition())
						{
							case 1:
								r.setFirstauthorscore(Double.toString(score.getScore()));
								break;
							case 2:
								r.setSecauthorscore(Double.toString(score.getScore()));
								break;
							case 3:
								r.setThreeauthorscore(Double.toString(score.getScore()));
								break;
							case 4:
								r.setFourauthorscore(Double.toString(score.getScore()));
								break;
							case 5:
								r.setFiveauthorscore(Double.toString(score.getScore()));
								break;
							case 6:
								sb.append(",");
								sb.append(Double.toString(score.getScore()));
								break;
						}
					}
					if(sb.length() > 0){
						sb.append(",");
						r.setOtherauthorscore(sb.toString());
					}
				}
				nl.add(r);
			}
			
		} else  if(usercode != null &&!usercode.equals("")&&usercodes == null){		//2.科研人员
			if (l == null || l.size() == 0) 
				return;
			for (TRj t : l) {
				Rj r = new Rj();
				BeanUtils.copyProperties(t, r);
				//获取所有人员分值
				if(t.getScore() != null){
					for( TRjScore score : t.getScore())
					{
						switch(score.getPosition())
						{
							case 1:
								if(usercode.equals(score.getUsercode())){
									r.setFirstauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 2:
								if(usercode.equals(score.getUsercode())){
									r.setSecauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 3:
								if(usercode.equals(score.getUsercode())){
									r.setThreeauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 4:
								if(usercode.equals(score.getUsercode())){
									r.setFourauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 5:
								if(usercode.equals(score.getUsercode())){
									r.setFiveauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 6:
								if(usercode.equals(score.getUsercode())){
									r.setOtherauthorscore(Double.toString(score.getScore()));
								}
						}
					}
				}
				
				nl.add(r);
			}
		} else {
			// 3.秘书
			if (l != null && l.size() > 0) {
				for (TRj t : l) {
					Rj r = new Rj();
					BeanUtils.copyProperties(t, r);
					nl.add(r);
				}
			}
		}
		
	}
	
	/**
	 * 排序 默认所有按照保存时间排序
	 * 
	 * @param
	 * @param hql
	 * @return
	 */
	private String addOrder(Rj rj, String hql) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" order by savetime desc");
		if (rj.getSort() != null) {
			sqlStr.append(",");
			sqlStr.append(rj.getSort() + " " + rj.getOrder());
		}

		return sqlStr.toString();
	}

	private String addWhere(Rj rj, String hql, Map<String, Object> params) {
		if (rj.getTitle() != null && !rj.getTitle().trim().equals("")) {

			hql += " where  t.title like :title";
			params.put("title", "%%" + rj.getTitle().trim() + "%%");
		}
		return hql;
	}

//	private String addWhere(Rj rj, String hql, Map<String, Object> params,
//			String username, List<String> usernames) {
	private String addWhere(Rj rj, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" where 1=1 ");

		//user为科研人员
		if(usercode != null && !usercode.equals("") && usercodes == null){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthorcode = :username ");
			sqlStr.append(" or t.secauthorcode = :username ");
			sqlStr.append(" or t.threeauthorcode = :username ");
			sqlStr.append(" or t.fourauthorcode = :username ");
			sqlStr.append(" or t.fiveauthorcode = :username ");
			sqlStr.append(" or t.otherauthorcode like :otherusername");
			sqlStr.append(")");
			params.put("username", usercode);
			params.put("otherusername", "%%,"+usercode+",%%");
		}else {
			//是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
				for (int i = 0; i < usercodes.size(); i++) {
					if (i==0) {
					sqlStr.append("  and    (  t.firstauthorcode='" + usercodes.get(0)
								+ "'   or  t.secauthorcode='" + usercodes.get(0)
								+ "'   or  t.threeauthorcode='" + usercodes.get(0)
								+ "'   or  t.fourauthorcode='" + usercodes.get(0)							
								+ "'   or  t.fiveauthorcode='" + usercodes.get(0)
								+ "'   or  t.otherauthorcode like  '%%" +","+usercodes.get(0)+","+ "%%"
								+ "'   )  ");
					
					}if(usercode != null &&!usercode.equals("")&&usercodes == null){
						sqlStr.append(" and (");
						sqlStr.append(" t.firstauthorcode = :username ");
						sqlStr.append(" or t.secauthorcode = :username ");
						sqlStr.append(" or t.threeauthorcode = :username ");
						sqlStr.append(" or t.fourauthorcode = :username ");
						sqlStr.append(" or t.fiveauthorcode = :username ");
						sqlStr.append(" or t.otherauthorcode like :otherusername");
						sqlStr.append(")");
						params.put("username", usercode);
						params.put("otherusername", "%%,"+usercode+",%%");
					}else{
					sqlStr.append("  or    (  t.firstauthorcode='" + usercodes.get(i)
							+ "'   or  t.secauthorcode='" + usercodes.get(i)
							+ "'   or  t.threeauthorcode='" + usercodes.get(i)
							+ "'   or  t.fourauthorcode='" + usercodes.get(i)							
							+ "'   or  t.fiveauthorcode='" + usercodes.get(i) 
							+ "'   or  t.otherauthorcode like '%%" + "," + usercodes.get(i) + "," + "%%"
							+ "'    )   ");
					}
				}
			} else if (usercode != null) {
				//管理员
//				sqlStr.append(" and (");
//				sqlStr.append(" t.firstauthorcode = :username ");
//				sqlStr.append(" or t.secauthorcode = :username ");
//				sqlStr.append(" or t.threeauthorcode = :username ");
//				sqlStr.append(" or t.fourauthorcode = :username ");
//				sqlStr.append(" or t.fiveauthorcode = :username ");
//				sqlStr.append(" or t.otherauthorcode like :otherusername ");
//				sqlStr.append(")");
//				params.put("username", usercode);
//				params.put("otherusername", "%%," + usercode + ",%%");
			}
		}

		if (rj.getTitle() != null && !rj.getTitle().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + rj.getTitle().trim() + "%%");
			} else {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + rj.getTitle().trim() + "%%");
			}
		}

		// 高级过滤功能
		// 1.年份
		String begin_year = rj.getS_begin_year(), end_year = rj.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
		}
		// 2.论文名称
		String rj_name = rj.getS_lw_name();
		if (rj_name != null && !rj_name.isEmpty()) {
			sqlStr.append(" and t.title like :title");
			params.put("title", "%%" + rj_name.trim() + "%%");
		}
		// 3.论文作者
		String rj_author = rj.getS_lw_author();
		if(rj_author != null && !rj_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthor like :susername ");
			sqlStr.append(" or t.secauthor like :susername ");
			sqlStr.append(" or t.threeauthor like :susername ");
			sqlStr.append(" or t.fourauthor like :susername ");
			sqlStr.append(" or t.fiveauthor like :susername ");
			sqlStr.append(" or t.otherauthor like :sotherusername ");
			sqlStr.append(")");
			params.put("susername", "%%" + rj_author.trim() + "%%");
			params.put("sotherusername", "%%" + rj_author.trim() + "%%");
		}

		// 4 添加根据作者编号过滤
		String rj_authorcode = rj.getS_lw_authorcode();
		if(rj_authorcode != null && !rj_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthorcode = :authorcode ");
			sqlStr.append(" or t.secauthorcode = :authorcode ");
			sqlStr.append(" or t.threeauthorcode = :authorcode ");
			sqlStr.append(" or t.fourauthorcode = :authorcode ");
			sqlStr.append(" or t.fiveauthorcode = :authorcode ");
			sqlStr.append(" or t.otherauthorcode like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode", rj_authorcode);
			params.put("otherauthorcode", "%%" + rj_authorcode + "%%");
		}

		return sqlStr.toString();
	}

	@Override
//	public DataGrid datagrid(Rj rj, String username, List<String> usernames) {
	public DataGrid datagrid(Rj rj, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();

		String hql = "from TRj t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(rj, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(rj, hql);
		try {
//			ActionContext actionContext = ActionContext.getContext();
//	        Map session = actionContext.getSession();
//	        session.put("QUERY_TABLE", "t_rj");
//	        session.put("QUERY_HSQL", hql);
//	        session.put("QUERY_PARAMS", params);

			this.hsql = hql;
			this.params = params;
	        
			List<TRj> l = rjDao.find(hql, params, rj.getPage(), rj.getRows());
			List<Rj> nl = new ArrayList<Rj>();
			changeModel(l, nl,usercode,usercodes);
			dg.setTotal(rjDao.count(totalHql, params));
			dg.setRows(nl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}

	@Override
	public void remove(String ids) {
		if (ids == null)
			throw new java.lang.NullPointerException();
		String[] nids = ids.split(",");
		String hql = "delete TRj t where t.rjid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		// roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		rjDao.executeHql(hql);
	}

	@Override
	public Rj edit(Rj rj) {
		TRj trj = rjDao.get(TRj.class, rj.getRjid());
		BeanUtils.copyProperties(rj, trj, new String[] { "rjid" });
		// 持久化至数据库
		rjDao.saveOrUpdate(trj);
		return rj;
	}

	@Override
	public List<TRj> findRoleInRange(String[] ids) {
		return null;
	}

	@Override
	public List<TRj> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}

	@Override
	public void SoftByPoi(FileInputStream fis) {
		try {
			SoftImportEvent<TRj> importEvent = new SoftImportEvent<TRj>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("Soft", tableName, hsql, params);
	}

	/*
	 * @Override public DataGrid datagrid(Lw lw, List<String> usernames) { //
	 * TODO Auto-generated method stub DataGrid dg = new DataGrid();
	 * 
	 * String hql = "from TLw t "; Map<String, Object> params = new
	 * HashMap<String, Object>(); hql = addWhere(lw, hql, params, username);
	 * String totalHql = "select count(*) " + hql; hql = addOrder(lw, hql); try
	 * { List<TLw> l = lwDao.find(hql, params, lw.getPage(), lw.getRows());
	 * List<Lw> nl = new ArrayList<Lw>(); changeModel(l, nl);
	 * dg.setTotal(lwDao.count(totalHql, params)); dg.setRows(nl); } catch
	 * (Exception e) { e.printStackTrace(); } return dg; }
	 */

	public class SoftImportEvent<T> implements ImportExcelEventI<T> {

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
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			return model;
		}

		@Override
		public boolean IsValidModel(T model) {
			try {
				if (JavaClass.getFieldValue(model, "title").equals(""))
					return false;
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	@Override
	public boolean  removeAll() {
		String hql = "delete TRj t ";
		try{
			rjDao.executeHql(hql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//no need submit and audit
	@Override
	public List<Rj> submit(List<Rj> rjList) {
		return null;
	}

	@Override
	public List<Rj> audit(String audit_status, String auditOpinion, List<Rj> rjList) {
		return null;
	}
}
