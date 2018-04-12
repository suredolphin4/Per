package com.performance.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Lab;
import com.performance.pagemodel.PubPart;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.LabServiceI;
import com.performances.model.TDomain;
import com.performances.model.TLab;
import com.performances.model.TPubPart;
@Transactional
@Service("labService")
public class LabServiceImpl extends BaseServiceImpl implements LabServiceI {
	private BaseDaoI<TLab> labDao;
	private ImportExcelServiceI<TPubPart> _importExcel;

	private static final  String tableName = "t_lab";
	private String hsql;
	private Map<String, Object> params;
	
	public ImportExcelServiceI<TPubPart> get_importExcel() {
		return _importExcel;
	}
	@Autowired
	public void set_importExcel(ImportExcelServiceI<TPubPart> _importExcel) {
		this._importExcel = _importExcel;
	}

	public BaseDaoI<TLab> getLabDao() {
		return labDao;
	}
	@Autowired
	public void setLabDao(BaseDaoI<TLab> labDao) {
		this.labDao = labDao;
	}

	@Override
	public Lab save(Lab lab) {
		TLab t = new TLab();
		TDomain tdomain=new TDomain();
		tdomain.setDomainid(lab.getDomaincode());
		tdomain.setName(lab.getDomainname());
		
		BeanUtils.copyProperties(lab, t);
		
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
		labDao.save(t);
		BeanUtils.copyProperties(t, lab);
		return lab;
	}

	@Override
	public DataGrid datagrid(Lab lab) {
		DataGrid dg = new DataGrid();
		String hql = "from TLab t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(lab, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(lab, hql);
		
		/**
		 * 保存导出时会使用到3个参数
		 */
//		ActionContext actionContext = ActionContext.getContext();
//        Map<String, Object> session = actionContext.getSession();
//        if(session.containsKey("QUERY_HSQL")){
//       	 session.remove("QUERY_HSQL");
//       }
//       if(session.containsKey("QUERY_TABLE")){
//       	session.remove("QUERY_TABLE");
//       }
//       if(session.containsKey("QUERY_PARAMS")){
//       	session.remove("QUERY_PARAMS");
//       }
//        session.put("QUERY_TABLE", "t_lab");
//        session.put("QUERY_HSQL", hql);
//        session.put("QUERY_PARAMS", params);

		this.hsql = hql;
		this.params = params;
        
		List<TLab> l = labDao.find(hql, params, lab.getPage(), lab.getRows());
		List<Lab> nl = new ArrayList<Lab>();
		changeModel(l, nl);
		dg.setTotal(labDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TLab> l, List<Lab> nl) {
		if (l != null && l.size() > 0) {
			for (TLab t : l) {
				Lab r= new Lab();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}
	private String addOrder(Lab lab, String hql) {
		if (lab.getSort() != null) {
			hql += " order by " + lab.getSort() + " " + lab.getOrder();
		}
		return hql;
	}
	private String addWhere(Lab lab, String hql, Map<String, Object> params) {
		
		if (lab.getName() != null && !lab.getName().trim().equals("")) {
			hql += " where  t.name like :title  ";
			params.put("title", "%%" + lab.getName().trim() + "%%");
			
		}else if(lab.getDomaincode()!=null){
			hql += " where  t.domaincode= :domaincode ";
			params.put("domaincode",lab.getDomaincode());
			
		}
		return hql;
	}
	
	@Override
	public void remove(String ids) {
	String[] nids = ids.split(",");
		String hql = "delete TLab t where t.labid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		//roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		labDao.executeHql(hql);

	}

	@Override
	public Lab edit(Lab lab) {
		TLab tLab = labDao.get(TLab.class, lab.getLabid());
		BeanUtils.copyProperties(lab, tLab, new String[] { "labid" });
		labDao.saveOrUpdate(tLab);
		return lab;
	}

	@Override
	public List<TLab> findEntityByHQL(String hql, Object[] objects) {
		return null;
	}
	@Override
	public List<Lab> ThesisByPoi(FileInputStream fis) {
		List<Lab> infos = new ArrayList<Lab>();
		try {
			if(fis!=null){
				_importExcel.setEventProxy(null);
				_importExcel.ImportExcelToDB("t_lab", fis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return infos;
	}
	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("Lab", tableName, hsql, params);
	}

}
