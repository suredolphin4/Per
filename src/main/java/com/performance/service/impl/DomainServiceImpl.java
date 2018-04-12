package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

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
import com.performance.pagemodel.Lab;
import com.performance.pagemodel.Lw;
import com.performance.service.BzLevelServiceI;
import com.performance.service.DomainServiceI;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.ThesisServiceI;
import com.performances.model.TBzLevel;
import com.performances.model.TDomain;
import com.performances.model.TLw;
import com.performances.model.TPubPart;
@Transactional
@Service("domainService")
public class DomainServiceImpl extends BaseServiceImpl implements DomainServiceI {

	@Autowired
	private ImportExcelServiceI<TDomain> _importExcel;
	
	private BaseDaoI<TDomain> domainDao;

	private static final  String tableName = "t_domain";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TDomain> getDomainDao() {
		return domainDao;
	}
	@Autowired
	public void setDomainDao(BaseDaoI<TDomain> domainDao) {
		this.domainDao = domainDao;
	}

	@Override
	public Domain save(Domain domain) {
		TDomain t = new TDomain();
		
		BeanUtils.copyProperties(domain, t);
		
//		t.setRolename(rj.getRolename());
//		t.setRoledesc(role.getRoledesc());
//		t.setRolevalue(role.getRolevalue());
//		String[] roleRights=role.getRoledesc().split(",");
//		List<TRight> rightList=rightService.findRightsInRange(roleRights);
//		for(TRight tright:rightList){
//			
//			t.getTRights().add(tright);
//			
//		}
//		t.setTRights(trights);
		domainDao.save(t);
		BeanUtils.copyProperties(t, domain);
		return domain;
	}

	@Override
	public DataGrid datagrid(Domain domain) {
		DataGrid dg = new DataGrid();
		String hql = "from TDomain t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(domain, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(domain, hql);
		
		/**
		 * 保存导出时会使用到3个参数
		 */
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
//        session.put("QUERY_TABLE", "t_domain");
//        session.put("QUERY_HSQL", hql);
//        session.put("QUERY_PARAMS", params);

		this.hsql = hql;
		this.params = params;
        
		List<TDomain> l = domainDao.find(hql, params, domain.getPage(), domain.getRows());
		List<Domain> nl = new ArrayList<Domain>();
		changeModel(l, nl);
		dg.setTotal(domainDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TDomain> l, List<Domain> nl) {
		if (l != null && l.size() > 0) {
			for (TDomain t : l) {
				Domain r= new Domain();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}
	private String addOrder(Domain domain, String hql) {
		if (domain.getSort() != null) {
			hql += " order by " + domain.getSort() + " " + domain.getOrder();
		}
		return hql;
	}
	private String addWhere(Domain domain, String hql, Map<String, Object> params) {
		if (domain.getName() != null && !domain.getName().trim().equals("")) {
			hql += " where  t.name like :title";
			params.put("title", "%%" + domain.getName().trim() + "%%");
		}
		return hql;
	}
	
	@Override
	public void remove(String ids) {
	String[] nids = ids.split(",");
		String hql = "delete TDomain t where t.domainid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		//roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		domainDao.executeHql(hql);

	}

	@Override
	public Domain edit(Domain domain) {
		TDomain tDomain = domainDao.get(TDomain.class, domain.getDomainid());
		BeanUtils.copyProperties(domain, tDomain, new String[] { "domainid" });
		domainDao.saveOrUpdate(tDomain);
		return domain;
	}

	

	@Override
	public List<TDomain> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}
	@Override
	public List<Domain> ThesisByPoi(FileInputStream fis) {
		List<Domain> infos = new ArrayList<Domain>();
		Domain domain = null;

		try {
			_importExcel.ImportExcelToDB(tableName, fis);
		} catch (XPathExpressionException | InstantiationException
				| IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return infos;
	}
	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("Domain");
	}

}
