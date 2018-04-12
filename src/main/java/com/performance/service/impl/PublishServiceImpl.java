package com.performance.service.impl;

import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Publish;
import com.performance.pagemodel.Examine;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.PublishServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TPublish;
import com.performances.model.TPublishScore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.xpath.XPathExpressionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Transactional
@Service("publishService")
public class PublishServiceImpl extends BaseServiceImpl implements PublishServiceI {
	private BaseDaoI<TPublish> publishDao;

	private static final  String tableName = "t_publish";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TPublish> getPublishDao() {
		return publishDao;
	}

	@Autowired
	public void setPublishDao(BaseDaoI<TPublish> publishDao) {
		this.publishDao = publishDao;
	}

	@Autowired
	private ImportExcelServiceI<TPublish> _importExcel;

	@Override
	public Publish save(Publish publish) {
		TPublish t = new TPublish();
		BeanUtils.copyProperties(publish, t);
		publishDao.save(t);
		BeanUtils.copyProperties(t, publish);
		return publish;
	}

	@Override
	public DataGrid datagrid(Publish publish) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();
		String hql = "from TPublish t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(publish, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(publish, hql);
		List<TPublish> l = publishDao.find(hql, params, publish.getPage(), publish.getRows());
		List<Publish> nl = new ArrayList<Publish>();
		changeModel(l, nl);
		dg.setTotal(publishDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TPublish> l, List<Publish> nl) {
		if (l != null && l.size() > 0) {
			for (TPublish t : l) {
				Publish r = new Publish();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private void changeModel(List<TPublish> l, List<Publish> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0)
				return;

			for (TPublish t : l) {
				Publish r = new Publish();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				try{
					if(t.getScore() != null){
						StringBuilder authorsb = new StringBuilder();
						StringBuilder editorsb = new StringBuilder();
						StringBuilder subeditorsb = new StringBuilder();
						for( TPublishScore score : t.getScore())
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
						if(authorsb.length() > 0){
							authorsb.append(",");
							r.setOtherauthorscore(authorsb.toString());
						}
						if(editorsb.length() > 0){
							editorsb.append(",");
							r.setOthereditorscore(editorsb.toString());
						}
						if(subeditorsb.length() > 0){
							subeditorsb.append(",");
							r.setOthersubeditorscore(subeditorsb.toString());
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
			for (TPublish t : l) {
				Publish r = new Publish();
				BeanUtils.copyProperties(t, r);
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					for( TPublishScore score : t.getScore())
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
				for (TPublish t : l) {
					Publish r = new Publish();
					BeanUtils.copyProperties(t, r);
					nl.add(r);
				}
			}
		}

	}

	/**
	 * 排序 默认所有按照保存时间排序
	 *
	 * @param publish
	 * @param hql
	 * @return
	 */

	private String addOrder(Publish publish, String hql) {
		// TODO Auto-generated method stub
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" order by ");

		if (publish.getSort() != null&&!publish.getSort().equals("savetime")) {
			//sqlStr.append(",");
			sqlStr.append(publish.getSort() + " " + publish.getOrder());
		}else{
			sqlStr.append("  savetime desc");
		}

		return sqlStr.toString();
	}


	private String addWhere(Publish publish, String hql, Map<String, Object> params) {
		if (publish.getTitle() != null && !publish.getTitle().trim().equals("")) {

			hql += " where  t.title like :title";
			params.put("title", "%%" + publish.getTitle().trim() + "%%");
		}
		return hql;
	}

	private String addWhere(Publish publish, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" where 1=1 ");
		// 管理员
		if (usercode.equals("") && usercodes == null) {
			// 过滤掉审核状态为“已保存”的论文记录
			//sqlStr.append(" and examinestatus != '已保存' ");
		}else  if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthorcode = :username ");
			sqlStr.append(" or t.secauthorcode = :username ");
			sqlStr.append(" or t.threeauthorcode = :username ");
			sqlStr.append(" or t.fourauthorcode = :username ");
			sqlStr.append(" or t.fiveauthorcode = :username ");
			sqlStr.append(" or t.otherauthorcode like :otherusername");
			sqlStr.append(" or t.firsteditorcode = :username ");
			sqlStr.append(" or t.seceditorcode = :username ");
			sqlStr.append(" or t.thirdeditorcode = :username ");
			sqlStr.append(" or t.othereditorcode like :otherusername");

			sqlStr.append(" or t.firstsubeditorcode = :username ");
			sqlStr.append(" or t.secsubeditorcode = :username ");
			sqlStr.append(" or t.thirdsubeditorcode = :username ");
			sqlStr.append(" or t.othersubeditorcode like :otherusername");
			sqlStr.append(")");
			params.put("username", usercode);
			params.put("otherusername", "%%,"+usercode+",%%");
		}else {
			// 是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
				sqlStr.append(" and t.examinestatus = '审核通过' ");
				sqlStr.append("  and    ((  t.firstauthorcode='"
						+ usercodes.get(0)
						+ "'   or  t.secauthorcode='"
						+ usercodes.get(0)
						+ "'   or  t.threeauthorcode='"
						+ usercodes.get(0)
						+ "'   or  t.fourauthorcode='"
						+ usercodes.get(0)
						+ "'   or  t.firsteditorcode='"
						+ usercodes.get(0)
						+ "'   or  t.seceditorcode='"
						+ usercodes.get(0)
						+ "'   or  t.thirdeditorcode='"
						+ usercodes.get(0)
						+ "'   or  t.othereditorcode like  '%%" + ","
						+ usercodes.get(0) + "," + "%%"
						+ "'   or  t.firstsubeditorcode='"
						+ usercodes.get(0)
						+ "'   or  t.secsubeditorcode='"
						+ usercodes.get(0)
						+ "'   or  t.thirdsubeditorcode='"
						+ usercodes.get(0)
						+ "'   or  t.othersubeditorcode like  '%%"
						+ "," + usercodes.get(0) + "," + "%%"
						+ "'   or  t.fiveauthorcode='"
						+ usercodes.get(0)
						+ "'   or  t.otherauthorcode like  '%%" + ","
						+ usercodes.get(0) + "," + "%%" + "'   )  ");

				for (int i = 1; i < usercodes.size(); i++) {
						sqlStr.append("  or    (  t.firstauthorcode='"
								+ usercodes.get(i)
								+ "'   or  t.secauthorcode='"
								+ usercodes.get(i)
								+ "'   or  t.threeauthorcode='"
								+ usercodes.get(i)
								+ "'   or  t.fourauthorcode='"
								+ usercodes.get(i)
								+ "'   or  t.firsteditorcode='"
								+ usercodes.get(0)
								+ "'   or  t.seceditorcode='"
								+ usercodes.get(0)
								+ "'   or  t.thirdeditorcode='"
								+ usercodes.get(0)
								+ "'   or  t.othereditorcode like  '%%" + ","
								+ usercodes.get(0) + "," + "%%"
								+ "'   or  t.firstsubeditorcode='"
								+ usercodes.get(0)
								+ "'   or  t.secsubeditorcode='"
								+ usercodes.get(0)
								+ "'   or  t.thirdsubeditorcode='"
								+ usercodes.get(0)
								+ "'   or  t.othersubeditorcode like  '%%"
								+ "," + usercodes.get(0) + "," + "%%"
								+ "'   or  t.fiveauthorcode='"
								+ usercodes.get(i)
								+ "'   or  t.otherauthorcode like '%%" + ","
								+ usercodes.get(i) + "," + "%%" + "'    )   ");
				}
				sqlStr.append(" ) ");
			} else if (usercode != null) {
				sqlStr.append(" and t.examinestatus != '已保存' ");
				sqlStr.append(" and (");
				sqlStr.append(" t.firstauthorcode = :username ");
				sqlStr.append(" or t.secauthorcode = :username ");
				sqlStr.append(" or t.threeauthorcode = :username ");
				sqlStr.append(" or t.fourauthorcode = :username ");
				sqlStr.append(" or t.firsteditorcode = :username ");
				sqlStr.append(" or t.seceditorcode = :username ");
				sqlStr.append(" or t.thirdeditorcode = :username ");
				sqlStr.append(" or t.firstsubeditorcode = :username ");
				sqlStr.append(" or t.secsubeditorcode = :username ");
				sqlStr.append(" or t.thirdsubeditorcode = :username ");
				sqlStr.append(" or t.othereditorcode like :otherusername ");
				sqlStr.append(" or t.othersubeditorcode like :otherusername ");

				sqlStr.append(" or t.fiveauthorcode = :username ");
				sqlStr.append(" or t.otherauthorcode like :otherusername ");
				sqlStr.append(")");
				params.put("username", usercode);
				params.put("otherusername", "%%," + usercode + ",%%");
			}
		}

		if (publish.getTitle() != null && !publish.getTitle().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + publish.getTitle().trim() + "%%");
			} else {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + publish.getTitle().trim() + "%%");
			}
		}

		// 根据论文审核状态过滤
		if (publish.getAuditfilter() != null
				&& !publish.getAuditfilter().equalsIgnoreCase("")) {
			if (!publish.getAuditfilter().equals("全部")) {
				sqlStr.append(" and t.examinestatus = :examinestatus");
				params.put("examinestatus", publish.getAuditfilter());
			}
		}

		// 高级过滤功能
		// 1.年份
		String begin_year = publish.getS_begin_year(), end_year = publish.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
		}
		// 2.论文名称
		String dt_name = publish.getS_lw_name();
		if (dt_name != null && !dt_name.isEmpty()) {
			sqlStr.append(" and t.title like :title");
			params.put("title", "%%" + dt_name.trim() + "%%");
		}
		// 3.论文作者
		String dt_author = publish.getS_lw_author();
		if (dt_author != null && !dt_author.isEmpty()) {
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthor like :susername ");
			sqlStr.append(" or t.secauthor like :susername ");
			sqlStr.append(" or t.threeauthor like :susername ");
			sqlStr.append(" or t.fourauthor like :susername ");
//			sqlStr.append(" or t.commauthor like :susername ");
			sqlStr.append(" or t.fiveauthor like :susername ");
			sqlStr.append(" or t.otherauthor like :sotherusername ");

			sqlStr.append(" or t.firsteditor like :susername ");
			sqlStr.append(" or t.seceditor like :susername ");
			sqlStr.append(" or t.thirdeditor like :susername ");
			sqlStr.append(" or t.othereditor like :sotherusername");
			sqlStr.append(" or t.firstsubeditor like :susername ");
			sqlStr.append(" or t.secsubeditor like :susername ");
			sqlStr.append(" or t.thirdsubeditor like :susername ");
			sqlStr.append(" or t.othersubeditor like :sotherusername");

			sqlStr.append(")");
			params.put("susername", "%%" + dt_author.trim() + "%%");
			params.put("sotherusername", "%%" + dt_author.trim() + "%%");
		}

		// 4 添加根据作者编号过滤
		String dt_authorcode = publish.getS_lw_authorcode();
		if(dt_authorcode != null && !dt_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthorcode = :authorcode ");
			sqlStr.append(" or t.secauthorcode = :authorcode ");
			sqlStr.append(" or t.threeauthorcode = :authorcode ");
			sqlStr.append(" or t.fourauthorcode = :authorcode ");
			sqlStr.append(" or t.fiveauthorcode = :authorcode ");
			sqlStr.append(" or t.otherauthorcode like :otherauthorcode ");
			sqlStr.append(" or t.firsteditorcode = :authorcode ");
			sqlStr.append(" or t.seceditorcode = :authorcode ");
			sqlStr.append(" or t.thirdeditorcode = :authorcode ");
			sqlStr.append(" or t.firstsubeditorcode = :authorcode ");
			sqlStr.append(" or t.secsubeditorcode = :authorcode ");
			sqlStr.append(" or t.thirdsubeditorcode = :authorcode ");
			sqlStr.append(" or t.othereditorcode like :otherauthorcode ");
			sqlStr.append(" or t.othersubeditorcode like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode", dt_authorcode);
			params.put("otherauthorcode", "%%" + dt_authorcode + "%%");
		}

		return sqlStr.toString();

	}

	@Override
	// public DataGrid datagrid(Dt dt, String username, List<String> usernames)
	// {
	public DataGrid datagrid(Publish publish, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();

		String hql = "from TPublish t ";
		Map<String, Object> params = new HashMap<String, Object>();
		// hql = addWhere(dt, hql, params, username, usernames);
		hql = addWhere(publish, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(publish, hql);
		try {
			this.hsql = hql;
			this.params = params;

			List<TPublish> l = publishDao.find(hql, params, publish.getPage(), publish.getRows());
			List<Publish> nl = new ArrayList<Publish>();
			changeModel(l, nl, usercode, usercodes);
			dg.setTotal(publishDao.count(totalHql, params));
			dg.setRows(nl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}

	@Override
	public void remove(String ids) {
		if (ids == null)
			throw new NullPointerException();
		String[] nids = ids.split(",");
		String hql = "delete TPublish t where t.publishid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		// roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		publishDao.executeHql(hql);
	}

	@Override
	public Publish edit(Publish publish) {
		// TODO 需要更改为只更新发送过来的列
		TPublish tpublish = publishDao.get(TPublish.class, publish.getPublishid());
		BeanUtils.copyProperties(publish, tpublish, new String[] { "publishid", "submitUser", "submitTime" });
		// 持久化至数据库
		publishDao.saveOrUpdate(tpublish);
		return publish;

	}

	/**
	 * 地图提交操作
	 *
	 * @param publishList
	 * @return
	 */
	@Override
	public List<Publish> submit(List<Publish> publishList, String submitUser) {
		for (Publish publish : publishList) {
			TPublish tpublish = publishDao.get(TPublish.class, publish.getPublishid());
			tpublish.setExaminestatus(Examine.EXAMINE_SUBMIT.getExamine());
			tpublish.setSubmitUser(submitUser);
			tpublish.setSubmitTime(new Timestamp(new Date().getTime()));
			// 鎸佷箙鍖栬嚦鏁版嵁搴�
			publishDao.saveOrUpdate(tpublish);
		}
		return publishList;
	}
	
	/**
	 * 撤回已提交地图
	 */
	@Override
	public List<Publish> revoke(List<Publish> publishList) {
		for (Publish publish : publishList) {
			TPublish tpublish = publishDao.get(TPublish.class, publish.getPublishid());
			tpublish.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
			publishDao.saveOrUpdate(tpublish);
		}
		return publishList;
	}

	/**
	 * 论文审核操作
	 */
	@Override
	public List<Publish> audit(String auditStatus, String auditOpinion,
			List<Publish> publishList) {
		if ("pass".equalsIgnoreCase(auditStatus)) {
			for (Publish publish : publishList) {
				TPublish tpublish = publishDao.get(TPublish.class, publish.getPublishid());
				tpublish.setAuditopinion(auditOpinion);
				tpublish.setExaminestatus(Examine.EXAMINE_COMPLETE.getExamine());
				// 持久化至数据库
				publishDao.saveOrUpdate(tpublish);
			}
		} else if ("reject".equalsIgnoreCase(auditStatus)) {
			for (Publish publish : publishList) {
				TPublish tpublish = publishDao.get(TPublish.class, publish.getPublishid());
				tpublish.setAuditopinion(auditOpinion);
				tpublish.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
				// 持久化至数据库
				publishDao.saveOrUpdate(tpublish);
			}
		}

		return publishList;
	}

	@Override
	public List<TPublish> findRoleInRange(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TPublish> findEntityByHQL(String hql, Object[] objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void PublishByPoi(FileInputStream fis) {

		try {
			PublishImportEvent<TPublish> importEvent = new PublishImportEvent<TPublish>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("Publish", tableName, hsql, params);
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

	public class PublishImportEvent<T> implements ImportExcelEventI<T> {

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
			try {
				if (JavaClass.getFieldValue(model, "title").equals(""))
					return false;
				return true;
			} catch (Exception e) {
				return false;
			}

		}

	}

}
