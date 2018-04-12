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
import com.performance.pagemodel.Award;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Examine;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.Writing;
import com.performance.service.AwardServiceI;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.impl.WritingServiceImpl.WritingImportEvent;
import com.performance.util.excel.JavaClass;
import com.performances.model.TAward;
import com.performances.model.TAwardScore;
import com.performances.model.TLw;
import com.performances.model.TWritingScore;
import com.performances.model.Twriting;

@Transactional
@Service("awardService")
public class AwardServiceImpl extends BaseServiceImpl implements AwardServiceI {
	
	@Autowired
	private BaseDaoI<TAward> awardDao;

	private static final  String tableName = "t_award";
	private String hsql;
	private Map<String, Object> params;
	
	//Excel导入服务
	@Autowired
	private ImportExcelServiceI<TAward> _importExcel;

	@Override    
	public Award save(Award awd) {
		TAward t = new TAward();
		BeanUtils.copyProperties(awd, t);
		awardDao.save(t);
		//BeanUtils.copyProperties(t, awd);
		return awd;
	}
	
	@Override
	public DataGrid datagrid(Award awd) {
		return null;
	}

	@Override
	public DataGrid datagrid(Award awd, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();
		String hql = "from TAward t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(awd, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(awd, hql);
		try {
//			ActionContext actionContext = ActionContext.getContext();
//	        Map session = actionContext.getSession();
//	        session.put("QUERY_TABLE", "t_award");
//	        session.put("QUERY_HSQL", hql);
//	        session.put("QUERY_PARAMS", params);

			this.hsql = hql;
			this.params = params;
	        
			List<TAward> l = awardDao.find(hql, params, awd.getPage(), awd.getRows());
			List<Award> nl = new ArrayList<>();
			changeModel(l, nl, usercode, usercodes);
			dg.setTotal(awardDao.count(totalHql, params));
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
		String hql = "delete TAward t where t.id in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		awardDao.executeHql(hql);
	}

	@Override
	public Award edit(Award awd) {
		TAward tawd = awardDao.get(TAward.class, awd.getId());
		BeanUtils.copyProperties(awd, tawd, new String[] { "id", "submitUser", "submitTime" });
		//持久化至数据库
		awardDao.saveOrUpdate(tawd);
		return awd;
	}

	@Override
	public List<TAward> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}

