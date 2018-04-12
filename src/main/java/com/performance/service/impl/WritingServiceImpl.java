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
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Examine;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Writing;
import com.performance.pagemodel.Zx;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.WritingServiceI;
import com.performance.service.impl.ThesisServiceImpl.ThesisImportEvent;
import com.performance.util.excel.JavaClass;
import com.performances.model.TLw;
import com.performances.model.TWritingScore;
import com.performances.model.TZx;
import com.performances.model.TZxScore;
import com.performances.model.Twriting;

@Transactional
@Service("writingService")
public class WritingServiceImpl implements WritingServiceI {
	
	//著作DAO对象
	@Autowired
	private BaseDaoI<Twriting> writingDao;

	private static final  String tableName = "t_writing";
	private String hsql;
	private Map<String, Object> params;
	
	//Excel导入服务
	@Autowired
	private ImportExcelServiceI<Twriting> _importExcel;

	@Override
	public Writing save(Writing wt) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		wt.setSavetime(timestamp);
		Twriting t = new Twriting();
		BeanUtils.copyProperties(wt, t);
		writingDao.save(t);
		BeanUtils.copyProperties(t, wt);
		return wt;
	}

	@Override
	public DataGrid datagrid(Writing wt) {
		DataGrid dg = new DataGrid();
		String hql = "from Twriting t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(wt, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(wt, hql);
		List<Twriting> l = writingDao.find(hql, params, wt.getPage(), wt.getRows());
		List<Writing> nl = new ArrayList<>();
		changeModel(l, nl);
		dg.setTotal(writingDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Twriting> l, List<Writing> nl) {
		if (l != null && l.size() > 0) {
			for (Twriting t : l) {
				Writing r = new Writing();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private String addOrder(Writing wt, String hql) {
		if (wt.getSort() != null) {
			hql += " order by " + wt.getSort() + " " + wt.getOrder();
		}
		return hql;
	}

	private String addWhere(Writing wt, String hql, Map<String, Object> params) {
		return hql;
	}

	private String addWhere(Writing wt, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {
		
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" where 1=1 ");
		// 管理员
		if (usercode.equals("") && usercodes == null) {
			//过滤掉审核状态为“已保存”的论文记录
			//sqlStr.append(" and audit_status != '已保存' ");
		}else if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and (");
			sqlStr.append(" t.first_editor_code = :username ");
			sqlStr.append(" or t.second_editor_code = :username ");
			sqlStr.append(" or t.third_editor_code = :username ");
			sqlStr.append(" or t.first_subeditor_code = :username ");
			sqlStr.append(" or t.second_subeditor_code = :username ");
			sqlStr.append(" or t.third_subeditor_code = :username ");
			sqlStr.append(" or t.other_subeditor_code like :otherusername");
			sqlStr.append(" or t.first_author_code = :username ");
			sqlStr.append(" or t.second_author_code = :username ");
			sqlStr.append(" or t.other_editor_code like :otherusername");
			sqlStr.append(" or t.third_author_code = :username ");
			sqlStr.append(" or t.fourth_author_code = :username ");
			sqlStr.append(" or t.fifth_author_code = :username ");
			sqlStr.append(" or t.other_author_code like :otherusername");
			sqlStr.append(")");
			params.put("username", usercode);
			params.put("otherusername", "%%,"+usercode+",%%");
		}else {
			// 是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
				sqlStr.append(" and t.audit_status = '审核通过' ");
				for (int i = 0; i < usercodes.size(); i++) {
					if (i==0) {	
					sqlStr.append("  and (");
					sqlStr.append(" (t.first_editor_code='" + usercodes.get(0)
							    + "'   or  t.second_editor_code='" + usercodes.get(0)
							    + "'   or  t.third_editor_code='" + usercodes.get(0)
								+ "'   or  t.first_subeditor_code='" + usercodes.get(0)
								+ "'   or  t.second_subeditor_code='" + usercodes.get(0)
								+ "'   or  t.third_subeditor_code='" + usercodes.get(0)
							    + "'   or  t.first_author_code ='" + usercodes.get(0)
								+ "'   or  t.second_author_code ='" + usercodes.get(0)
								+ "'   or  t.third_author_code ='" + usercodes.get(0)
								+ "'   or  t.fourth_author_code ='" + usercodes.get(0)
								+ "'   or  t.fifth_author_code ='" + usercodes.get(0)
								+ "'   or  t.other_editor_code like  '%%" +","+usercodes.get(0)+","+ "%%"
								+ "'   or  t.other_subeditor_code like  '%%" +","+usercodes.get(0)+","+ "%%"
								+ "'   or  t.other_author_code like  '%%" +","+usercodes.get(0)+","+ "%%"
								+ "'   )  ");
					}else{
						sqlStr.append("  or  (t.first_editor_code='" + usercodes.get(i)
							    + "'   or  t.second_editor_code='" + usercodes.get(i)
							    + "'   or  t.third_editor_code='" + usercodes.get(i)
								+ "'   or  t.first_subeditor_code='" + usercodes.get(i)
								+ "'   or  t.second_subeditor_code='" + usercodes.get(i)
								+ "'   or  t.third_subeditor_code='" + usercodes.get(i)
							    + "'   or  t.first_author_code ='" + usercodes.get(i)
								+ "'   or  t.second_author_code ='" + usercodes.get(i)
								+ "'   or  t.third_author_code ='" + usercodes.get(i)
								+ "'   or  t.fourth_author_code ='" + usercodes.get(i)
								+ "'   or  t.fifth_author_code ='" + usercodes.get(i)
								+ "'   or  t.other_editor_code like  '%%" +","+usercodes.get(i)+","+ "%%"
								+ "'   or  t.other_subeditor_code like  '%%" +","+usercodes.get(i)+","+ "%%"
								+ "'   or  t.other_author_code like  '%%" +","+usercodes.get(i)+","+ "%%"
								+ "'   )  ");
					}
				}
				sqlStr.append(")");
			} else if (usercode != null) {
				sqlStr.append(" and t.audit_status != '已保存' ");
				sqlStr.append(" and (");
				sqlStr.append(" t.first_editor_code = :usercode ");
				sqlStr.append(" or t.second_editor_code = :usercode ");
				sqlStr.append(" or t.third_editor_code = :usercode ");
				sqlStr.append(" or t.first_subeditor_code = :usercode ");
				sqlStr.append(" or t.second_subeditor_code = :usercode ");
				sqlStr.append(" or t.third_subeditor_code = :usercode ");
				sqlStr.append(" or t.first_author_code = :usercode ");
				sqlStr.append(" or t.second_author_code = :usercode ");
				sqlStr.append(" or t.third_author_code = :usercode ");
				sqlStr.append(" or t.fourth_author_code = :usercode ");
				sqlStr.append(" or t.fifth_author_code = :usercode ");
				sqlStr.append(" or t.other_editor_code like :otherusercode ");
				sqlStr.append(" or t.other_subeditor_code like :otherusercode ");
				sqlStr.append(" or t.other_author_code like :otherusercode ");
				sqlStr.append(")");
				params.put("usercode", usercode);
				params.put("otherusercode", "%%," + usercode + ",%%");
			}
		}

		if (wt.getTitle() != null && !wt.getTitle().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + wt.getTitle().trim() + "%%");
			} else {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + wt.getTitle().trim() + "%%");
			}
		}

		// 根据论文审核状态过滤
		if (wt.getAuditfilter() != null
				&& !wt.getAuditfilter().equalsIgnoreCase("")) {
			if (!wt.getAuditfilter().equals("全部")) {
				sqlStr.append(" and t.audit_status = :audit_status");
				params.put("audit_status", wt.getAuditfilter());
			}
		}
		
		// 高级过滤功能
		// 1.年份
		String begin_year = wt.getS_begin_year(), end_year = wt.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
		}
		// 2.论文名称
		String zx_name = wt.getS_lw_name();
		if (zx_name != null && !zx_name.isEmpty()) {
			sqlStr.append(" and t.title like :title");
			params.put("title", "%%" + zx_name.trim()  + "%%");
		}
		// 3.论文作者
		String zx_author = wt.getS_lw_author();
		if(zx_author != null && !zx_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.first_editor like :susername ");
			sqlStr.append(" or t.second_editor like :susername ");
			sqlStr.append(" or t.third_editor like :susername ");
			sqlStr.append(" or t.other_editor like :susername ");
			sqlStr.append(" or t.first_subeditor like :susername ");
			sqlStr.append(" or t.second_subeditor like :susername ");
			sqlStr.append(" or t.third_subeditor like :susername ");
			sqlStr.append(" or t.other_subeditor like :susername ");
			sqlStr.append(" or t.first_author like :susername ");
			sqlStr.append(" or t.second_author like :susername ");
			sqlStr.append(" or t.third_author like :susername ");
			sqlStr.append(" or t.fourth_author like :susername ");
			sqlStr.append(" or t.fifth_author like :susername ");
			sqlStr.append(" or t.other_author like :susername ");
			sqlStr.append(")");
			params.put("susername", "%%" + zx_author.trim() + "%%");
		}

		// 4 添加根据作者编号过滤
		String zz_authorcode = wt.getS_lw_authorcode();
		if(zz_authorcode != null && !zz_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.first_editor_code = :authorcode ");
			sqlStr.append(" or t.second_editor_code = :authorcode ");
			sqlStr.append(" or t.third_editor_code = :authorcode ");
			sqlStr.append(" or t.first_subeditor_code = :authorcode ");
			sqlStr.append(" or t.second_subeditor_code = :authorcode ");
			sqlStr.append(" or t.third_subeditor_code = :authorcode ");
			sqlStr.append(" or t.first_author_code = :authorcode ");
			sqlStr.append(" or t.second_author_code = :authorcode ");
			sqlStr.append(" or t.third_author_code = :authorcode ");
			sqlStr.append(" or t.fourth_author_code = :authorcode ");
			sqlStr.append(" or t.fifth_author_code = :authorcode ");
			sqlStr.append(" or t.other_editor_code like :otherauthorcode ");
			sqlStr.append(" or t.other_subeditor_code like :otherauthorcode ");
			sqlStr.append(" or t.other_author_code like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode", zz_authorcode);
			params.put("otherauthorcode", "%%" + zz_authorcode + "%%");
		}
		
		return sqlStr.toString();
	}

	@Override
	public DataGrid datagrid(Writing wt, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();
		String hql = "from Twriting t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(wt, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(wt, hql);
		try {
			this.hsql = hql;
			this.params = params;
	        
			List<Twriting> l = writingDao.find(hql, params, wt.getPage(), wt.getRows());
			List<Writing> nl = new ArrayList<Writing>();
			changeModel(l, nl, usercode, usercodes);
			dg.setTotal(writingDao.count(totalHql, params));
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
		String hql = "delete Twriting t where t.id in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		writingDao.executeHql(hql);
	}

	@Override
	public List<Twriting> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}

	@Override
	public Writing edit(Writing wt) {
		Twriting twt = writingDao.get(Twriting.class, wt.getId());
		BeanUtils.copyProperties(wt, twt, new String[] { "id","submitUser","submitTime" });
		//持久化至数据库
		writingDao.saveOrUpdate(twt);
		return wt;
	}

	@Override
	public void importExcel(FileInputStream fis) {
		try {
			WritingImportEvent<Twriting> importEvent = new WritingImportEvent<Twriting>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("Writing", tableName, hsql, params);
	}
	
	private void changeModel(List<Twriting> l, List<Writing> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0) 	
				return;
			
			for (Twriting t : l) {
				Writing r = new Writing();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				
				//获取所有人员分值
				try{
					if(t.getScore() != null){
						StringBuilder authorsb = new StringBuilder();
						StringBuilder subeditorsb = new StringBuilder();
						StringBuilder editorsb = new StringBuilder();
						for( TWritingScore score : t.getScore())
						{
							hasScore = true;
							switch(score.getPosition())
							{
								case 1:
									r.setFirsteditorscore(Double.toString(score.getScore()));
									break;
								case 2:
									r.setSeceditorscore(Double.toString(score.getScore()));
									break;
								case 3:
									r.setThreeeditorscore(Double.toString(score.getScore()));
									break;
								case 4:
									editorsb.append(",");
									editorsb.append(Double.toString(score.getScore()));
									break;
								case 5:
									r.setFirstsubeditorscore(Double.toString(score.getScore()));
									break;
								case 6:
									r.setSecsubeditorscore(Double.toString(score.getScore()));
									break;
								case 7:
									r.setThreesubeditorscore(Double.toString(score.getScore()));
									break;
								case 8:
									subeditorsb.append(",");
									subeditorsb.append(Double.toString(score.getScore()));
									break;
								case 9:
									r.setFirstauthorscore(Double.toString(score.getScore()));
									break;
								case 10:
									r.setSecauthorscore(Double.toString(score.getScore()));
									break;
								case 11:
									r.setThreeauthorscore(Double.toString(score.getScore()));
									break;
								case 12:
									r.setFourauthorscore(Double.toString(score.getScore()));
									break;
								case 13:
									r.setFiveauthorscore(Double.toString(score.getScore()));
									break;
								case 14:
									authorsb.append(",");
									authorsb.append(Double.toString(score.getScore()));
									break;
								default:
									break;
							}
						}

						if(editorsb.length() > 0){
							editorsb.append(",");
							r.setOthereditorscore(editorsb.toString());
						}
						if(subeditorsb.length() > 0){
							subeditorsb.append(",");
							r.setOthersubeditorscore(subeditorsb.toString());
						}
						if(authorsb.length() > 0){
							authorsb.append(",");
							r.setOtherauthorscore(authorsb.toString());
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				if(!hasScore){
					r.setFirsteditorscore("无");
					r.setSeceditorscore("无");
					r.setThreeeditorscore("无");
					r.setOthereditorscore("无");
					r.setFirstsubeditorscore("无");
					r.setSecsubeditorscore("无");
					r.setThreesubeditorscore("无");
					r.setOthersubeditorscore("无");
					r.setFirstauthorscore("无");
					r.setSecauthorscore("无");
					r.setThreeauthorscore("无");
					r.setFourauthorscore("无");
					r.setFiveauthorscore("无");
					r.setOtherauthorscore("无");
				}
				nl.add(r);
			}
			
		} else  if(usercode != null &&!usercode.equals("")&&usercodes == null){		//2.科研人员
			if (l == null || l.size() == 0) 
				return;
			for (Twriting t : l) {
				Writing r = new Writing();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					for( TWritingScore score : t.getScore())
					{
						hasScore = true;
						switch(score.getPosition())
						{
							case 1:
								if(usercode.equals(score.getUsercode())){
									r.setFirsteditorscore(Double.toString(score.getScore()));
								}
								break;
							case 2:
								if(usercode.equals(score.getUsercode())){
									r.setSeceditorscore(Double.toString(score.getScore()));
								}
								break;
							case 3:
								if(usercode.equals(score.getUsercode())){
									r.setThreeeditorscore(Double.toString(score.getScore()));
								}
								break;
							case 4:
								if(usercode.equals(score.getUsercode())){
									r.setOthereditorscore(Double.toString(score.getScore()));
								}
								break;
							case 5:
								if(usercode.equals(score.getUsercode())){
									r.setFirstsubeditorscore(Double.toString(score.getScore()));
								}
								break;
							case 6:
								if(usercode.equals(score.getUsercode())){
									r.setSecsubeditorscore(Double.toString(score.getScore()));
								}
								break;
							case 7:
								if(usercode.equals(score.getUsercode())){
									r.setThreesubeditorscore(Double.toString(score.getScore()));
								}
								break;
							case 8:
								if(usercode.equals(score.getUsercode())){
									r.setOthersubeditorscore(Double.toString(score.getScore()));
								}
								break;
							case 9:
								if(usercode.equals(score.getUsercode())){
									r.setFirstauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 10:
								if(usercode.equals(score.getUsercode())){
									r.setSecauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 11:
								if(usercode.equals(score.getUsercode())){
									r.setThreeauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 12:
								if(usercode.equals(score.getUsercode())){
									r.setFourauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 13:
								if(usercode.equals(score.getUsercode())){
									r.setFiveauthorscore(Double.toString(score.getScore()));
								}
								break;
							case 14:
								if(usercode.equals(score.getUsercode())){
									r.setOtherauthorscore(Double.toString(score.getScore()));
								}
								break;
							default:
								break;
						}
					}
				}
				
				if(!hasScore){
					r.setFirsteditorscore("无");
					r.setSeceditorscore("无");
					r.setThreeeditorscore("无");
					r.setOthereditorscore("无");
					r.setFirstsubeditorscore("无");
					r.setSecsubeditorscore("无");
					r.setThreesubeditorscore("无");
					r.setOthersubeditorscore("无");
					r.setFirstauthorscore("无");
					r.setSecauthorscore("无");
					r.setThreeauthorscore("无");
					r.setFourauthorscore("无");
					r.setFiveauthorscore("无");
					r.setOtherauthorscore("无");
				}
				nl.add(r);
			}
		} else {
			// 3.秘书
			if (l != null && l.size() > 0) {
				for (Twriting t : l) {
					Writing r = new Writing();
					BeanUtils.copyProperties(t, r);
					nl.add(r);
				}
			}
		}
		
	}
	
	public class WritingImportEvent<T> implements ImportExcelEventI<T>{

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

	@Override
	public List<Writing> submit(List<Writing> wtList, String submitUser) {
		for (Writing wt : wtList) {
			Twriting twt = writingDao.get(Twriting.class, wt.getId());
			twt.setAudit_status(Examine.EXAMINE_SUBMIT.getExamine());
			twt.setSubmitUser(submitUser);
			twt.setSubmitTime(new Timestamp(new java.util.Date().getTime()));
			writingDao.saveOrUpdate(twt);
		}
		return wtList;
	}
	
	@Override
	public List<Writing> revoke(List<Writing> wtList) {
		for (Writing wt : wtList) {
			Twriting twt = writingDao.get(Twriting.class, wt.getId());
			twt.setAudit_status(Examine.EXAMINE_REJECT.getExamine());
			writingDao.saveOrUpdate(twt);
		}
		return wtList;
	}

	@Override
	public List<Writing> audit(String audit_status, String auditOpinion,
			List<Writing> wtList) {
		if ("pass".equalsIgnoreCase(audit_status)) {
			for (Writing wt : wtList) {
				Twriting twt = writingDao.get(Twriting.class, wt.getId());
				twt.setAuditOpinion(auditOpinion);
				//twt.set(auditOpinion);
				twt.setAudit_status(Examine.EXAMINE_COMPLETE.getExamine());
				// 持久化至数据库
				writingDao.saveOrUpdate(twt);

			}
		} else if ("reject".equalsIgnoreCase(audit_status)) {
			for (Writing wt : wtList) {
				Twriting twt = writingDao.get(Twriting.class, wt.getId());
				twt.setAudit_status(Examine.EXAMINE_REJECT.getExamine());
				twt.setAuditOpinion(auditOpinion);
				//持久化至数据库
				writingDao.saveOrUpdate(twt);
			}
		}

		return wtList;
	}
}
