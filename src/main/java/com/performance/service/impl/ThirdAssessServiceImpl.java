package com.performance.service.impl;

import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Examine;
import com.performance.pagemodel.Ta;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.ThirdAssessServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TTa;
import com.performances.model.TTaScore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.xpath.XPathExpressionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional
@Service("thirdAssessService")
public class ThirdAssessServiceImpl extends BaseServiceImpl implements
		ThirdAssessServiceI {

	private BaseDaoI<TTa> taDao;

	private static final  String tableName = "t_ta";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TTa> getTaDao() {
		return taDao;
	}

	@Autowired
	public void setTaDao(BaseDaoI<TTa> taDao) {
		this.taDao = taDao;
	}

	@Autowired
	private ImportExcelServiceI<TTa> _importExcel;

	@Override
	public Ta save(Ta ta) {
		TTa t = new TTa();
		BeanUtils.copyProperties(ta, t);
		taDao.save(t);
		BeanUtils.copyProperties(t, ta);
		return ta;
	}

	@Override
	public DataGrid datagrid(Ta ta) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();
		String hql = "from TTa t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(ta, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(ta, hql);
		List<TTa> l = taDao.find(hql, params, ta.getPage(), ta.getRows());
		List<Ta> nl = new ArrayList<Ta>();
		changeModel(l, nl);
		dg.setTotal(taDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TTa> l, List<Ta> nl) {
		if (l != null && l.size() > 0) {
			for (TTa t : l) {
				Ta r = new Ta();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private void changeModel(List<TTa> l, List<Ta> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0)
				return;

			for (TTa t : l) {
				Ta r = new Ta();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					StringBuilder sb = new StringBuilder();
					for( TTaScore score : t.getScore())
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
			for (TTa t : l) {
				Ta r = new Ta();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					for( TTaScore score : t.getScore())
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
				for (TTa t : l) {
					Ta r = new Ta();
					BeanUtils.copyProperties(t, r);
					nl.add(r);
				}
			}
		}

	}

	/**
	 * 排序  默认所有按照保存时间排序
	 * @param ta
	 * @param hql
	 * @return
	 */
	private String addOrder(Ta ta, String hql) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" order by ");

		if (ta.getSort() != null&&!ta.getSort().equals("savetime")) {
			//sqlStr.append(",");
			sqlStr.append(ta.getSort() + " " + ta.getOrder());
		}else{
			sqlStr.append("  savetime desc");
		}

		return sqlStr.toString();
	}


	private String addWhere(Ta ta, String hql, Map<String, Object> params) {
		if (ta.getTitle() != null && !ta.getTitle().trim().equals("")) {

			hql += " where  t.title like :title";
			params.put("title", "%%" + ta.getTitle().trim() + "%%");
		}
		return hql;
	}

	private String addWhere(Ta ta, String hql, Map<String, Object> params,
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

		if (ta.getTitle() != null && !ta.getTitle().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + ta.getTitle().trim() + "%%");
			} else {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + ta.getTitle().trim() + "%%");
			}
		}

		// 根据论文审核状态过滤
		if (ta.getAuditfilter() != null
				&& !ta.getAuditfilter().equalsIgnoreCase("")) {
			if (!ta.getAuditfilter().equals("全部")) {
				sqlStr.append(" and t.examinestatus = :examinestatus");
				params.put("examinestatus", ta.getAuditfilter());
			}
		}

		// 高级过滤功能
		// 1.年份
		String begin_year = ta.getS_begin_year(), end_year = ta.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
		}
		// 2.论文名称
		String ta_name = ta.getS_lw_name();
		if (ta_name != null && !ta_name.isEmpty()) {
			sqlStr.append(" and t.title like :title");
			params.put("title", "%%" + ta_name.trim() + "%%");
		}
		// 3.论文作者
		String ta_author = ta.getS_lw_author();
		if(ta_author != null && !ta_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthor like :susername ");
			sqlStr.append(" or t.secauthor like :susername ");
			sqlStr.append(" or t.threeauthor like :susername ");
			sqlStr.append(" or t.fourauthor like :susername ");
			sqlStr.append(" or t.fiveauthor like :susername ");
			sqlStr.append(" or t.otherauthor like :sotherusername ");
			sqlStr.append(")");
			params.put("susername", "%%" + ta_author.trim() + "%%");
			params.put("sotherusername", "%%" + ta_author.trim() + "%%");
		}

		// 4 添加根据作者编号过滤
		String ta_authorcode = ta.getS_lw_authorcode();
		if(ta_authorcode != null && !ta_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthorcode = :authorcode ");
			sqlStr.append(" or t.secauthorcode = :authorcode ");
			sqlStr.append(" or t.threeauthorcode = :authorcode ");
			sqlStr.append(" or t.fourauthorcode = :authorcode ");
			sqlStr.append(" or t.fiveauthorcode = :authorcode ");
			sqlStr.append(" or t.otherauthorcode like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode", ta_authorcode);
			params.put("otherauthorcode", "%%" + ta_authorcode + "%%");
		}

		return sqlStr.toString();
	}

	@Override
