package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.BzLevel;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Domain;
import com.performance.pagemodel.Dt;
import com.performance.pagemodel.Lab;
import com.performance.pagemodel.Lw;
import com.performance.pagemodel.PubPart;
import com.performance.service.BzLevelServiceI;
import com.performance.service.DomainServiceI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.PubPartServiceI;
import com.performance.service.ThesisServiceI;
import com.performances.model.TBzLevel;
import com.performances.model.TDomain;
import com.performances.model.TDt;
import com.performances.model.TLw;
import com.performances.model.TPubPart;
import com.performances.model.TUser;

@Transactional
@Service("pubPartService")
public class PubPartServiceImpl extends BaseServiceImpl implements
		PubPartServiceI {

	private BaseDaoI<TPubPart> pubPartDao;

	private ImportExcelServiceI<TPubPart> _importExcel;

	private static final  String tableName = "t_pubpart";
	private String hsql;
	private Map<String, Object> params;
	
	public ImportExcelServiceI<TPubPart> get_importExcel() {
		return _importExcel;
	}
	@Autowired
	public void set_importExcel(ImportExcelServiceI<TPubPart> _importExcel) {
		this._importExcel = _importExcel;
	}

	public BaseDaoI<TPubPart> getPubPartDao() {
		return pubPartDao;
	}

	@Autowired
	public void setPubPartDao(BaseDaoI<TPubPart> pubPartDao) {
		this.pubPartDao = pubPartDao;
	}

	@Override
	public PubPart save(PubPart pubPart) {
		TPubPart t = new TPubPart();

		BeanUtils.copyProperties(pubPart, t);

		// t.setRolename(rj.getRolename());
		// t.setRoledesc(role.getRoledesc());
		// t.setRolevalue(role.getRolevalue());
		// String[] roleRights=role.getRoledesc().split(",");
		// List<TRight> rightList=rightService.findRightsInRange(roleRights);
		// for(TRight tright:rightList){
		//
		// t.getTRights().add(tright);
		//
		// }
		// t.setTRights(trights);
		t.setSavetime(new Timestamp(new java.util.Date().getTime()));
		pubPartDao.save(t);
		BeanUtils.copyProperties(t, pubPart);
		return pubPart;
	}

	@Override
	public DataGrid datagrid(PubPart pubPart) {
		DataGrid dg = new DataGrid();
		String hql = "from TPubPart t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(pubPart, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(pubPart, hql);
		
//		/**
//		 * 保存导出时会使用到3个参数
//		 */
//		ActionContext actionContext = ActionContext.getContext();
//        Map session = actionContext.getSession();
//        if(session.containsKey("QUERY_HSQL")){
//       	 session.remove("QUERY_HSQL");
//       }
//       if(session.containsKey("QUERY_TABLE")){
//       	session.remove("QUERY_TABLE");
//       }
//       if(session.containsKey("QUERY_PARAMS")){
//       	session.remove("QUERY_PARAMS");
//       }
//        session.put("QUERY_TABLE", "t_pubpart");
//        session.put("QUERY_HSQL", hql);
//        session.put("QUERY_PARAMS", params);

		this.hsql = hql;
		this.params = params;
        
		List<TPubPart> l = pubPartDao.find(hql, params, pubPart.getPage(), pubPart.getRows());
		List<PubPart> nl = new ArrayList<PubPart>();
		changeModel(l, nl);
		dg.setTotal(pubPartDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TPubPart> l, List<PubPart> nl) {
		if (l != null && l.size() > 0) {
			for (TPubPart t : l) {
				PubPart r = new PubPart();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private String addOrder(PubPart pubPart, String hql) {
		if (pubPart.getSort() != null) {
			hql += " order by " + pubPart.getSort() + " " + pubPart.getOrder();
		}
		return hql;
	}

	private String addWhere(PubPart pubPart, String hql,
			Map<String, Object> params) {
		StringBuilder sqlStr = new StringBuilder(hql);
		sqlStr.append(" where 1=1 ");
		if (pubPart.getTitle() != null && !pubPart.getTitle().trim().equals("")&& pubPart.getIssn() == null) {
			sqlStr.append(" and t.title like :title");
			params.put("title", "%%" + pubPart.getTitle().trim() + "%%");
		}
		if(pubPart.getTitle() == null && pubPart.getIssn() != null && !(pubPart.getIssn().isEmpty())){
			sqlStr.append(" and t.issn like :issn");
			params.put("issn", "%%" + pubPart.getIssn().trim() + "%%");
		}
		if (pubPart.getTitle() != null && !pubPart.getTitle().trim().equals("") && pubPart.getIssn() != null && !(pubPart.getIssn().isEmpty())) {
			sqlStr.append(" and ( t.title like :title");
			params.put("title", "%%" + pubPart.getTitle().trim() + "%%");
			sqlStr.append(" or t.issn like :issn )");
			params.put("issn", "%%" + pubPart.getIssn().trim() + "%%");
		}
		return sqlStr.toString();
	}

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete TPubPart t where t.tpid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		// roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		pubPartDao.executeHql(hql);

	}

	@Override
	public PubPart edit(PubPart pubPart) {
			TPubPart tPubPart = pubPartDao.get(TPubPart.class, pubPart.getTpid());
			BeanUtils.copyProperties(pubPart, tPubPart, new String[] { "tpid" });
			tPubPart.setSavetime(new Timestamp(new java.util.Date().getTime()));
			pubPartDao.saveOrUpdate(tPubPart);
			return pubPart;
	}

	@Override
	public List<TPubPart> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}

	@Override
	public List<PubPart> ThesisByPoi(FileInputStream fis) {
		List<PubPart> infos = new ArrayList<PubPart>();
		
		try {
			if(fis!=null){
				_importExcel.setEventProxy(null);
				_importExcel.ImportExcelToDB(tableName, fis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return infos;
	}
	
	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("Pubpart", tableName, hsql, params);
	}
	
	@Override
	public PubPart pubPartCheck(PubPart pubPart) {
		Map<String, Object> params = new HashMap<String, Object>();
		String uc = pubPart.getIds();
		params.put("name", uc.substring(0, uc.indexOf(",")));
		params.put("name2", uc.substring(uc.indexOf(",") + 1, uc.length()));
		TPubPart t = pubPartDao.get("from  TPubPart t where t.pubkind = :name  and   t.issn = :name2 ",params);
		if (t != null) {
			BeanUtils.copyProperties(t, pubPart);
			return pubPart;
		} else {
			return null;
		}
	}
	
	/**
	 * 根据期刊类别获取期刊列表
	 */
	@Override
	public DataGrid getPubListByKind(PubPart pubPart) {
		if(pubPart.getPubkind() == null || pubPart.getPubkind().isEmpty()){
			return null;
		}
		
		DataGrid dg = new DataGrid();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from TPubPart t where t.pubkind = :pubkind ");
		params.put("pubkind", pubPart.getPubkind());
		if(pubPart.getTitle() != null && !pubPart.getTitle().isEmpty()){
			hql.append(" and t.title like :title ");
			params.put("title", "%%" + pubPart.getTitle() + "%%");
			
		}
		if(pubPart.getIssn() != null){
			hql.append(" and t.issn like :issn ");
			params.put("issn", "%%" + pubPart.getIssn() + "%%");
		}
		
		String totalHql = "select count(*) " + hql.toString();
		
		List<TPubPart> l = pubPartDao.find(hql.toString(), params, pubPart.getPage(), pubPart.getRows());
		List<PubPart> nl = new ArrayList<PubPart>();
		changeModel(l, nl);
		dg.setTotal(pubPartDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}
}
