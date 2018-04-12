package com.performance.service.impl;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPathExpressionException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.sql.HSQLCaseFragment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.performance.action.ThesisAction;
import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Examine;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.LwStutas;
import com.performance.pagemodel.Zx;
import com.performance.service.ImportExcelEventI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.ThesisServiceI;
import com.performance.util.excel.JavaClass;
import com.performances.model.TDomain;
import com.performances.model.TLw;
import com.performances.model.TLwScore;
import com.performances.model.TZx;
import com.performances.model.TZxScore;

@Transactional
@Service("thesisService")
public class ThesisServiceImpl extends BaseServiceImpl implements
		ThesisServiceI {

	private BaseDaoI<TLw> lwDao;

	private static final  String tableName = "t_lw";
	private String hsql;
	private Map<String, Object> params;


	public BaseDaoI<TLw> getLwDao() {
		return lwDao;
	}

	@Autowired
	public void setLwDao(BaseDaoI<TLw> lwDao) {
		this.lwDao = lwDao;
	}

	@Autowired
	private ImportExcelServiceI<TLw> _importExcel;
	
	@Override
	public Lw save(Lw lw) {
		TLw t = new TLw();
		BeanUtils.copyProperties(lw, t);
		lwDao.save(t);
		//BeanUtils.copyProperties(t, lw);
		return lw;
	}

	@Override
	public DataGrid datagrid(Lw lw) {
		DataGrid dg = new DataGrid();
		String hql = "from TLw t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(lw, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lw, hql);
		List<TLw> l = lwDao.find(hql, params, lw.getPage(), lw.getRows());
		List<Lw> nl = new ArrayList<Lw>();
		changeModel(l, nl);
		dg.setTotal(lwDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TLw> l, List<Lw> nl) {
		if (l != null && l.size() > 0) {
			for (TLw t : l) {
				Lw r = new Lw();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}
	
	/**
	 * 排序  默认所有按照保存时间排序
	 * @param lw
	 * @param hql
	 * @return
	 */
	private String addOrder(Lw lw, String hql) {
		StringBuilder sqlStr = new StringBuilder(hql);
		if (lw.getSort() != null) {
			sqlStr.append(" order by ");
			sqlStr.append(lw.getSort() + " " + lw.getOrder());
		}
		
		return sqlStr.toString();
	}

	private String addWhere(Lw lw, String hql, Map<String, Object> params) {
		if (lw.getTitle() != null && !lw.getTitle().trim().equals("")) {

			hql += " where  t.title like :title";
			params.put("title", "%%" + lw.getTitle().trim() + "%%");
		}
		return hql;
	}

	private String addWhere(Lw lw, String hql, Map<String, Object> params,
			String usercode, List<String> usercodes) {
		// private String addWhere(Lw lw, String hql, Map<String, Object>
		// params, String username) {
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
			sqlStr.append(" or t.commauthorcode = :username ");
			sqlStr.append(" or t.fiveauthorcode = :username ");
			sqlStr.append(" or t.otherauthorcode like :otherusername");
			sqlStr.append(")");
			params.put("username", usercode);
			params.put("otherusername", "%%,"+usercode+",%%");
		}else {
			// 是秘书
			if (usercode == null && usercodes.size() != 0 || usercodes != null) {
				for (int i = 0; i < usercodes.size(); i++) {
					if (i == 0) {
						sqlStr.append(" and t.examinestatus != '已保存' and t.examinestatus != '已退回' ");
						sqlStr.append(" and (( ");
						sqlStr.append(" t.firstauthorcode = '" + usercodes.get(0) + "'");
						sqlStr.append(" or t.secauthorcode = '" + usercodes.get(0) + "'");
						sqlStr.append(" or t.threeauthorcode = '" + usercodes.get(0) + "'");
						sqlStr.append(" or t.fourauthorcode = '" + usercodes.get(0) + "'");
						sqlStr.append(" or t.fiveauthorcode = '" + usercodes.get(0) + "'");
						sqlStr.append(" or t.commauthorcode = '" + usercodes.get(0) + "'");
						sqlStr.append(" or t.otherauthorcode like '%%" + "," + usercodes.get(0) + "," + "%%'");
						sqlStr.append(") ");
					} else {
						sqlStr.append(" or ( ");
						sqlStr.append(" t.firstauthorcode = '" + usercodes.get(i) + "'");
						sqlStr.append(" or t.secauthorcode = '" + usercodes.get(i) + "'");
						sqlStr.append(" or t.threeauthorcode = '" + usercodes.get(i) + "'");
						sqlStr.append(" or t.fourauthorcode = '" + usercodes.get(i) + "'");
						sqlStr.append(" or t.fiveauthorcode = '" + usercodes.get(i) + "'");
						sqlStr.append(" or t.commauthorcode = '" + usercodes.get(i) + "'");
						sqlStr.append(" or t.otherauthorcode like '%%" + "," + usercodes.get(i) + "," + "%%'");
						sqlStr.append(")");
					}
				}
				sqlStr.append(") ");
			} else if (usercode != null) {
				sqlStr.append(" and t.examinestatus != '已保存' ");
				sqlStr.append(" and (");
				sqlStr.append(" t.firstauthorcode = :username ");
				sqlStr.append(" or t.secauthorcode = :username ");
				sqlStr.append(" or t.threeauthorcode = :username ");
				sqlStr.append(" or t.fourauthorcode = :username ");
				sqlStr.append(" or t.commauthorcode = :username ");
				sqlStr.append(" or t.fiveauthorcode = :username ");
				sqlStr.append(" or t.otherauthorcode like :otherusername ");
				sqlStr.append(")");
				params.put("username", usercode);
				params.put("otherusername", "%%," + usercode + ",%%");
			}
		}

		if (lw.getTitle() != null && !lw.getTitle().trim().equals("")) {
			if (usercode.equals("") && usercodes == null) {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + lw.getTitle().trim() + "%%");
			} else {
				sqlStr.append(" and t.title like :title");
				params.put("title", "%%" + lw.getTitle().trim() + "%%");
			}
		}

		// 根据论文审核状态过滤
		if (lw.getAuditfilter() != null
				&& !lw.getAuditfilter().equalsIgnoreCase("")) {
			if (!lw.getAuditfilter().equals("全部")) {
				sqlStr.append(" and t.examinestatus = :examinestatus");
				params.put("examinestatus", lw.getAuditfilter());
			}
		}
		
		// 高级过滤功能
		// 1.年份
		String begin_year = lw.getS_begin_year(), end_year = lw.getS_end_year();
		if (begin_year != null && !begin_year.isEmpty()) {
			sqlStr.append(" and t.year >= :beginyear");
			params.put("beginyear", begin_year);
		}
		if (end_year != null && !end_year.isEmpty()) {
			sqlStr.append(" and t.year <= :endyear");
			params.put("endyear", end_year);
		}
		// 2.论文名称
		String lw_name = lw.getS_lw_name();
		if (lw_name != null && !lw_name.isEmpty()) {
			sqlStr.append(" and t.title like :title");
			params.put("title", "%%" + lw_name + "%%");
		}
		
		// add by @zheng 添加期刊名称过滤
		String lw_pubname = lw.getS_lw_pubname();
		if(lw_pubname != null && !lw_pubname.isEmpty()){
			sqlStr.append(" and t.pubtitle like :pubname");
			params.put("pubname", "%%" + lw_pubname + "%%");
		}
		
		// 3.论文作者
		String lw_author = lw.getS_lw_author();
		if(lw_author != null && !lw_author.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthor like :susername ");
			sqlStr.append(" or t.secauthor like :susername ");
			sqlStr.append(" or t.threeauthor like :susername ");
			sqlStr.append(" or t.fourauthor like :susername ");
			sqlStr.append(" or t.commauthor like :susername ");
			sqlStr.append(" or t.fiveauthor like :susername ");
			sqlStr.append(" or t.otherauthor like :sotherusername ");
			sqlStr.append(")");
			params.put("susername", "%%" + lw_author+ "%%");
			params.put("sotherusername", "%%" + lw_author + "%%");
		}
		
		// add by zhengxb 添加根据作者编号过滤
		String lw_authorcode = lw.getS_lw_authorcode();
		if(lw_authorcode != null && !lw_authorcode.isEmpty()){
			sqlStr.append(" and (");
			sqlStr.append(" t.firstauthorcode = :authorcode ");
			sqlStr.append(" or t.secauthorcode = :authorcode ");
			sqlStr.append(" or t.threeauthorcode = :authorcode ");
			sqlStr.append(" or t.fourauthorcode = :authorcode ");
			sqlStr.append(" or t.commauthorcode = :authorcode ");
			sqlStr.append(" or t.fiveauthorcode = :authorcode ");
			sqlStr.append(" or t.otherauthorcode like :otherauthorcode ");
			sqlStr.append(")");
			params.put("authorcode",lw_authorcode);
			params.put("otherauthorcode", "%%" + lw_authorcode + "%%");
		}

		// 4.领域、研究室   //TODO 涉及多表操作
		String domain = lw.getS_which_domain(), lab = lw.getS_which_lab();
		if (domain != null && !domain.isEmpty()) {
//			sqlStr.append(" and t.domainid = :domain");
//			params.put("domain", domain);
		}
		if (lab != null && !lab.isEmpty()) {
//			sqlStr.append(" and t.labid = :lab");
//			params.put("lab", domain);
		}
		return sqlStr.toString();
	}

	@Override
	public DataGrid datagrid(Lw lw, String usercode, List<String> usercodes) {
		DataGrid dg = new DataGrid();

		String hql = "from TLw t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(lw, hql, params, usercode, usercodes);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lw, hql);
		try {
//			ActionContext actionContext = ActionContext.getContext();
//	        Map session = actionContext.getSession();
//	        session.put("QUERY_TABLE", "t_lw");
//	        session.put("QUERY_HSQL", hql);
//	        session.put("QUERY_PARAMS", params);

			this.hsql = hql;
			this.params = params;
	        
			List<TLw> l = lwDao.find(hql, params, lw.getPage(), lw.getRows());
			List<Lw> nl = new ArrayList<Lw>();
			changeModel(l, nl, usercode, usercodes);
			dg.setTotal(lwDao.count(totalHql, params));
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
		String hql = "delete TLw t where t.lwid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		// roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		lwDao.executeHql(hql);
	}

	@Override
	public Lw edit(Lw lw) {
		//todo 需要更改为只更新发送过来的列
		TLw tlw = lwDao.get(TLw.class, lw.getLwid());
		BeanUtils.copyProperties(lw, tlw, new String[] { "lwid", "submitUser", "submitTime"});
		//持久化至数据库
		lwDao.saveOrUpdate(tlw);
		return lw;
	}

	/**
	 * 论文提交操作
	 * 
	 * @param lwList 提交的论文列表
	 * @return
	 */
	@Override
	public List<Lw> submit(List<Lw> lwList, String submitUser) {
		for (Lw lw : lwList) {
			TLw tlw = lwDao.get(TLw.class, lw.getLwid());
			tlw.setExaminestatus(Examine.EXAMINE_SUBMIT.getExamine());
			tlw.setSubmitUser(submitUser);
			tlw.setSubmitTime(new Timestamp(new java.util.Date().getTime()));
			lwDao.saveOrUpdate(tlw);
		}
		return lwList;
	}
	
	
	/**
	 * 论文撤销提交
	 * 
	 * @param lwList 需要撤销提交的论文列表
	 */
	@Override
	public List<Lw> revoke(List<Lw> lwList) {
		for (Lw lw : lwList) {
			TLw tlw = lwDao.get(TLw.class, lw.getLwid());
			tlw.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
			lwDao.saveOrUpdate(tlw);
		}
		return lwList;
	}

	/**
	 * 论文审核操作
	 */
	@Override
	public List<Lw> audit(String auditStatus, String auditUser, String auditOpinion, List<Lw> lwList) {
		if ("pass".equalsIgnoreCase(auditStatus)) {
			for (Lw lw : lwList) {
				TLw tlw = lwDao.get(TLw.class, lw.getLwid());
				tlw.setAuditOpinion(auditOpinion);
				tlw.setExaminestatus(Examine.EXAMINE_COMPLETE.getExamine());
				tlw.setAuditUser(auditUser);
				// 持久化至数据库
				lwDao.saveOrUpdate(tlw);
			}
		} else if ("reject".equalsIgnoreCase(auditStatus)) {
			for (Lw lw : lwList) {
				TLw tlw = lwDao.get(TLw.class, lw.getLwid());
				tlw.setAuditOpinion(auditOpinion);
				tlw.setExaminestatus(Examine.EXAMINE_REJECT.getExamine());
				tlw.setAuditUser(auditUser);
				// 持久化至数据库
				lwDao.saveOrUpdate(tlw);
			}
		}

		return lwList;
	}

	@Override
	public List<TLw> findRoleInRange(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TLw> findEntityByHQL(String hql, Object[] objects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ThesisByPoi(FileInputStream fis) {
		try {
			ThesisImportEvent<TLw> importEvent = new ThesisImportEvent<TLw>();
			_importExcel.setEventProxy(importEvent);
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String exportExcel(){
		return _importExcel.exportExcel("Thesis", tableName, hsql, params);
	}

	//读出包含绩效分数的全部论文数据，并直接导出到Excel
	@Override
	public String exportExcelIncludingScore(){
		List<TLw> l = lwDao.find("from TLw");
		List<Lw> nl = new ArrayList<Lw>();
		changeModel(l, nl, "", null);

//导出到Excel
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

				}
			}

			String path = prop.getProperty("uploadDirectory");
			File pathFile = new File(path);
			if(!pathFile.exists()){
				pathFile.mkdir();
			}

			//1.判断是否有重复文件，有则删除，否则新增
			String realFileName = getFileName(path , "ALLThesisIncludingScore");
			String strFile = path + "/" + realFileName + ".xlsx";
			File file = new File (strFile);
			file = buildExcel(file, nl);
		} catch (Exception e) {
			e.printStackTrace();

		}
		finally {
			return "ALLThesisIncludingScore.xlsx";
		}
	}

	private File buildExcel(File file,List<Lw> nl)  {

		Workbook wb=new XSSFWorkbook();

		Sheet sheet=wb.createSheet();
		org.apache.poi.ss.usermodel.Row header=sheet.createRow(0);

		//写导出表头
		int cellIndex = 0;
		String headers[] = {"年度", "刊物类别", "论文题目", "审核状态", "审核意见", "刊物名称", "标准刊号", "卷", "期", "起止页", "单位排名", "通讯作者", "通讯作者arp", "通讯作者绩效","第一作者", "第一作者arp", "第一作者绩效", "第二作者", "第二作者arp", "第二作者绩效","第三作者","第三作者arp", "第三作者绩效","第四作者","第四作者arp", "第四作者绩效","第五作者","第五作者arp", "第五作者绩效","其他作者","其他作者arp", "论文状态","doi号","是否国际合作","提交人","提交时间","附件"};
		for (int i=0; i < headers.length; i++) {
			header.createCell(cellIndex++).setCellValue(headers[i]);
		}

		//2.写表内容数据
		for (int i = 0; i < nl.size(); i++) {
			Lw itemModel = nl.get(i);
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(i + 1);
			if(itemModel.getYear() != null) {
				row.createCell(0).setCellValue(itemModel.getYear());
			}else{
				row.createCell(0).setCellValue("");
			}
			if(itemModel.getPubkind() != null){
				row.createCell(1).setCellValue(itemModel.getPubkind());
			}else{
				row.createCell(1).setCellValue("");
			}
			if(itemModel.getTitle() != null) {
				row.createCell(2).setCellValue(itemModel.getTitle());
			}else{
				row.createCell(2).setCellValue("");
			}
			if(itemModel.getExaminestatus() != null) {
				row.createCell(3).setCellValue(itemModel.getExaminestatus());
			}else{
				row.createCell(3).setCellValue("");
			}
			if(itemModel.getAuditOpinion() != null) {
				row.createCell(4).setCellValue(itemModel.getAuditOpinion());
			}else{
				row.createCell(4).setCellValue("");
			}
			if(itemModel.getPubtitle() != null) {
				row.createCell(5).setCellValue(itemModel.getPubtitle());
			}else{
				row.createCell(5).setCellValue("");
			}
			if(itemModel.getPubcode() != null) {
				row.createCell(6).setCellValue(itemModel.getPubcode());
			}else{
				row.createCell(6).setCellValue("");
			}
			if(itemModel.getRoll() != null) {
				row.createCell(7).setCellValue(itemModel.getRoll());
			}else{
				row.createCell(7).setCellValue("");
			}
			if(itemModel.getExpect() != null) {
				row.createCell(8).setCellValue(itemModel.getExpect());
			}else{
				row.createCell(8).setCellValue("");
			}
			if(itemModel.getBegin2end() != null) {
				row.createCell(9).setCellValue(itemModel.getBegin2end());
			}else{
				row.createCell(9).setCellValue("");
			}
			if(itemModel.getUnit() != null) {
				row.createCell(10).setCellValue(itemModel.getUnit());
			}else{
				row.createCell(10).setCellValue("");
			}
			if(itemModel.getCommauthor() != null) {
				row.createCell(11).setCellValue(itemModel.getCommauthor());
			}else{
				row.createCell(11).setCellValue("");
			}
			if(itemModel.getCommauthorcode() != null) {
				row.createCell(12).setCellValue(itemModel.getCommauthorcode());
			}else{
				row.createCell(12).setCellValue("");
			}
			if(itemModel.getCommauthorscore() != null) {
				row.createCell(13).setCellValue(itemModel.getCommauthorscore());
			}else{
				row.createCell(13).setCellValue("");
			}
			if(itemModel.getFirstauthor() != null) {
				row.createCell(14).setCellValue(itemModel.getFirstauthor());
			}else{
				row.createCell(14).setCellValue("");
			}
			if(itemModel.getFirstauthorcode() != null) {
				row.createCell(15).setCellValue(itemModel.getFirstauthorcode());
			}else{
				row.createCell(15).setCellValue("");
			}
			if(itemModel.getFirstauthorscore() != null) {
				row.createCell(16).setCellValue(itemModel.getFirstauthorscore());
			}else{
				row.createCell(16).setCellValue("");
			}
			if(itemModel.getSecauthor() != null) {
				row.createCell(17).setCellValue(itemModel.getSecauthor());
			}else{
				row.createCell(17).setCellValue("");
			}
			if(itemModel.getSecauthorcode() != null) {
				row.createCell(18).setCellValue(itemModel.getSecauthorcode());
			}else{
				row.createCell(18).setCellValue("");
			}
			if(itemModel.getSecauthorscore() != null) {
				row.createCell(19).setCellValue(itemModel.getSecauthorscore());
			}else{
				row.createCell(19).setCellValue("");
			}
			if(itemModel.getThreeauthor() != null) {
				row.createCell(20).setCellValue(itemModel.getThreeauthor());
			}else{
				row.createCell(20).setCellValue("");
			}
			if(itemModel.getThreeauthorcode() != null) {
				row.createCell(21).setCellValue(itemModel.getThreeauthorcode());
			}else{
				row.createCell(21).setCellValue("");
			}
			if(itemModel.getThreeauthorscore() != null) {
				row.createCell(22).setCellValue(itemModel.getThreeauthorscore());
			}else{
				row.createCell(22).setCellValue("");
			}
			if(itemModel.getFourauthor() != null) {
				row.createCell(23).setCellValue(itemModel.getFourauthor());
			}else{
				row.createCell(23).setCellValue("");
			}
			if(itemModel.getFourauthorcode() != null) {
				row.createCell(24).setCellValue(itemModel.getFourauthorcode());
			}else{
				row.createCell(24).setCellValue("");
			}
			if(itemModel.getFourauthorscore() != null) {
				row.createCell(25).setCellValue(itemModel.getFourauthorscore());
			}else{
				row.createCell(25).setCellValue("");
			}
			if(itemModel.getFiveauthor() != null) {
				row.createCell(26).setCellValue(itemModel.getFiveauthor());
			}else{
				row.createCell(26).setCellValue("");
			}
			if(itemModel.getFiveauthorcode() != null) {
				row.createCell(27).setCellValue(itemModel.getFiveauthorcode());
			}else{
				row.createCell(27).setCellValue("");
			}
			if(itemModel.getFiveauthorscore() != null) {
				row.createCell(28).setCellValue(itemModel.getFiveauthorscore());
			}else{
				row.createCell(28).setCellValue("");
			}
			if(itemModel.getOtherauthor() != null) {
				row.createCell(29).setCellValue(itemModel.getOtherauthor());
			}else{
				row.createCell(29).setCellValue("");
			}
			if(itemModel.getOtherauthorcode() != null){
				row.createCell(30).setCellValue(itemModel.getOtherauthorcode());
			}else{
				row.createCell(30).setCellValue("");
			}
			if(itemModel.getStatus() != null) {
				row.createCell(31).setCellValue(itemModel.getStatus());
			}else{
				row.createCell(31).setCellValue("");
			}
			if(itemModel.getDoi() != null) {
				row.createCell(32).setCellValue(itemModel.getDoi());
			}else {
				row.createCell(32).setCellValue("");
			}
			if(itemModel.getInterpartner() != null) {
				row.createCell(33).setCellValue(itemModel.getInterpartner());
			}else{
				row.createCell(33).setCellValue("");
			}
			if(itemModel.getSubmitUser() != null) {
				row.createCell(34).setCellValue(itemModel.getSubmitUser());
			}else{
				row.createCell(34).setCellValue("");
			}
			if(itemModel.getSubmitTime() != null) {
				row.createCell(35).setCellValue(itemModel.getSubmitTime().toString());
			}else{
				row.createCell(35).setCellValue("");
			}
			if(itemModel.getAppend() != null) {
				row.createCell(36).setCellValue(itemModel.getAppend());
			}else{
				row.createCell(36).setCellValue("");
			}
		}
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
		}catch (Exception ex){
			ex.printStackTrace();
		}

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


	private void changeModel(List<TLw> l, List<Lw> nl, String usercode, List<String> usercodes) {
		if (usercode.equals("") && usercodes == null) {		//1.管理员
			if (l == null || l.size() == 0) 	
				return;
			
			for (TLw t : l) {
				Lw r = new Lw();
				BeanUtils.copyProperties(t, r);
				
				boolean hasScore = false;
				//获取所有人员分值
				if(t.getScore() != null){
					for( TLwScore score : t.getScore())
					{
						switch(score.getPosition())
						{
							case 0:
								r.setCommauthorscore(Double.toString(score.getScore()));
								break;
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
							default:
								break;
						}
						
						hasScore = true;
					}
				}
				
				if(!hasScore){
					r.setCommauthorscore("无");
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
			for (TLw t : l) {
				Lw r = new Lw();
				BeanUtils.copyProperties(t, r);
				//获取所有人员分值
				boolean hasScore = false;
				if(t.getScore() != null){
					for( TLwScore score : t.getScore())
					{
						switch(score.getPosition())
						{
							case 0:
								if(usercode.equals(score.getUsercode())){
									r.setCommauthorscore(Double.toString(score.getScore()));
								}
								break;
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
								break;
						}
						hasScore = true;
					}
				}
				if(!hasScore){
					r.setCommauthorscore("无");
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
				for (TLw t : l) {
					Lw r = new Lw();
					BeanUtils.copyProperties(t, r);
					nl.add(r);
				}
			}
		}
		
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
	
	
	public class ThesisImportEvent<T> implements ImportExcelEventI<T>{

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
