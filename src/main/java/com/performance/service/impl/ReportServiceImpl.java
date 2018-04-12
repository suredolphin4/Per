package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.performance.service.ReportServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TZx;
import com.performances.model.TZxScore;

@Transactional
@Service("reportService")
public class ReportServiceImpl extends BaseServiceImpl implements
		ReportServiceI {

	private BaseDaoI<TZx> zxDao;

	private static final  String tableName = "t_zx";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TZx> getZxDao() {
		return zxDao;
	}

	@Autowired
	public void setZxDao(BaseDaoI<TZx> zxDao) {
		this.zxDao = zxDao;
	}

	@Autowired
	private ImportExcelServiceI<TZx> _importExcel;
	
	@Override
	public Zx save(Zx zx) {
		// TODO Auto-generated method stub
		TZx t = new TZx();
		BeanUtils.copyProperties(zx, t);
		zxDao.save(t);
		BeanUtils.copyProperties(t, zx);
		return zx;
	}

	@Override
	public DataGrid datagrid(Zx zx) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();
		String hql = "from TZx t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(zx, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(zx, hql);
		List<TZx> l = zxDao.find(hql, params, zx.getPage(), zx.getRows());
		List<Zx> nl = new ArrayList<Zx>();
		changeModel(l, nl);
		dg.setTotal(zxDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TZx> l, List<Zx> nl) {
		// TODO Auto-generated method stub
		if (l != null && l.size() > 0) {
			for (TZx t : l) {
				Zx r = new Zx();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}

	}
	private void changeModel(List<TZx> l, List<Zx> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0)
				return;
			
			for (TZx t : l) {
				Zx r = new Zx();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				
				//获取所有人员分值
				if(t.getTzxScore() != null){
					StringBuilder sb = new StringBuilder();
					for( TZxScore score : t.getTzxScore())
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
						hasScore = true;
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
			for (TZx t : l) {
				Zx r = new Zx();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getTzxScore() != null){
					for( TZxScore score : t.getTzxScore())
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
				for (TZx t : l) {
					Zx r = new Zx();
					BeanUtils.copyProperties(t, r);
					nl.add(r);
				}
			}
		}
		
	}
	
	/**
	 * 排序  默认所有按照保存时间排序
	 * @param zx
	 * @param hql
	 * @return
	 */
	private String addOrder(Zx zx, String hql) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" order by ");
	
		if (zx.getSort() != null&&!zx.getSort().equals("savetime")) {
			//sqlStr.append(",");
			sqlStr.append(zx.getSort() + " " + zx.getOrder());
		}else{
			sqlStr.append("  savetime desc");
		}
		
		return sqlStr.toString();
	}

	@Override
	public DataGrid datagrid(Zx zx, String usercode, List<String> usercodes) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();

		String hql = "from TZx t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(zx, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		
			
			hql = addOrder(zx, hql);
		try {
			this.hsql = hql;
			this.params = params;
	        
	        dg.setTotal(zxDao.count(totalHql, params));
			List<TZx> l = zxDao.find(hql, params, zx.getPage(), zx.getRows());
			List<Zx> nl = new ArrayList<Zx>();
			changeModel(l, nl, usercode, usercodes);
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
		String hql = "delete TZx t where t.zxid in (";
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
	public Zx edit(Zx zx) {
		// TODO Auto-generated method stub
		TZx tzx = zxDao.get(TZx.class, zx.getZxid());
		BeanUtils.copyProperties(zx, tzx, new String[] { "zxid", "submitUser", "submitTime" });
		// 持久化至数据库
		zxDao.saveOrUpdate(tzx);
		return zx;
	}

	@Override
	public List<TZx> findRoleInRange(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TZx> findEntityByHQL(String hql, Object[] objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ReportByPoi(FileInputStream fis) {
		try {
			ReportImportEvent<TZx> importEvent = new ReportImportEvent<TZx>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Zx> submit(List<Zx> zxList, String submitUser) {
		for (Zx zx : zxList) {
			TZx tzx = zxDao.get(TZx.class, zx.getZxid());
			tzx.setExaminestatus(Examine.EXAMINE_SUBMIT.getExamine());
			tzx.setSubmitUser(submitUser);
			tzx.setSubmitTime(new Timestamp(new java.util.Date().getTime()));
			
			zxDao.saveOrUpdate(tzx);
		}
		return zxList;
	}
	
	/**
	 * 撤回已提交咨询报告
	 * @param zxList 需要撤回的咨询报告列表
	 * @author zheng
	 */
	@Override
	public List<Zx> revoke(List<Zx> zxList) {
		for (Zx zx : zxList) {
			TZx tzx = zxDao.get(TZx.class, zx.getZxid());
			tzx.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
			zxDao.saveOrUpdate(tzx);
		}
		return zxList;
	}

	@Override
	public List<Zx> audit(String audit_status, String auditOpinion,
			List<Zx> zxList) {
		// TODO Auto-generated method stub
		if ("pass".equalsIgnoreCase(audit_status)) {
			for (Zx zx : zxList) {
				TZx tzx = zxDao.get(TZx.class, zx.getZxid());
				tzx.setAuditopinion(auditOpinion);
				tzx.setExaminestatus(Examine.EXAMINE_COMPLETE.getExamine());
				// 持久化至数据库
				zxDao.saveOrUpdate(tzx);
			}
		} else if ("reject".equalsIgnoreCase(audit_status)) {
			for (Zx zx : zxList) {
				TZx tzx = zxDao.get(TZx.class, zx.getZxid());
				tzx.setAuditopinion(auditOpinion);
				tzx.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
				// 持久化至数据库
				zxDao.saveOrUpdate(tzx);
			}
		}

		return zxList;
	}

	private String addWhere(Zx zx, String hql, Map<String, Object> params) {
		if (zx.getTitle() != null && !zx.getTitle().trim().equals("")) {

			hql += " where  t.title like :title";
			params.put("title", "%%" + zx.getTitle().trim() + "%%");
		}
		return hql;
	}

	private String addWhere(Zx zx, String hql, Map<String, Object> params,
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
			sqlStr.append(" or t.otherauthorcode like :otherusername ");
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

		if (zx.getTitle() != null && !zx.getTitle().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + zx.getTitle().trim() + "%%");
			} else {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + zx.getTitle().trim() + "%%");
			}
		}

		// 根据论文审核状态过滤
		if (zx.getAuditfilter() != null
				&& !zx.getAuditfilter().equalsIgnoreCase("")) {
			if (!zx.getAuditfilter().equals("全部")) {
				sqlStr.append(" and t.examinestatus = :examinestatus");
				params.put("examinestatus", zx.getAuditfilter());
			}
		}

		// 高级过滤功能
		// 1.年份
		String begin_year = zx.getS_begin_year(), end_year = zx.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
		}
		// 2.论文名称
		String zx_name = zx.getS_lw_name();
		if (zx_name != null && !zx_name.isEmpty()) {
			sqlStr.append(" and t.title like :title");
			params.put("title", "%%" + zx_name.trim()  + "%%");
		}
		// 3.论文作者
		String zx_author = zx.getS_lw_author();
		if(zx_author != null && !zx_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthor like :susername ");
			sqlStr.append(" or t.secauthor like :susername ");
			sqlStr.append(" or t.threeauthor like :susername ");
			sqlStr.append(" or t.fourauthor like :susername ");
			sqlStr.append(" or t.fiveauthor like :susername ");
			sqlStr.append(" or t.otherauthor like :sotherusername ");
			sqlStr.append(")");
			params.put("susername", "%%" + zx_author.trim() + "%%");
			params.put("sotherusername", "%%" + zx_author.trim()  + "%%");
		}

		// 4 添加根据作者编号过滤
		String zx_authorcode = zx.getS_lw_authorcode();
		if(zx_authorcode != null && !zx_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthorcode = :authorcode ");
			sqlStr.append(" or t.secauthorcode = :authorcode ");
			sqlStr.append(" or t.threeauthorcode = :authorcode ");
			sqlStr.append(" or t.fourauthorcode = :authorcode ");
			sqlStr.append(" or t.fiveauthorcode = :authorcode ");
			sqlStr.append(" or t.otherauthorcode like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode", zx_authorcode);
			params.put("otherauthorcode", "%%" + zx_authorcode + "%%");
		}

		return sqlStr.toString();
	}
	
	@Override
	public String exportExcel(){
		return _importExcel.exportExcel("Report", tableName, hsql, params);
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
