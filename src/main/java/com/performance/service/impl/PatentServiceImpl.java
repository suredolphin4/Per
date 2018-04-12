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
import com.performance.pagemodel.Rj;
import com.performance.pagemodel.Zl;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.PatentServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TRj;
import com.performances.model.TRjScore;
import com.performances.model.TZl;
import com.performances.model.TZlScore;

@Transactional
@Service("patentService")
public class PatentServiceImpl extends BaseServiceImpl implements PatentServiceI{
	private BaseDaoI<TZl> yjDao;

	private static final  String tableName = "t_zl";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TZl> getYjDao() {
		return yjDao;
	}

	@Autowired
	public void setYjDao(BaseDaoI<TZl> lwDao) {
		this.yjDao = lwDao;
	}

	@Autowired
	private ImportExcelServiceI<TZl> _importExcel;
	
	@Override
	public Zl save(Zl lw) {
		TZl t = new TZl();
		BeanUtils.copyProperties(lw, t);
		t.setSavetime(new Timestamp(new java.util.Date().getTime()));
		yjDao.save(t);
		BeanUtils.copyProperties(t, lw);
		return lw;
	}

	@Override
	public DataGrid datagrid(Zl lw) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();
		String hql = "from TZl t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(lw, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lw, hql);
		List<TZl> l = yjDao.find(hql, params, lw.getPage(), lw.getRows());
		List<Zl> nl = new ArrayList<Zl>();
		changeModel(l, nl);
		dg.setTotal(yjDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TZl> l, List<Zl> nl) {
		if (l != null && l.size() > 0) {
			for (TZl t : l) {
				Zl r = new Zl();
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
	
	private void changeModel(List<TZl> l, List<Zl> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0) 	
				return;
			
			for (TZl t : l) {
				Zl r = new Zl();
				BeanUtils.copyProperties(t, r);
				
				//获取所有人员分值
				if(t!=null){
				if(t.getScore() != null){
					StringBuilder sb = new StringBuilder();
					double sc=0.00;
					String _score="";
					
					try {
					for( TZlScore score : t.getScore())
					{
							if(score!=null){
								
								try{
									sc=score.getScore();
									_score=Double.toString(sc);
								}catch(Exception e){
									_score="0.0";
								}
								if(Double.valueOf(sc)!=null)	{
								switch(score.getPosition())
								{
									case 1:
											r.setFirstauthorscore(_score);
										break;
									case 2:
											r.setSecauthorscore(_score);
										break;
									case 3:
											r.setThreeauthorscore(_score);
										break;
									case 4:
											r.setFourauthorscore(_score);
										break;
									case 5:
											r.setFiveauthorscore(_score);
										break;
									case 6:
										sb.append(",");
											sb.append(_score);
										break;
								}
								
								
								}
								
							}
							if(sb.length() > 0){
							sb.append(",");
							r.setOtherauthorscore(sb.toString());
							}
						}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("score is null");
					}
				}
				}
				nl.add(r);
			}
			
		} else  if(usercode != null &&!usercode.equals("")&&usercodes == null){		//2.科研人员
			if (l == null || l.size() == 0) 
				return;
			for (TZl t : l) {
				Zl r = new Zl();
				BeanUtils.copyProperties(t, r);
				//获取所有人员分值
				if(t!=null){
				if(t.getScore() != null){
					for( TZlScore score : t.getScore())
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
				}

				nl.add(r);
			}
		} else {
			// 3.秘书
			if (l != null && l.size() > 0) {
				for (TZl t : l) {
					Zl r = new Zl();
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
	private String addOrder(Zl lw, String hql) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" order by savetime desc");
		if (lw.getSort() != null) {
			sqlStr.append(",");
			sqlStr.append(lw.getSort() + " " + lw.getOrder());
		}
		
		return sqlStr.toString();
	}

	private String addWhere(Zl lw, String hql, Map<String, Object> params) {
//		if (lw.getTitle() != null && !lw.getTitle().trim().equals("")) {
//
//			hql += " where  t.title like :title";
//			params.put("title", "%%" + lw.getTitle().trim() + "%%");
//		}
		return hql;
	}

	private String addWhere(Zl lw, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {

		StringBuilder sqlStr = new StringBuilder(hql);
		
		sqlStr.append(" where 1=1 ");
		// 管理员
		if (usercode.equals("") && usercodes == null) {
			
		}else  if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstInvectorcode = :username ");
			sqlStr.append(" or t.secondInvectorcode = :username ");
			sqlStr.append(" or t.thirdInvectorcode = :username ");
			sqlStr.append(" or t.fourthInvectorcode = :username ");
			sqlStr.append(" or t.fifthInvectorcode = :username ");
			sqlStr.append(" or t.otherInvectorcode like  :otherusername ");
			sqlStr.append(")");
			params.put("username", usercode);
			params.put("otherusername", "%%,"+usercode+",%%");
		}else {
			// 是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
                sqlStr.append(" and (( ");
                sqlStr.append(" t.firstInvectorcode = :username0");
                sqlStr.append(" or t.secondInvectorcode = :username0 ");
                sqlStr.append(" or t.thirdInvectorcode = :username0 ");
                sqlStr.append(" or t.fourthInvectorcode = :username0 ");
                sqlStr.append(" or t.fifthInvectorcode = :username0 ");
                sqlStr.append(" or t.otherInvectorcode like :otherusername0 ");
                sqlStr.append(") ");
                for (int i = 1; i < usercodes.size(); i++) {
                    sqlStr.append(" or ( ");
                    sqlStr.append(" t.firstInvectorcode = :username" + i);
                    sqlStr.append(" or t.secondInvectorcode = :username" + i);
                    sqlStr.append(" or t.thirdInvectorcode = :username"  + i);
                    sqlStr.append(" or t.fourthInvectorcode = :username"  + i);
                    sqlStr.append(" or t.fifthInvectorcode = :username"  + i);
                    sqlStr.append(" or t.otherInvectorcode like :otherusername"  + i);
                    sqlStr.append(")");
                }
                sqlStr.append(" ) ");
                
                for (int i = 0; i < usercodes.size(); i++) {
                	params.put("username" + i, usercodes.get(i));
                    params.put("otherusername" + i, "%%," + usercodes.get(i) + ",%%");
                }
			} else if (usercode != null) {
				sqlStr.append(" and (");
				sqlStr.append(" t.firstInvectorcode = :username ");
				sqlStr.append(" or t.secondInvectorcode = :username ");
				sqlStr.append(" or t.thirdInvectorcode = :username ");
				sqlStr.append(" or t.fourthInvectorcode = :username ");
				sqlStr.append(" or t.fifthInvectorcode = :username ");
				sqlStr.append(" or t.otherInvectorcode like :otherusername ");
				sqlStr.append(")");
				params.put("username", usercode);
				params.put("otherusername", "%%," + usercode + ",%%");
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
			params.put("beginyear", Integer.parseInt(begin_year));
			sqlStr.append(" and t.year >= :beginyear");
		}
		if (end_year != null && !end_year.isEmpty()) {
			params.put("endyear", Integer.parseInt(end_year));
			sqlStr.append(" and t.year <= :endyear");
		}

		// 3.专利作者
		String lw_author = lw.getS_lw_author();
		if(lw_author != null && !lw_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstInvector like :username ");
			sqlStr.append(" or t.secondInvector like :username ");
			sqlStr.append(" or t.thirdInvector like :username ");
			sqlStr.append(" or t.fourthInvector like :username ");
			sqlStr.append(" or t.fifthInvector like :username ");
			sqlStr.append(" or t.otherInvector like :username ");
			sqlStr.append(")");
			params.put("username", "%%" + lw_author.trim() + "%%");
	}
		// 4 添加根据作者编号过滤
		String lw_authorcode = lw.getS_lw_authorcode();
		if(lw_authorcode != null && !lw_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstInvectorcode = :authorcode ");
			sqlStr.append(" or t.secondInvectorcode = :authorcode ");
			sqlStr.append(" or t.thirdInvectorcode = :authorcode ");
			sqlStr.append(" or t.fourthInvectorcode = :authorcode ");
			sqlStr.append(" or t.fifthInvectorcode = :authorcode ");
			sqlStr.append(" or t.otherInvectorcode like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode", lw_authorcode);
			params.put("otherauthorcode", "%%" + lw_authorcode + "%%");
		}

//		// 4.领域、研究室   //TODO 涉及多表操作
//		String domain = lw.getS_which_domain(), lab = lw.getS_which_lab();
//		if (domain != null && !domain.isEmpty()) {
////			sqlStr.append(" and t.domainid = :domain");
////			params.put("domain", domain);
//		}
//		if (lab != null && !lab.isEmpty()) {
////			sqlStr.append(" and t.labid = :lab");
////			params.put("lab", domain);
//		}
		
		return sqlStr.toString();
	}

	@Override
	public DataGrid datagrid(Zl lw, String username, List<String> usernames) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();

		String hql = "from TZl t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(lw, hql, params, username, usernames);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lw, hql);
		try {
			this.hsql = hql;
			this.params = params;
	        
			List<TZl> l = yjDao.find(hql, params, lw.getPage(), lw.getRows());
			List<Zl> nl = new ArrayList<Zl>();
			changeModel(l, nl, username, usernames);
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
		String hql = "delete TZl t where t.id in (";
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
	public Zl edit(Zl lw) {
		// TODO zhengxuebing
		TZl tlw = yjDao.get(TZl.class, lw.getId());
		BeanUtils.copyProperties(lw, tlw, new String[] { "id" });
		//持久化至数据库
		tlw.setSavetime(new Timestamp(new java.util.Date().getTime()));
		yjDao.saveOrUpdate(tlw);
		return lw;
	}

	@Override
	public List<TZl> findRoleInRange(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TZl> findEntityByHQL(String hql, Object[] objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ThesisByPoi(FileInputStream fis) {
		
		try {
			PatentImportEvent<TZl> importEvent = new PatentImportEvent<TZl>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB("t_zl", fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel(){
		return _importExcel.exportExcel("Patent", tableName, hsql, params);
	}
	
	public class PatentImportEvent<T> implements ImportExcelEventI<T>{

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
			return true;
		}
		
	}

	@Override
	public boolean  removeAll() {
		String hql = "delete TZl t ";
		try{
			yjDao.executeHql(hql);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
