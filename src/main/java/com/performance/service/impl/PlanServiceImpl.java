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
import com.performance.pagemodel.Zx;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.PlanServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TGh;
import com.performances.model.TGhScore;
import com.performances.model.TLw;
import com.performances.model.TZx;
import com.performances.model.TZxScore;


@Transactional
@Service("planService")
public class PlanServiceImpl extends BaseServiceImpl implements
		PlanServiceI {

	private BaseDaoI<TGh> ghDao;

	private static final  String tableName = "t_gh";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TGh> getGhDao() {
		return ghDao;
	}

	@Autowired
	public void setGhDao(BaseDaoI<TGh> ghDao) {
		this.ghDao = ghDao;
	}

	@Autowired
	private ImportExcelServiceI<TGh> _importExcel;
	
	@Override
	public Gh save(Gh gh) {
		TGh t = new TGh();
		BeanUtils.copyProperties(gh, t);
		ghDao.save(t);
		BeanUtils.copyProperties(t, gh);
		return gh;
	}

	@Override
	public DataGrid datagrid(Gh gh) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();
		String hql = "from TGh t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(gh, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(gh, hql);
		List<TGh> l = ghDao.find(hql, params, gh.getPage(), gh.getRows());
		List<Gh> nl = new ArrayList<Gh>();
		changeModel(l, nl);
		dg.setTotal(ghDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TGh> l, List<Gh> nl) {
		if (l != null && l.size() > 0) {
			for (TGh t : l) {
				Gh r = new Gh();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}
	
	private void changeModel(List<TGh> l, List<Gh> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0) 	
				return;
			
			for (TGh t : l) {
				Gh r = new Gh();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					StringBuilder sb = new StringBuilder();
					for( TGhScore score : t.getScore())
					{
						hasScore = true;
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
				if(!hasScore){
					r.setOtherauthorscore("无");
					r.setFirstauthorscore("无");
					r.setSecauthorscore("无");
					r.setThreeauthorscore("无");
					r.setFourauthorscore("无");
					r.setFiveauthorscore("无");
				}
				nl.add(r);
			}
			
		} else  if(usercode != null &&!usercode.equals("")&&usercodes == null){		//2.科研人员
			if (l == null || l.size() == 0) 
				return;
			for (TGh t : l) {
				Gh r = new Gh();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					for( TGhScore score : t.getScore())
					{
						hasScore = true;
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
				if(!hasScore){
					r.setOtherauthorscore("无");
					r.setFirstauthorscore("无");
					r.setSecauthorscore("无");
					r.setThreeauthorscore("无");
					r.setFourauthorscore("无");
					r.setFiveauthorscore("无");
				}
				nl.add(r);
			}
		} else {
			// 3.秘书
			if (l != null && l.size() > 0) {
				for (TGh t : l) {
					Gh r = new Gh();
					BeanUtils.copyProperties(t, r);
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
	private String addOrder(Gh gh, String hql) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" order by ");
	
		if (gh.getSort() != null&&!gh.getSort().equals("savetime")) {
			//sqlStr.append(",");
			sqlStr.append(gh.getSort() + " " + gh.getOrder());
		}else{
			sqlStr.append("  savetime desc");
		}
		
		return sqlStr.toString();
	}
	

	private String addWhere(Gh gh, String hql, Map<String, Object> params) {
		if (gh.getTitle() != null && !gh.getTitle().trim().equals("")) {

			hql += " where  t.title like :title";
			params.put("title", "%%" + gh.getTitle().trim() + "%%");
		}
		return hql;
	}

	private String addWhere(Gh gh, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" where 1=1 ");
		// 管理员
		if (usercode.equals("") && usercodes == null) {
			//过滤掉审核状态为“已保存”的论文记录
			//sqlStr.append(" and examinestatus != '已保存' ");
		} else  if(usercode != null &&!usercode.equals("")&&usercodes == null){
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
			// 是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
				sqlStr.append(" and t.examinestatus = '审核通过' ");
				sqlStr.append(" and (( ");
				sqlStr.append(" t.firstauthorcode = :username0");
				sqlStr.append(" or t.secauthorcode = :username0");
				sqlStr.append(" or t.threeauthorcode = :username0");
				sqlStr.append(" or t.fourauthorcode = :username0");
				sqlStr.append(" or t.fiveauthorcode = :username0");
				sqlStr.append(" or t.otherauthorcode like :otherusername0");
				sqlStr.append(") ");
				for (int i = 1; i < usercodes.size(); i++) {
					sqlStr.append(" or ( ");
					sqlStr.append(" t.firstauthorcode = :username" + i);
					sqlStr.append(" or t.secauthorcode = :username" + i);
					sqlStr.append(" or t.threeauthorcode = :username" + i);
					sqlStr.append(" or t.fourauthorcode = :username" + i);
					sqlStr.append(" or t.fiveauthorcode = :username" + i);
					sqlStr.append(" or t.otherauthorcode like :otherusername" + i);
					sqlStr.append(")");
				}
				sqlStr.append(") ");
				for (int i = 0; i < usercodes.size(); i++) {
                	params.put("username" + i, usercodes.get(i));
                	params.put("otherusername"+i, "%%," + usercodes.get(i) + ",%%");
                }
			} else if (usercode != null) {
				sqlStr.append(" and t.examinestatus != '已保存' ");
				sqlStr.append(" and (");
				sqlStr.append(" t.firstauthorcode = :username ");
				sqlStr.append(" or t.secauthorcode = :username ");
				sqlStr.append(" or t.threeauthorcode = :username ");
				sqlStr.append(" or t.fourauthorcode = :username ");				
				sqlStr.append(" or t.fiveauthorcode = :username ");
				sqlStr.append(" or t.otherauthorcode like :otherusername ");
				sqlStr.append(")");
				params.put("username", usercode);
				params.put("otherusername", "%%," + usercode + ",%%");
			}
		}

		if (gh.getTitle() != null && !gh.getTitle().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + gh.getTitle().trim() + "%%");
			} else {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + gh.getTitle().trim() + "%%");
			}
		}

		// 根据论文审核状态过滤
		if (gh.getAuditfilter() != null
				&& !gh.getAuditfilter().equalsIgnoreCase("")) {
			if (!gh.getAuditfilter().equals("全部")) {
				sqlStr.append(" and t.examinestatus = :examinestatus");
				params.put("examinestatus", gh.getAuditfilter());
			}
		}

		// 高级过滤功能
		// 1.年份
		String begin_year = gh.getS_begin_year(), end_year = gh.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
		}
		// 2.论文名称
		String gh_name = gh.getS_lw_name();
		if (gh_name != null && !gh_name.isEmpty()) {
			sqlStr.append(" and t.title like :title");
			params.put("title", "%%" + gh_name.trim() + "%%");
		}
		// 3.论文作者
		String gh_author = gh.getS_lw_author();
		if(gh_author != null && !gh_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthor like :susername ");
			sqlStr.append(" or t.secauthor like :susername ");
			sqlStr.append(" or t.threeauthor like :susername ");
			sqlStr.append(" or t.fourauthor like :susername ");
			sqlStr.append(" or t.fiveauthor like :susername ");
			sqlStr.append(" or t.otherauthor like :sotherusername ");
			sqlStr.append(")");
			params.put("susername", "%%" + gh_author.trim() + "%%");
			params.put("sotherusername", "%%" + gh_author.trim() + "%%");
		}

		// 4 添加根据作者编号过滤
		String gh_authorcode = gh.getS_lw_authorcode();
		if(gh_authorcode != null && !gh_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthorcode = :authorcode ");
			sqlStr.append(" or t.secauthorcode = :authorcode ");
			sqlStr.append(" or t.threeauthorcode = :authorcode ");
			sqlStr.append(" or t.fourauthorcode = :authorcode ");
			sqlStr.append(" or t.fiveauthorcode = :authorcode ");
			sqlStr.append(" or t.otherauthorcode like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode", gh_authorcode);
			params.put("otherauthorcode", "%%" + gh_authorcode + "%%");
		}

		return sqlStr.toString();
	}

	@Override
