package com.performance.service.impl;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

import javax.xml.xpath.XPathExpressionException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.opensymphony.xwork2.ActionContext;
import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.Award;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Examine;
import com.performance.pagemodel.Gh;
import com.performance.pagemodel.Score;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.ScoreServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TGh;
import com.performances.model.TGhScore;
import com.performances.model.TLw;
import com.performances.model.TUser;
import com.performances.model.TZx;
import com.performances.model.TZxScore;


@Transactional
@Service("scoreService")
public class ScoreServiceImpl extends BaseServiceImpl implements
		ScoreServiceI {

	private SessionFactory sessionFactory;
	private BaseDaoI<TUser> userDao;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		Session session = this.sessionFactory.getCurrentSession();
		return session;
	}
	
	public BaseDaoI<TUser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<TUser> userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 排序  默认所有按照保存时间排序
	 * @param
	 * @param
	 * @return
	 */
	private String addOrder(Score score) {
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append(" order by usercode desc");
		if (score.getSort() != null) {
			sqlStr.append(",");
			sqlStr.append(score.getSort() + " " + score.getOrder());
		}
		
		return sqlStr.toString();
	}

	private String addWhere(Score gh,  Map<String, Object> params,
			String usercode, List<String> usercodes, String authorcode) {
		params.clear();
		
		StringBuilder sqlStr = new StringBuilder(" where 1=1 ");

		// 科研人员
		if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and ");
			sqlStr.append(" t.");
			sqlStr.append(authorcode);
			sqlStr.append(" = :username ");
			params.put("username", usercode);
		}

		// 高级过滤功能
		// 1.年份
		boolean hasYear = false;
		String begin_year = gh.getS_begin_year(), end_year = gh.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
			hasYear = true;
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
			hasYear = true;
		}

		// 1.论文类型
		List<String> thesis_type = gh.getS_which_type();
		if (thesis_type != null && !thesis_type.isEmpty()) {
			sqlStr.append(" and t.fid = r.lwid ");
			if(thesis_type.size() < 3){
				sqlStr.append(" and ( r.pubkind = :type0");
				params.put("type0", thesis_type.get(0));
				for(int i = 1; i < thesis_type.size(); i++){
					sqlStr.append( " or r.pubkind = :type");
					sqlStr.append(i);
					params.put("type"+i, thesis_type.get(i));
				};
				sqlStr.append(") ");
			} else {
				for(int i = 0; i < thesis_type.size(); i++){
					sqlStr.append( " and r.pubkind != :type");
					sqlStr.append(i);
					params.put("type"+i, thesis_type.get(i));
				};
			}
		}
				
		//没有设置年份，默认取最近五年
		if(!hasYear){
			Calendar now = Calendar.getInstance();
			int nowYear = now.get(Calendar.YEAR);
			String beginyear = String.valueOf(nowYear - 4);
			String endyear = String.valueOf(nowYear);
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", beginyear);
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", endyear);

		}
		
		sqlStr.append( " group by ");
		sqlStr.append(authorcode);
		
		return sqlStr.toString();
	}

	//
	private String addWhereReportLbcn(Score gh,  Map<String, Object> params,
							String usercode, List<String> usercodes, String authorcode) {
		params.clear();

		StringBuilder sqlStr = new StringBuilder(" where 1=1 ");

		// 科研人员
		if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and ");
			sqlStr.append(" t.");
			sqlStr.append(authorcode);
			sqlStr.append(" = :username ");
			params.put("username", usercode);
		}

		// 高级过滤功能
		// 1.年份
		boolean hasYear = false;
		String begin_year = gh.getS_begin_year(), end_year = gh.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
			hasYear = true;
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
			hasYear = true;
		}

		// 1.咨询报告类型为“两办采纳”，单独计分，累计最大值为20分
		sqlStr.append(" and t.fid = z.zxid");
		sqlStr.append(" and  z.auditlevel = '两办采纳'");



		//没有设置年份，默认取最近五年
		if(!hasYear){
			Calendar now = Calendar.getInstance();
			int nowYear = now.get(Calendar.YEAR);
			String beginyear = String.valueOf(nowYear - 4);
			String endyear = String.valueOf(nowYear);
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", beginyear);
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", endyear);

		}

		sqlStr.append( " group by ");
		sqlStr.append(authorcode);

		return sqlStr.toString();
	}

	private String addProjectWhere(Score gh,  Map<String, Object> params,
			String usercode, List<String> usercodes, String authorcode) {
		params.clear();
		
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append(" where 1=1 ");
		// 科研人员
		if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and ");
			sqlStr.append(" t.");
			sqlStr.append(authorcode);
			sqlStr.append(" = :username ");
			params.put("username", usercode);
		}

		// 高级过滤功能
		// 1.年份
		boolean hasYear = false;
		String begin_year = gh.getS_begin_year(), end_year = gh.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.endTime >= :beginyear");
			params.put("beginyear", begin_year);
			hasYear = true;
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.beginTime <= :endyear");
			params.put("endyear", end_year);
			hasYear = true;
		}

		//没有设置年份，默认取最近五年
		if(!hasYear){
			Calendar now = Calendar.getInstance();
			int nowYear = now.get(Calendar.YEAR);
			String beginyear = String.valueOf(nowYear - 4);
			//String endyear = String.valueOf(nowYear);

			sqlStr.append(" and t.endTime >= :beginyear");
			params.put("beginyear", beginyear);

//			sqlStr.append(" and t.endTime <= :endyear");
//			params.put("endyear", endyear);


		}
		
		sqlStr.append( " group by ");
		sqlStr.append(authorcode);
		
		return sqlStr.toString();
	}
	
	private String addUserWhere(Score score,  String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" where IsNum(t.usercode)=1 ");
		// 科研人员
		if(usercode != null &&!usercode.equals("")&&usercodes == null){
			sqlStr.append(" and ");
			sqlStr.append(" t.usercode = :username ");
			params.put("username", usercode);
		}

		// 高级过滤功能
		String author = score.getS_lw_author();
		if (author != null && !author.isEmpty()) {
			sqlStr.append(" and (");
			sqlStr.append(" t.name like :name ");
			sqlStr.append(")");
			params.put("name", "%%" + author.trim() + "%%");
		}

		String authorcode = score.getS_lw_authorcode();
		if (authorcode != null && !authorcode.isEmpty()) {
			sqlStr.append(" and (");
			sqlStr.append(" t.usercode = :usercode ");
			sqlStr.append(")");
			params.put("usercode",  authorcode);
		}

		String domain = score.getS_which_domain();
		if (domain != null && !domain.isEmpty()) {
			sqlStr.append(" and (");
			sqlStr.append(" t.domain like :domain ");
			sqlStr.append(")");
			params.put("domain", "%%" + domain.trim() + "%%");
		}
				
		sqlStr.append( "order by domain, usercode");
		
		return sqlStr.toString();
	}
	
	private Map<String, Double> GetScores(String hql, Map<String,Object> params){
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List l = q.list();
		Map<String, Double> maps = new HashMap<String, Double>();
		for(int i = 0; i < l.size(); i++){
			Object[] item = (Object[])l.get(i);
			if(item[0] != null && item[0].toString().trim() != ""){
				maps.put(item[0].toString(), Double.parseDouble(item[1].toString()));
			}
		}
		
		return maps;
	}
	
	private void changeModel(List<TUser> l, List<Score> nl, 
		Map<String, Double> sciThesis, Map<String, Double> inlandThesis, Map<String, Double> otherThesis, Map<String, Double> report, Map<String, Double> reportLbcn,
		Map<String, Double> award, Map<String, Double> writing,Map<String, Double> plan,Map<String, Double> thirdAssess, Map<String, Double> standard,
		Map<String, Double> map, Map<String, Double> software,Map<String, Double> patent, Map<String, Double> project,
		Map<String, Double> lessons, Map<String, Double> gradute, Map<String, Double> publish, Map<String, Double> achievetrans){
		int i = 0;
		if (l != null && l.size() > 0) {
			for (TUser t : l) {
				Score score = new Score();
				score.setId(i++);
				score.setDomain(t.getDomain());
				score.setName(t.getName());
				String usercode = t.getUsercode();
				score.setUsercode(usercode);
				
				if(sciThesis.containsKey(usercode)){
					score.setSciThesisScore(sciThesis.get(usercode));
				}
				
				if(inlandThesis.containsKey(usercode)){
					score.setInlandThesisScore(inlandThesis.get(usercode));
				}
				
				if(otherThesis.containsKey(usercode)){
					score.setOtherThesisScore(otherThesis.get(usercode));
				}
				
				if(report.containsKey(usercode)){
					double reportFinal = report.get(usercode);
					if(reportLbcn.containsKey(usercode)){
						if(reportLbcn.get(usercode) > 20 ){
							//咨询报告两办采纳总分不超过20分
							double diss = reportLbcn.get(usercode) - 20;
							reportFinal -= diss;
						}
					}
					score.setReportScore(reportFinal);
				}
//咨询报告两办采纳总分
				if(reportLbcn.containsKey(usercode)){
					score.setReportLbcnScore(reportLbcn.get(usercode));
				}
				
				if(award.containsKey(usercode)){
					score.setAwardScore(award.get(usercode));
				}
				
				if(writing.containsKey(usercode)){
					score.setWritingScore(writing.get(usercode));
				}
				
				if(plan.containsKey(usercode)){
					score.setPlanScore(plan.get(usercode));
				}

				if(thirdAssess.containsKey(usercode)){
					score.setThirdAssessScore(thirdAssess.get(usercode));
				}
				
				if(standard.containsKey(usercode)){
					score.setStandardScore(standard.get(usercode));
				}
				
				if(map.containsKey(usercode)){
					score.setMapScore(map.get(usercode));
				}
				
				if(software.containsKey(usercode)){
					score.setSoftwareScore(software.get(usercode));
				}
				
				if(patent.containsKey(usercode)){
					score.setPatentScore(patent.get(usercode));
				}
				
				if(project.containsKey(usercode)){
					score.setProjectScore(project.get(usercode));
				}
				
				if(lessons.containsKey(usercode)){
					score.setLessonScore(lessons.get(usercode));
				}
				
				if(gradute.containsKey(usercode)){
					score.setGraduteScore(gradute.get(usercode));
				}

				if(publish.containsKey(usercode)){
					double publishFinal = publish.get(usercode);
					if(publish.get(usercode) > 5){
						publishFinal = 5;
					}
					score.setPublishScore(publishFinal);
				}

				if(achievetrans.containsKey(usercode)){
					score.setAchievetransScore(achievetrans.get(usercode));
				}
				
				//设置申报登记
				score.setLevel(t.getUserCategory());
				
				nl.add(score);
			}
		}
	}
	
	@Override
	public DataGrid datagrid(Score score, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			
			//1.获取SCI/SSCI论文总分 
			String hql = "select t.usercode, sum(t.score) from TLwScore as t, TLw as r";
			List<String> thesisTypes = new ArrayList<String>();
			thesisTypes.add("SCI收录");
			thesisTypes.add("SSCI收录");
			score.setS_which_type(thesisTypes);
			String where = addWhere(score, params, usercode, usercodes, "usercode");
			hql = hql + where;
			Map<String, Double> mapsSciThesis = GetScores(hql, params);
			
			//2.获取国内刊物论文总分
			hql = "select t.usercode, sum(t.score) from TLwScore as t, TLw as r";
			thesisTypes.clear();
			thesisTypes.add("国内刊物");
			score.setS_which_type(thesisTypes);
			where = addWhere(score, params, usercode, usercodes, "usercode");
			hql = hql + where;
			Map<String, Double> mapsInlandThesis = GetScores(hql, params);
			
			//3.获取国内刊物论文总分
			hql = "select t.usercode, sum(t.score) from TLwScore as t, TLw as r";
			thesisTypes.clear();
			thesisTypes.add("SCI收录");
			thesisTypes.add("SSCI收录");
			thesisTypes.add("国内刊物");
			score.setS_which_type(thesisTypes);
			where = addWhere(score, params, usercode, usercodes, "usercode");
			hql = hql + where;
			Map<String, Double> mapsOtherThesis = GetScores(hql, params);
			score.setS_which_type(null);


			//获取类型为“两办采纳”的咨询报告的总分
			hql = "select t.usercode, sum(t.score) from TZxScore as t, TZx as z ";
			where = addWhereReportLbcn(score, params, usercode, usercodes, "usercode");
			hql = hql + where;
			Map<String, Double> mapsReportLbcn = GetScores(hql, params);
			//2.获取咨询报告总分
			hql = "select usercode, sum(score) from TZxScore t ";
			where = addWhere(score, params, usercode, usercodes, "usercode");
			hql = hql + where;
			Map<String, Double> mapsReport = GetScores(hql, params);


			//3.获取奖励总分
			hql = "select usercode, sum(score) from TAwardScore t ";
			hql = hql + where;
			Map<String, Double> mapsAward = GetScores(hql, params);
			//4.获取著作总分
			hql = "select usercode, sum(score) from TWritingScore t ";
			hql = hql + where;
			Map<String, Double> mapsWriting = GetScores(hql, params);
			//5.获取规划总分
			hql = "select usercode, sum(score) from TGhScore t ";
			hql = hql + where;
			Map<String, Double> mapsPlan = GetScores(hql, params);
			//6.获取标准总分
			hql = "select usercode, sum(score) from TBzScore t ";
			hql = hql + where;
			Map<String, Double> mapsStandard = GetScores(hql, params);	
			//7.获取地图总分
			hql = "select usercode, sum(score) from TDtScore t ";
			hql = hql + where;
			Map<String, Double> mapsMap = GetScores(hql, params);	
			//8.获取软件总分
			hql = "select usercode, sum(score) from TRjScore t ";
			hql = hql + where;
			Map<String, Double> mapsSoftware = GetScores(hql, params);	
			//9.获取专利总分
			hql = "select usercode, sum(score) from TZlScore t ";
			hql = hql + where;
			Map<String, Double> mapsPatent = GetScores(hql, params);
			//13.获取数据出版总分
			hql = "select usercode, sum(score) from TPublishScore t ";
			hql = hql + where;
			Map<String, Double> mapsPublish = GetScores(hql, params);
			//14.获取成果转化总分
			hql = "select usercode, sum(score) from TAchievetransScore t ";
			hql = hql + where;
			Map<String, Double> mapsAchievetrans = GetScores(hql, params);
			//15.获取第三方评估总分
			hql = "select usercode, sum(score) from TTaScore t ";
			hql = hql + where;
			Map<String, Double> mapsThirdAssess = GetScores(hql, params);
			//10.获取项目总分
			where = addProjectWhere(score, params, usercode, usercodes, "subLeaderCode");
			hql = "select subLeaderCode as usercode, sum(score) from TXm t ";
			hql = hql + where;
			Map<String, Double> mapsProject = GetScores(hql, params);
			//11.获取授课总分
			where = addWhere(score, params, usercode, usercodes, "teacherCode");
			hql = "select teacherCode as usercode, sum(score) from TSk t ";
			hql = hql + where;
			Map<String, Double> mapsLesson = GetScores(hql, params);	
			//12.获取研究生获奖总分
			hql = "select teacherCode as usercode, sum(score) from TYj t ";
			hql = hql + where;
			Map<String, Double> mapsGradute = GetScores(hql, params);

			//15.获取作者信息
			hql = "from TUser t ";
			Map<String, Object> userParams = new HashMap<String, Object>();
			hql = addUserWhere(score, hql, userParams, usercode, usercodes);
			String totalHql = "select count(*) " + hql;
			List<TUser> l = userDao.find(hql, userParams, score.getPage(), score.getRows());
			List<Score> nl = new ArrayList<>();
			changeModel(l, nl, mapsSciThesis, mapsInlandThesis, mapsOtherThesis, mapsReport, mapsReportLbcn, mapsAward,
					mapsWriting, mapsPlan,mapsThirdAssess, mapsStandard, mapsMap, mapsSoftware, mapsPatent,
					mapsProject, mapsLesson, mapsGradute, mapsPublish, mapsAchievetrans);
			dg.setTotal(userDao.count(totalHql, userParams));
			dg.setRows(nl);
			
			ActionContext actionContext = ActionContext.getContext();
	        Map session = actionContext.getSession();
	        session.put("SCORE_USERCODE", usercode);
	        session.put("SCORE_OBJECT", score);
	        session.put("SCORE_USERCODES", usercodes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}

	private File exportExcel(File file) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, XPathExpressionException
	{
		Workbook wb=new XSSFWorkbook();
		 
		Sheet sheet=wb.createSheet();
		org.apache.poi.ss.usermodel.Row header=sheet.createRow(0);
		
		//写导出表头
		int cellIndex = 0;
		String headers[] = {"序号", "研究领域", "名称", "用户编号", "科研绩效合计", "SCI和SSCI论文", "国内论文", "其他论文", "咨询报告", "专利", "软件", "标准", "规划", "第三方评估","著作", "地图集", "项目大小", "国家及省部级奖励", "学生得奖", "授课","数据出版","成果转化", "申报级别"};
		for (int i=0; i < headers.length; i++) {
			header.createCell(cellIndex++).setCellValue(headers[i]);
		}
		
		//2.写表内容数据
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        Score score = (Score)session.get("SCORE_OBJECT");
		String usercode = (String)session.get("SCORE_USERCODE"); 
		List<String> usercodes = (List<String>)session.get("SCORE_USERCODES"); 

		Map<String, Object> params = new HashMap<String, Object>();
		
		//1.获取SCI/SSCI论文总分 
		String hql = "select t.usercode, sum(t.score) from TLwScore as t, TLw as r";
		List<String> thesisTypes = new ArrayList<String>();
		thesisTypes.add("SCI收录");
		thesisTypes.add("SSCI收录");
		score.setS_which_type(thesisTypes);
		String where = addWhere(score, params, usercode, usercodes, "usercode");
		hql = hql + where;
		Map<String, Double> mapsSciThesis = GetScores(hql, params);
		
		//2.获取国内刊物论文总分
		hql = "select t.usercode, sum(t.score) from TLwScore as t, TLw as r";
		thesisTypes.clear();
		thesisTypes.add("国内刊物");
		score.setS_which_type(thesisTypes);
		where = addWhere(score, params, usercode, usercodes, "usercode");
		hql = hql + where;
		Map<String, Double> mapsInlandThesis = GetScores(hql, params);
		
		//3.获取国内刊物论文总分
		hql = "select t.usercode, sum(t.score) from TLwScore as t, TLw as r";
		thesisTypes.clear();
		thesisTypes.add("SCI收录");
		thesisTypes.add("SSCI收录");
		thesisTypes.add("国内刊物");
		score.setS_which_type(thesisTypes);
		where = addWhere(score, params, usercode, usercodes, "usercode");
		hql = hql + where;
		Map<String, Double> mapsOtherThesis = GetScores(hql, params);
		score.setS_which_type(null);

		//获取类型为“两办采纳”的咨询报告的总分
		hql = "select t.usercode, sum(t.score) from TZxScore as t, TZx as z ";
		where = addWhereReportLbcn(score, params, usercode, usercodes, "usercode");
		hql = hql + where;
		Map<String, Double> mapsReportLbcn = GetScores(hql, params);
		//2.获取咨询报告总分
		hql = "select usercode, sum(score) from TZxScore t ";
		where = addWhere(score, params, usercode, usercodes, "usercode");
		hql = hql + where;
		Map<String, Double> mapsReport = GetScores(hql, params);

		//3.获取奖励总分
		hql = "select usercode, sum(score) from TAwardScore t ";
		hql = hql + where;
		Map<String, Double> mapsAward = GetScores(hql, params);
		//4.获取著作总分
		hql = "select usercode, sum(score) from TWritingScore t ";
		hql = hql + where;
		Map<String, Double> mapsWriting = GetScores(hql, params);
		//5.获取规划总分
		hql = "select usercode, sum(score) from TGhScore t ";
		hql = hql + where;
		Map<String, Double> mapsPlan = GetScores(hql, params);
		//5.获取第三方评估总分
		hql = "select usercode, sum(score) from TTaScore t ";
		hql = hql + where;
		Map<String, Double> mapsThirdAssess = GetScores(hql, params);
		//6.获取标准总分
		hql = "select usercode, sum(score) from TBzScore t ";
		hql = hql + where;
		Map<String, Double> mapsStandard = GetScores(hql, params);	
		//7.获取地图总分
		hql = "select usercode, sum(score) from TDtScore t ";
		hql = hql + where;
		Map<String, Double> mapsMap = GetScores(hql, params);	
		//8.获取软件总分
		hql = "select usercode, sum(score) from TRjScore t ";
		hql = hql + where;
		Map<String, Double> mapsSoftware = GetScores(hql, params);	
		//9.获取专利总分
		hql = "select usercode, sum(score) from TZlScore t ";
		hql = hql + where;
		Map<String, Double> mapsPatent = GetScores(hql, params);
		//13.获取数据出版总分
		hql = "select usercode, sum(score) from TPublishScore t ";
		hql = hql + where;
		Map<String, Double> mapsPublish = GetScores(hql, params);
		//14.获取成果转化总分
		hql = "select usercode, sum(score) from TAchievetransScore t ";
		hql = hql + where;
		Map<String, Double> mapsAchievetrans = GetScores(hql, params);
		//10.获取项目总分
		where = addProjectWhere(score, params, usercode, usercodes, "subLeaderCode");
		hql = "select subLeaderCode as usercode, sum(score) from TXm t ";
		hql = hql + where;
		Map<String, Double> mapsProject = GetScores(hql, params);	
		//11.获取授课总分
		where = addWhere(score, params, usercode, usercodes, "teacherCode");
		hql = "select teacherCode as usercode, sum(score) from TSk t ";
		hql = hql + where;
		Map<String, Double> mapsLesson = GetScores(hql, params);	
		//12.获取研究生获奖总分
		hql = "select teacherCode as usercode, sum(score) from TYj t ";
		hql = hql + where;
		Map<String, Double> mapsGradute = GetScores(hql, params);

		//15.获取作者信息
		hql = "from TUser t ";
		Map<String, Object> userParams = new HashMap<String, Object>();
		hql = addUserWhere(score, hql, userParams, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		List<TUser> l = userDao.find(hql, userParams);
		
		DecimalFormat df = new DecimalFormat("####0.00"); 
		for (int i=0; i < l.size(); i++) {
			cellIndex = 0;
			double total = 0;
			TUser user = l.get(i);
			String itemUserCode = user.getUsercode();
			org.apache.poi.ss.usermodel.Row row=sheet.createRow(i + 1);
			row.createCell(cellIndex++).setCellValue(i+1);						//1.序号
			row.createCell(cellIndex++).setCellValue(user.getDomain());			//2.领域
			row.createCell(cellIndex++).setCellValue(user.getName());			//3.名称
			row.createCell(cellIndex++).setCellValue(itemUserCode);				//4.用户编号
			
			row.createCell(cellIndex++).setCellValue("");						//5.绩效总分
			
			if(mapsSciThesis.containsKey(itemUserCode)){						//6.SCI和SSCI
				total += mapsSciThesis.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsSciThesis.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}
			
			if(mapsInlandThesis.containsKey(itemUserCode)){						//7.国内论文
				total += mapsInlandThesis.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsInlandThesis.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}
			
			if(mapsOtherThesis.containsKey(itemUserCode)){						//8.其他论文
				total += mapsOtherThesis.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsOtherThesis.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}
			
			if(mapsReport.containsKey(itemUserCode)){							//9.咨询报告
				//判断咨询报告两办采纳类型的累计分数，超过20分按20分计算
				double reportFinal = mapsReport.get(itemUserCode);
		      if(mapsReportLbcn.containsKey(itemUserCode)){
				  if(mapsReportLbcn.get(itemUserCode) > 20){
					  double diss = mapsReportLbcn.get(itemUserCode)-20;
					  reportFinal -= diss;
				  }
			  }
				total += reportFinal;
				row.createCell(cellIndex++).setCellValue(mapsReport.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}

			
			if(mapsPatent.containsKey(itemUserCode)){							//10.专利
				total += mapsPatent.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsPatent.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}
			
			if(mapsSoftware.containsKey(itemUserCode)){							//11.软件
				total += mapsSoftware.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsSoftware.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}
			
			if(mapsStandard.containsKey(itemUserCode)){							//12.标准
				total += mapsStandard.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsStandard.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}
			
			if(mapsPlan.containsKey(itemUserCode)){								//13.规划
				total += mapsPlan.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsPlan.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}

			if(mapsThirdAssess.containsKey(itemUserCode)){								//13.规划
				total += mapsThirdAssess.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsThirdAssess.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}
			
			if(mapsWriting.containsKey(itemUserCode)){							//14.著作
				total += mapsWriting.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsWriting.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}
			
			if(mapsMap.containsKey(itemUserCode)){								//15.地图集
				total += mapsMap.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsMap.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}

			if(mapsProject.containsKey(itemUserCode)){							//16.项目大小
				total += mapsProject.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsProject.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}

			if(mapsAward.containsKey(itemUserCode)){							//17.国家及省部级奖励
				total += mapsAward.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsAward.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}

			if(mapsGradute.containsKey(itemUserCode)){							//18.学生得奖
				total += mapsGradute.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsGradute.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}

			if(mapsLesson.containsKey(itemUserCode)){							//19.授课
				total += mapsLesson.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsLesson.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}

			if(mapsPublish.containsKey(itemUserCode)){								//20.数据出版
				double publishFinal = mapsPublish.get(itemUserCode);
				if(publishFinal > 5){
					publishFinal = 5;
				}
				total += publishFinal;
				row.createCell(cellIndex++).setCellValue(mapsPublish.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}

			if(mapsAchievetrans.containsKey(itemUserCode)){								//21.成果转化
				total += mapsAchievetrans.get(itemUserCode);
				row.createCell(cellIndex++).setCellValue(mapsAchievetrans.get(itemUserCode));
			} else {
				row.createCell(cellIndex++).setCellValue(0);
			}
																				
			row.createCell(cellIndex++).setCellValue(user.getUserCategory());				//19.申报级别
			row.getCell(4).setCellValue(df.format(total));									//20。设置总分							
		}
		
		FileOutputStream fileOut = new FileOutputStream(file);
		wb.write(fileOut);
		fileOut.close();
		
		return file;
	}
	
	private String getFileName(String path, String fileName){
		String strFile = path + "/" + fileName + ".xlsx";
		String result = fileName;
		File file = new File(strFile);
		int i = 0;
		
		while(file.exists()){
			try
			{
				file.delete();
			}catch(Exception e){
				i++;
				strFile = path + "/" + fileName + i + ".xlsx";
				result = fileName + i;
				file = new File(strFile);
			}
		}
		
		return result;
	}
	
	@Override
	public String exportExcel(){
		try {
			//获取上传文件路径，应在配置文件中定义
			Properties prop = new Properties();
			String propFileName = "config.properties";

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				try{
					prop.load(inputStream);
					inputStream.close();
				}catch(IOException e){
//				this.success = false;
//				this.message = "加载config.property文件出错！";
//				return ERROR;
				}
			} else {
				//throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
//			this.success = false;
//			this.message = "未发现配置文件config.property";
//			return ERROR;
			}

			String path = prop.getProperty("uploadDirectory");
			File pathFile = new File(path);
			if(!pathFile.exists()){
				pathFile.mkdir();
			}

			String realFileName = getFileName(path , "Score");
			String strFile = path + "/" + realFileName + ".xlsx";
			File file = new File (strFile);

			file = exportExcel(file);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | XPathExpressionException
				| IOException e) {
			e.printStackTrace();
		}
		return "Score.xlsx";
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