	@Override
	public void importExcel(FileInputStream fis) {
		try {
			AwardImportEvent<TAward> importEvent = new AwardImportEvent<TAward>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("award", tableName, hsql, params);
	}

	@Override
	public List<Award> submit(List<Award> awdList, String submitUser) {
		for (Award awd : awdList) {
			TAward tawd = awardDao.get(TAward.class, awd.getId());
			tawd.setAuditStatus(Examine.EXAMINE_SUBMIT.getExamine());
			tawd.setSubmitUser(submitUser);
			tawd.setSubmitTime(new Timestamp(new java.util.Date().getTime()));
			// 鎸佷箙鍖栬嚦鏁版嵁搴�
			awardDao.saveOrUpdate(tawd);
		}
		return awdList;
	}
	
	

	@Override
	public List<Award> revoke(List<Award> awdList) {
		for (Award awd : awdList) {
			TAward tawd = awardDao.get(TAward.class, awd.getId());
			tawd.setAuditStatus(Examine.EXAMINE_REJECT.getExamine());
			awardDao.saveOrUpdate(tawd);
		}
		return awdList;	
	}

	@Override
	public List<Award> audit(String auditStatus, String auditOpinion, List<Award> awdList) {
		if ("pass".equalsIgnoreCase(auditStatus)) {
			for (Award awd : awdList) {
				TAward tawd = awardDao.get(TAward.class, awd.getId());
				tawd.setAuditOpinion(auditOpinion);
				tawd.setAuditStatus(Examine.EXAMINE_COMPLETE.getExamine());
				//持久化至数据库
				awardDao.saveOrUpdate(tawd);
			}
		} else if ("reject".equalsIgnoreCase(auditStatus)) {
			for (Award awd : awdList) {
				TAward tawd = awardDao.get(TAward.class, awd.getId());
				tawd.setAuditOpinion(auditOpinion);
				tawd.setAuditStatus(Examine.EXAMINE_REJECT.getExamine());
				// 持久化至数据库
				awardDao.saveOrUpdate(tawd);
			}
		}

		return awdList;
	}
	
	private String addOrder(Award wt, String hql) {
		if (wt.getSort() != null) {
			hql += " order by " + wt.getSort() + " " + wt.getOrder();
		}
		return hql;
	}

	private String addWhere(Award wt, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {
		
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" where 1=1 ");
		// 管理员
		if (usercode.equals("") && usercodes == null) {
			//过滤掉审核状态为“已保存”的论文记录
			//sqlStr.append(" and auditStatus != '已保存' ");
		} else if(usercode != null &&!usercode.equals("")&&usercodes == null){
			
			sqlStr.append(" and (");
			sqlStr.append(" t.awardeeCodeOne = :username ");
			sqlStr.append(" or t.awardeeCodeTwo = :username ");
			sqlStr.append(" or t.awardeeCodeThree = :username ");
			sqlStr.append(" or t.awardeeCodeFour = :username ");
			sqlStr.append(" or t.awardeeCodeFive = :username ");
			sqlStr.append(" or t.awardeeCodeSix =:username");
			sqlStr.append(" or t.awardeeCodeSeven = :username ");
			//sqlStr.append(" or t.awardeeEight = :username ");
			sqlStr.append(" or t.awardeeCodeEight = :username ");
			sqlStr.append(" or t.awardeeCodeNine = :username ");
			sqlStr.append(" or t.awardeeCodeTen = :username ");
			sqlStr.append(" or t.awardeeCodeOther like :otherusername");
			sqlStr.append(")");
			params.put("username", usercode);
			params.put("otherusername", "%%,"+usercode+",%%");
		}else {
			// 是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
				for (int i = 0; i < usercodes.size(); i++) {
					if (i==0) {
						sqlStr.append(" and t.auditStatus = '审核通过' ");
						sqlStr.append(" and  (");
						sqlStr.append("(t.awardeeCodeOne='" + usercodes.get(0)
							    + "'   or  t.awardeeCodeTwo='" + usercodes.get(0)
							    + "'   or  t.awardeeCodeThree='" + usercodes.get(0)
							    + "'   or  t.awardeeCodeFour ='" + usercodes.get(0)
								+ "'   or  t.awardeeCodeFive ='" + usercodes.get(0)
								+ "'   or  t.awardeeCodeSix ='" + usercodes.get(0)
								+ "'   or  t.awardeeCodeSeven ='" + usercodes.get(0)
								+ "'   or  t.awardeeCodeNine ='" + usercodes.get(0)
								+ "'   or  t.awardeeCodeEight ='" + usercodes.get(0)
								+ "'   or  t.awardeeCodeTen ='" + usercodes.get(0)
								+ "'   or  t.awardeeCodeOther like  '%%" +","+usercodes.get(0)+","+ "%%"
								+ "'   )  ");
					}else{
						sqlStr.append("  or  (t.awardeeCodeOne='" + usercodes.get(i)
							    + "'   or  t.awardeeCodeTwo='" + usercodes.get(i)
							    + "'   or  t.awardeeCodeThree='" + usercodes.get(i)
							    + "'   or  t.awardeeCodeSix ='" + usercodes.get(i)
								+ "'   or  t.awardeeCodeFour ='" + usercodes.get(i)
								+ "'   or  t.awardeeCodeFive ='" + usercodes.get(i)
								+ "'   or  t.awardeeCodeSeven ='" + usercodes.get(i)
								+ "'   or  t.awardeeCodeEight ='" + usercodes.get(i)
								+ "'   or  t.awardeeCodeNine ='" + usercodes.get(i)
								+ "'   or  t.awardeeCodeTen ='" + usercodes.get(i)
								+ "'   or  t.awardeeCodeOther like  '%%" +","+usercodes.get(i)+","+ "%%"
								//+ "'   or  t.other_author_code like  '%%" +","+usercodes.get(i)+","+ "%%"
								+ "'   )  ");
					}
				}
				sqlStr.append(") ");
			} else if (usercode != null) {
				sqlStr.append(" and t.auditStatus != '已保存' ");
				sqlStr.append(" and (");
				sqlStr.append(" t.awardeeCodeOne = :usercode ");
				sqlStr.append(" or t.awardeeCodeTwo = :usercode ");
				sqlStr.append(" or t.awardeeCodeThree = :usercode ");
				sqlStr.append(" or t.awardeeCodeSix = :usercode ");
				sqlStr.append(" or t.awardeeCodeFour = :usercode ");
				sqlStr.append(" or t.awardeeCodeFive = :usercode ");
				sqlStr.append(" or t.awardeeCodeSeven = :usercode ");
				sqlStr.append(" or t.awardeeCodeEight = :usercode ");
				sqlStr.append(" or t.awardeeCodeNine = :usercode ");
				sqlStr.append(" or t.awardeeCodeTen = :usercode ");
				sqlStr.append(" or t.awardeeCodeOther like :otherusercode ");
				sqlStr.append(")");
				params.put("usercode", usercode);
				params.put("otherusercode", "%%," + usercode + ",%%");
			}
		}

		if (wt.getAwardProjectName()!= null && !wt.getAwardProjectName().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.awardprojectname like :title");
				params.put("title", "%%" + wt.getAwardProjectName().trim() + "%%");
			} else {
				sqlStr.append(" and t.awardprojectname like :title");
				params.put("title", "%%" + wt.getAwardProjectName().trim() + "%%");
			}
		}

