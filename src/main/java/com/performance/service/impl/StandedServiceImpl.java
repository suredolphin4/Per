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
import com.performance.pagemodel.Bz;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Examine;
import com.performance.pagemodel.Gh;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.StandedServiceI;
import com.performance.service.impl.ThesisServiceImpl.ThesisImportEvent;
import com.performance.util.excel.JavaClass;
import com.performances.model.TBz;
import com.performances.model.TBzScore;
import com.performances.model.TGh;
import com.performances.model.TGhScore;
import com.performances.model.TLw;




@Transactional
@Service("standedService")
public class StandedServiceImpl extends BaseServiceImpl implements
		StandedServiceI {

	private BaseDaoI<TBz> bzDao;

	private static final  String tableName = "t_bz";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TBz> getbzDao() {
		return bzDao;
	}

	@Autowired
	public void setBzDao(BaseDaoI<TBz> bzDao) {
		this.bzDao = bzDao;
	}

	@Autowired
	private ImportExcelServiceI<TBz> _importExcel;
	
	@Override
	public void StandedByPoi(FileInputStream fis) {
		
		try {
			StandedImportEvent<TBz> importEvent = new StandedImportEvent<TBz>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel(){
		return _importExcel.exportExcel("Standed", tableName, hsql, params);
	}
	
	@Override
	public Bz save(Bz bz) {
		TBz t = new TBz();
		BeanUtils.copyProperties(bz, t);
		bzDao.save(t);
		BeanUtils.copyProperties(t, bz);
		return bz;
	}

	@Override
	public DataGrid datagrid(Bz bz) {
		DataGrid dg = new DataGrid();
		String hql = "from TBz t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(bz, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(bz, hql);
		List<TBz> l = bzDao.find(hql, params, bz.getPage(), bz.getRows());
		List<Bz> nl = new ArrayList<Bz>();
		changeModel(l, nl);
		dg.setTotal(bzDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TBz> l, List<Bz> nl) {
		if (l != null && l.size() > 0) {
			for (TBz t : l) {
				Bz r = new Bz();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private void changeModel(List<TBz> l, List<Bz> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0) 	
				return;
			
			for (TBz t : l) {
				Bz r = new Bz();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					StringBuilder sb = new StringBuilder();
					for( TBzScore score : t.getScore())
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
			for (TBz t : l) {
				Bz r = new Bz();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					for( TBzScore score : t.getScore())
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
				for (TBz t : l) {
					Bz r = new Bz();
					BeanUtils.copyProperties(t, r);
					nl.add(r);
				}
			}
		}
		
	}
	
	
	private String addOrder(Bz bz, String hql) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" order by ");
	
		if (bz.getSort() != null&&!bz.getSort().equals("savetime")) {
			//sqlStr.append(",");
			sqlStr.append(bz.getSort() + " " + bz.getOrder());
		}else{
			sqlStr.append("  savetime desc");
		}
		
		return sqlStr.toString();
	}

	@Override
//	public DataGrid datagrid(Bz bz, String username, List<String> usernames) {
	public DataGrid datagrid(Bz bz, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();

		String hql = "from TBz t ";
		Map<String, Object> params = new HashMap<String, Object>();
//		hql = addWhere(bz, hql, params, username, usernames);
		hql = addWhere(bz, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(bz, hql);
		try {		
//			ActionContext actionContext = ActionContext.getContext();
//	        Map session = actionContext.getSession();
//	        session.put("QUERY_TABLE", "t_bz");
//	        session.put("QUERY_HSQL", hql);
//	        session.put("QUERY_PARAMS", params);

			this.hsql = hql;
			this.params = params;

			List<TBz> l = bzDao.find(hql, params, bz.getPage(), bz.getRows());
			List<Bz> nl = new ArrayList<Bz>();
			changeModel(l, nl, usercode, usercodes);
			dg.setTotal(bzDao.count(totalHql, params));
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
		String hql = "delete TBz t where t.bzid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		bzDao.executeHql(hql);
	}

	@Override
	public Bz edit(Bz bz) {
		TBz tbz = bzDao.get(TBz.class, bz.getBzid());
		BeanUtils.copyProperties(bz, tbz, new String[] { "bzid", "submitUser", "submitTime" });
		// 持久化至数据库
		bzDao.saveOrUpdate(tbz);
		return bz;

	}

	@Override
	public List<TBz> findRoleInRange(String[] ids) {
		return null;
	}

	@Override
	public List<TBz> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}

	@Override
	public List<Bz> submit(List<Bz> bzList, String submitUser) {
		for (Bz bz : bzList) {
			TBz tbz = bzDao.get(TBz.class, bz.getBzid());
			tbz.setExaminestatus(Examine.EXAMINE_SUBMIT.getExamine());
			tbz.setSubmitUser(submitUser);
			tbz.setSubmitTime(new Timestamp(new java.util.Date().getTime()));
			// 鎸佷箙鍖栬嚦鏁版嵁搴�
			bzDao.saveOrUpdate(tbz);
		}
		return bzList;
	}
	
	@Override
	public List<Bz> revoke(List<Bz> standardList) {
		for (Bz bz : standardList) {
			TBz tbz = bzDao.get(TBz.class, bz.getBzid());
			tbz.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
			bzDao.saveOrUpdate(tbz);
		}
		return standardList;
	}

	@Override
	public List<Bz> audit(String audit_status, String auditOpinion,
			List<Bz> bzList) {
		if ("pass".equalsIgnoreCase(audit_status)) {
			for (Bz bz : bzList) {
				TBz tbz = bzDao.get(TBz.class, bz.getBzid());
				tbz.setAuditopinion(auditOpinion);
				tbz.setExaminestatus(Examine.EXAMINE_COMPLETE.getExamine());
				// 持久化至数据库
				bzDao.saveOrUpdate(tbz);
			}
		} else if ("reject".equalsIgnoreCase(audit_status)) {
			for (Bz bz : bzList) {
				TBz tbz = bzDao.get(TBz.class, bz.getBzid());
				tbz.setAuditopinion(auditOpinion);
				tbz.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
				// 持久化至数据库
				bzDao.saveOrUpdate(tbz);
			}
		}

		return bzList;
	}

	private String addWhere(Bz bz, String hql, Map<String, Object> params) {
		if (bz.getTitle() != null && !bz.getTitle().trim().equals("")) {

			hql += " where  t.title like :title";
			params.put("title", "%%" + bz.getTitle().trim() + "%%");
		}
		return hql;
	}

	private String addWhere(Bz bz, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" where 1=1 ");
		// 管理员
		if (usercode.equals("") && usercodes == null) {
			//过滤掉审核状态为“已保存”的论文记录
			//sqlStr.append(" and examinestatus != '已保存' ");
		} else if(usercode != null &&!usercode.equals("")&&usercodes == null){
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

		if (bz.getTitle() != null && !bz.getTitle().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + bz.getTitle().trim() + "%%");
			} else {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + bz.getTitle().trim() + "%%");
			}
		}

		// 根据论文审核状态过滤
		if (bz.getAuditfilter() != null
				&& !bz.getAuditfilter().equalsIgnoreCase("")) {
			if (!bz.getAuditfilter().equals("全部")) {
				sqlStr.append(" and t.examinestatus = :examinestatus");
				params.put("examinestatus", bz.getAuditfilter());
			}
		}

		// 高级过滤功能
		// 1.年份
		String begin_year = bz.getS_begin_year(), end_year = bz.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
		}
		// 2.论文名称
		String bz_name = bz.getS_lw_name();
		if (bz_name != null && !bz_name.isEmpty()) {
			sqlStr.append(" and t.title like :title");
			params.put("title", "%%" + bz_name.trim() + "%%");
		}
		// 3.论文作者
		String bz_author = bz.getS_lw_author();
		if(bz_author != null && !bz_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthor like :susername ");
			sqlStr.append(" or t.secauthor like :susername ");
			sqlStr.append(" or t.threeauthor like :susername ");
			sqlStr.append(" or t.fourauthor like :susername ");
			sqlStr.append(" or t.fiveauthor like :susername ");
			sqlStr.append(" or t.otherauthor like :sotherusername ");
			sqlStr.append(")");
			params.put("susername", "%%" + bz_author.trim() + "%%");
			params.put("sotherusername", "%%" + bz_author.trim() + "%%");
		}

		// 4 添加根据作者编号过滤
		String bz_authorcode = bz.getS_lw_authorcode();
		if(bz_authorcode != null && !bz_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthorcode like :authorcode ");
			sqlStr.append(" or t.secauthorcode like :authorcode ");
			sqlStr.append(" or t.threeauthorcode like :authorcode ");
			sqlStr.append(" or t.fourauthorcode like :authorcode ");
			sqlStr.append(" or t.fiveauthorcode like :authorcode ");
			sqlStr.append(" or t.otherauthorcode like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode", "%%" + bz_authorcode + "%%");
			params.put("otherauthorcode", "%%" + bz_authorcode + "%%");
		}

		return sqlStr.toString();
	}
	
	public class StandedImportEvent<T> implements ImportExcelEventI<T>{

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

}