//	public DataGrid datagrid(Gh gh, String username, List<String> usernames) {
	public DataGrid datagrid(Gh gh, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();

		String hql = "from TGh t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(gh, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(gh, hql);
		try {
//			ActionContext actionContext = ActionContext.getContext();
//	        Map session = actionContext.getSession();
//	        session.put("QUERY_TABLE", "t_gh");
//	        session.put("QUERY_HSQL", hql);
//	        session.put("QUERY_PARAMS", params);

			this.hsql = hql;
			this.params = params;
	        
			List<TGh> l = ghDao.find(hql, params, gh.getPage(), gh.getRows());
			List<Gh> nl = new ArrayList<Gh>();
			changeModel(l, nl, usercode, usercodes);
			dg.setTotal(ghDao.count(totalHql, params));
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
		String hql = "delete TGh t where t.ghid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		// roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		ghDao.executeHql(hql);
	}

	@Override
	public Gh edit(Gh gh) {
		TGh tgh = ghDao.get(TGh.class, gh.getGhid());
		BeanUtils.copyProperties(gh, tgh, new String[] { "ghid", "submitUser", "submitTime" });
		//持久化至数据库
		ghDao.saveOrUpdate(tgh);
		return gh;
	}

	/**
	 * 论文提交操作
	 * 
	 * @param ghList     规划列表
	 * @param submitUser 提交人
	 * @return
	 */
	@Override
	public List<Gh> submit(List<Gh> ghList, String submitUser) {
		for (Gh gh : ghList) {
			TGh tgh = ghDao.get(TGh.class, gh.getGhid());
			tgh.setExaminestatus(Examine.EXAMINE_SUBMIT.getExamine());
			tgh.setSubmitUser(submitUser);
			tgh.setSubmitTime(new Timestamp(new java.util.Date().getTime()));
			// 鎸佷箙鍖栬嚦鏁版嵁搴�
			ghDao.saveOrUpdate(tgh);
		}
		return ghList;
	}
	
	/**
	 * 撤回已提交规划列表
	 * @param ghList 需撤回的规划列表
	 * @author zheng
 	 */
	@Override
	public List<Gh> revoke(List<Gh> planList) {
		for (Gh gh : planList) {
			TGh tgh = ghDao.get(TGh.class, gh.getGhid());
			tgh.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
			ghDao.saveOrUpdate(tgh);
		}
		return planList;
	}

	/**
	 * 论文审核操作
	 */
	@Override
	public List<Gh> audit(String auditStatus, String auditOpinion, List<Gh> ghList) {
		if ("pass".equalsIgnoreCase(auditStatus)) {
			for (Gh gh : ghList) {
				TGh tgh = ghDao.get(TGh.class, gh.getGhid());
				tgh.setAuditopinion(auditOpinion);
				tgh.setExaminestatus(Examine.EXAMINE_COMPLETE.getExamine());
				// 持久化至数据库
				ghDao.saveOrUpdate(tgh);
			}
		} else if ("reject".equalsIgnoreCase(auditStatus)) {
			for (Gh gh : ghList) {
				TGh tgh = ghDao.get(TGh.class, gh.getGhid());
				tgh.setAuditopinion(auditOpinion);
				tgh.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
				// 持久化至数据库
				ghDao.saveOrUpdate(tgh);
			}
		}

		return ghList;
	}

	@Override
	public List<TGh> findRoleInRange(String[] ids) {
		return null;
	}

	@Override
	public List<TGh> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}

	@Override
	public void PlanByPoi(FileInputStream fis) {
		
		try {
			PlanImportEvent<TGh> importEvent = new PlanImportEvent<TGh>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel(){
		return _importExcel.exportExcel("Plan", tableName, hsql, params);
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
	
	
	public class PlanImportEvent<T> implements ImportExcelEventI<T>{

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
