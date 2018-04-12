package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.performance.pagemodel.LwStutas;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.LwStutasServiceI;
import com.performances.model.TBzLevel;
import com.performances.model.TDomain;
import com.performances.model.TLwStutas;

@Transactional
@Service("lwStutasService")
public class LwStutasServiceImpl extends BaseServiceImpl implements
		LwStutasServiceI {

	private BaseDaoI<TLwStutas> lwStutasDao;

	private static final  String tableName = "t_lwstutas";
	private String hsql;
	private Map<String, Object> params;

	public BaseDaoI<TLwStutas> getLwStutasDao() {
		return lwStutasDao;
	}

	@Autowired
	public void setLwStutasDao(BaseDaoI<TLwStutas> lwStutasDao) {
		this.lwStutasDao = lwStutasDao;
	}

	@Autowired
	private ImportExcelServiceI<TDomain> _importExcel;
	@Override
	public LwStutas save(LwStutas lwStutas) {
		TLwStutas t = new TLwStutas();

		BeanUtils.copyProperties(lwStutas, t);

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
		lwStutasDao.save(t);
		BeanUtils.copyProperties(t, lwStutas);
		return lwStutas;
	}

	@Override
	public DataGrid datagrid(LwStutas lwStutas) {
		DataGrid dg = new DataGrid();
		String hql = "from TLwStutas t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(lwStutas, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lwStutas, hql);

//		/**
//		 * 保存导出时会使用到3个参数  @author zheng 论文状态不需要导出，这几句代码影响论文导出。
//		 */
//		ActionContext actionContext = ActionContext.getContext();
//		Map session = actionContext.getSession();
//		if (session.containsKey("QUERY_HSQL")) {
//			session.remove("QUERY_HSQL");
//		}
//		if (session.containsKey("QUERY_TABLE")) {
//			session.remove("QUERY_TABLE");
//		}
//		if (session.containsKey("QUERY_PARAMS")) {
//			session.remove("QUERY_PARAMS");
//		}
//		session.put("QUERY_TABLE", "t_lwstutas");
//		session.put("QUERY_HSQL", hql);
//		session.put("QUERY_PARAMS", params);

		this.hsql = hql;
		this.params = params;

		List<TLwStutas> l = lwStutasDao.find(hql, params, lwStutas.getPage(),
				lwStutas.getRows());
		List<LwStutas> nl = new ArrayList<LwStutas>();
		changeModel(l, nl);
		dg.setTotal(lwStutasDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public DataGrid getLwStatusList(){
		DataGrid dg = new DataGrid();
		String hql = "from TLwStutas t ";
		String totalHql = "select count(*) " + hql;

		List<TLwStutas> l = lwStutasDao.find(hql);
		List<LwStutas> nl = new ArrayList<LwStutas>();
		changeModel(l, nl);
		dg.setTotal(lwStutasDao.count(totalHql));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TLwStutas> l, List<LwStutas> nl) {
		if (l != null && l.size() > 0) {
			for (TLwStutas t : l) {
				LwStutas r = new LwStutas();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private String addOrder(LwStutas lwStutas, String hql) {
		if (lwStutas.getSort() != null) {
			hql += " order by " + lwStutas.getSort() + " "
					+ lwStutas.getOrder();
		}
		return hql;
	}

	private String addWhere(LwStutas lwStutas, String hql,
			Map<String, Object> params) {
		if (lwStutas.getName() != null && !lwStutas.getName().trim().equals("")) {
			hql += " where  t.name like :title";
			params.put("title", "%%" + lwStutas.getName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete TLwStutas t where t.lwstutasid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		// roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		lwStutasDao.executeHql(hql);

	}

	@Override
	public LwStutas edit(LwStutas lwStutas) {
		TLwStutas tLwStutas = lwStutasDao.get(TLwStutas.class, lwStutas.getLwstutasid());
		BeanUtils.copyProperties(lwStutas, tLwStutas, new String[] { "lwstutasid" });
		lwStutasDao.saveOrUpdate(tLwStutas);
		return lwStutas;
	}

	@Override
	public List<TLwStutas> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}

	@Override
	public List<LwStutas> ThesisByPoi(FileInputStream fis) {
		List<LwStutas> infos = new ArrayList<LwStutas>();
		LwStutas lwStutas = null;

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
		return _importExcel.exportExcel("thesisStutas", tableName, hsql, params);
	}
}