		// 根据论文审核状态过滤
		if (wt.getAuditfilter() != null
				&& !wt.getAuditfilter().equalsIgnoreCase("")) {
			if (!wt.getAuditfilter().equals("全部")) {
				sqlStr.append(" and t.auditStatus = :auditstatus");
				params.put("auditstatus", wt.getAuditfilter());
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
			sqlStr.append(" and t.awardProjectName like :title");
			params.put("title", "%%" + zx_name.trim()  + "%%");
		}
		// 3.论文作者
		String zx_author = wt.getS_lw_author();
		if(zx_author != null && !zx_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.awardeeOne like :susername ");
			sqlStr.append(" or t.awardeeTwo like :susername ");
			sqlStr.append(" or t.awardeeThree like :susername ");
			sqlStr.append(" or t.awardeeFour like :susername ");
			sqlStr.append(" or t.awardeeFive like :susername ");
			sqlStr.append(" or t.awardeeSix like :susername ");
			sqlStr.append(" or t.awardeeSeven like :susername ");
			sqlStr.append(" or t.awardeeEight like :susername ");
			sqlStr.append(" or t.awardeeNine like :susername ");
			sqlStr.append(" or t.awardeeTen like :susername ");
			sqlStr.append(" or t.awardeeOther like :sotherusername ");
			sqlStr.append(")");
			params.put("susername", "%%" + zx_author.trim() + "%%");
			params.put("sotherusername", "%%" + zx_author.trim()  + "%%");
		}

		// add by zhengxb 添加根据作者编号过滤
		String jl_authorcode = wt.getS_lw_authorcode();
		if(jl_authorcode != null && !jl_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.awardeeCodeOne = :authorcode ");
			sqlStr.append(" or t.awardeeCodeTwo = :authorcode ");
			sqlStr.append(" or t.awardeeCodeThree = :authorcode ");
			sqlStr.append(" or t.awardeeCodeFour = :authorcode ");
			sqlStr.append(" or t.awardeeCodeFive = :authorcode ");
			sqlStr.append(" or t.awardeeCodeSix = :authorcode ");
			sqlStr.append(" or t.awardeeCodeSeven = :authorcode ");
			sqlStr.append(" or t.awardeeCodeEight = :authorcode ");
			sqlStr.append(" or t.awardeeCodeNine = :authorcode ");
			sqlStr.append(" or t.awardeeCodeTen = :authorcode ");
			sqlStr.append(" or t.awardeeCodeOther like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode", jl_authorcode);
			params.put("otherauthorcode", "%%" + jl_authorcode + "%%");
		}

		return sqlStr.toString();
	}
	