//	public DataGrid datagrid(Gh gh, String username, List<String> usernames) {
	public DataGrid datagrid(Ta ta, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();

		String hql = "from TTa t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(ta, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(ta, hql);
		try {
//			ActionContext actionContext = ActionContext.getContext();
//	        Map session = actionContext.getSession();
//	        session.put("QUERY_TABLE", "t_gh");
//	        session.put("QUERY_HSQL", hql);
//	        session.put("QUERY_PARAMS", params);

			this.hsql = hql;
			this.params = params;

			List<TTa> l = taDao.find(hql, params, ta.getPage(), ta.getRows());
			List<Ta> nl = new ArrayList<Ta>();
			changeModel(l, nl, usercode, usercodes);
			dg.setTotal(taDao.count(totalHql, params));
			dg.setRows(nl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}

	@Override
	public void remove(String ids) {
		if(ids == null)
			throw new NullPointerException();
		String[] nids = ids.split(",");
		String hql = "delete TTa t where t.taid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		// roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		taDao.executeHql(hql);
	}

	@Override
	public Ta edit(Ta ta) {
		TTa tta = taDao.get(TTa.class, ta.getTaid());
		BeanUtils.copyProperties(ta, tta, new String[] { "taid", "submitUser", "submitTime" });
		//持久化至数据库
		taDao.saveOrUpdate(tta);
		return ta;
	}

	/**
	 * 论文提交操作
	 * 
	 * @param taList     规划列表
	 * @param submitUser 提交人
	 * @return
	 */
	@Override
	public List<Ta> submit(List<Ta> taList, String submitUser) {
		for (Ta ta : taList) {
			TTa tta = taDao.get(TTa.class, ta.getTaid());
			tta.setExaminestatus(Examine.EXAMINE_SUBMIT.getExamine());
			tta.setSubmitUser(submitUser);
			tta.setSubmitTime(new Timestamp(new java.util.Date().getTime()));
			// 鎸佷箙鍖栬嚦鏁版嵁搴�
			taDao.saveOrUpdate(tta);
		}
		return taList;
	}
	
	/**
	 * 撤回已提交规划列表
	 * @param thirdAssessList 需撤回的规划列表
	 * @author zheng
 	 */
	@Override
	public List<Ta> revoke(List<Ta> thirdAssessList) {
		for (Ta ta : thirdAssessList) {
			TTa tta = taDao.get(TTa.class, ta.getTaid());
			tta.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
			taDao.saveOrUpdate(tta);
		}
		return thirdAssessList;
	}

	/**
	 * 论文审核操作
	 */
	@Override
	public List<Ta> audit(String auditStatus, String auditOpinion, List<Ta> taList) {
		if ("pass".equalsIgnoreCase(auditStatus)) {
			for (Ta ta : taList) {
				TTa tta = taDao.get(TTa.class, ta.getTaid());
				tta.setAuditopinion(auditOpinion);
				tta.setExaminestatus(Examine.EXAMINE_COMPLETE.getExamine());
				// 持久化至数据库
				taDao.saveOrUpdate(tta);
			}
		} else if ("reject".equalsIgnoreCase(auditStatus)) {
			for (Ta ta : taList) {
				TTa tta = taDao.get(TTa.class, ta.getTaid());
				tta.setAuditopinion(auditOpinion);
				tta.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
				// 持久化至数据库
				taDao.saveOrUpdate(tta);
			}
		}

		return taList;
	}

	@Override
	public List<TTa> findRoleInRange(String[] ids) {
		return null;
	}

	@Override
	public List<TTa> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}

	@Override
	public void ThirdAssessByPoi(FileInputStream fis) {
		
		try {
			ThirdAssessImportEvent<TTa> importEvent = new ThirdAssessImportEvent<TTa>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel(){
		return _importExcel.exportExcel("ThirdAssess", tableName, hsql, params);
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
	
	
	public class ThirdAssessImportEvent<T> implements ImportExcelEventI<T>{

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