	private void changeModel(List<TAward> l, List<Award> nl) {
		if (l != null && l.size() > 0) {
			for (TAward t : l) {
				Award r = new Award();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}
	
	private void changeModel(List<TAward> l, List<Award> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0) 	
				return;
			
			for (TAward t : l) {
				Award r = new Award();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				try{
					if(t.getScore() != null){
						StringBuilder sb = new StringBuilder();
						for( TAwardScore score : t.getScore())
						{
							hasScore = true;
							switch(score.getPosition())
							{
								case 1:
									r.setAwardee1score(Double.toString(score.getScore()));
									break;
								case 2:
									r.setAwardee2score(Double.toString(score.getScore()));
									break;
								case 3:
									r.setAwardee3score(Double.toString(score.getScore()));
									break;
								case 4:
									r.setAwardee4score(Double.toString(score.getScore()));
									break;
								case 5:
									r.setAwardee5score(Double.toString(score.getScore()));
									break;
								case 6:
									r.setAwardee6score(Double.toString(score.getScore()));
									break;
								case 7:
									r.setAwardee7score(Double.toString(score.getScore()));
									break;
								case 8:
									r.setAwardee8score(Double.toString(score.getScore()));
									break;
								case 9:
									r.setAwardee9score(Double.toString(score.getScore()));
									break;
								case 10:
									r.setAwardee10score(Double.toString(score.getScore()));
									break;
								case 11:
									sb.append(",");
									sb.append(Double.toString(score.getScore()));
									break;
								default:
									break;
							}
						}
						if(sb.length() > 0){
							sb.append(",");
							r.setOtherawardeescore(sb.toString());
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(!hasScore){
					r.setAwardee1score("无");
					r.setAwardee2score("无");
					r.setAwardee3score("无");
					r.setAwardee4score("无");
					r.setAwardee5score("无");
					r.setAwardee6score("无");
					r.setAwardee7score("无");
					r.setAwardee8score("无");
					r.setAwardee9score("无");
					r.setAwardee10score("无");
					r.setOtherawardeescore("无");
				}
				
				nl.add(r);
			}
			
		} else  if(usercode != null &&!usercode.equals("")&&usercodes == null){		//2.科研人员
			if (l == null || l.size() == 0) 
				return;
			for (TAward t : l) {
				Award r = new Award();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					for(TAwardScore score : t.getScore())
					{
						hasScore = true;
						switch(score.getPosition())
						{
							case 1:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee1score(Double.toString(score.getScore()));
								}
								break;
							case 2:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee2score(Double.toString(score.getScore()));
								}
								break;
							case 3:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee3score(Double.toString(score.getScore()));
								}
								break;
							case 4:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee4score(Double.toString(score.getScore()));
								}
								break;
							case 5:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee5score(Double.toString(score.getScore()));
								}
								break;
							case 6:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee6score(Double.toString(score.getScore()));
								}
								break;
							case 7:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee7score(Double.toString(score.getScore()));
								}
								break;
							case 8:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee8score(Double.toString(score.getScore()));
								}
								break;
							case 9:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee9score(Double.toString(score.getScore()));
								}
								break;
							case 10:
								if(usercode.equals(score.getUsercode())){
									r.setAwardee10score(Double.toString(score.getScore()));
								}
								break;
							case 11:
								if(usercode.equals(score.getUsercode())){
									r.setOtherawardeescore(Double.toString(score.getScore()));
								}
								break;
							default:
								break;
						}
					}
				}
				
				if(!hasScore){
					r.setAwardee1score("无");
					r.setAwardee2score("无");
					r.setAwardee3score("无");
					r.setAwardee4score("无");
					r.setAwardee5score("无");
					r.setAwardee6score("无");
					r.setAwardee7score("无");
					r.setAwardee8score("无");
					r.setAwardee9score("无");
					r.setAwardee10score("无");
					r.setOtherawardeescore("无");
				}
				
				nl.add(r);
			}
		} else {
			// 3.秘书
			if (l != null && l.size() > 0) {
				for (TAward t : l) {
					Award r = new Award();
					BeanUtils.copyProperties(t, r);
					nl.add(r);
				}
			}
		}
		
	}
	
	public class AwardImportEvent<T> implements ImportExcelEventI<T>{

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
				if(JavaClass.getFieldValue(model, "awardProjectName").isEmpty() || JavaClass.getFieldValue(model, "awardName").isEmpty())
					return false;
				return true;
			}catch(Exception e){
				return false;
			}
		}
		
	}
}
