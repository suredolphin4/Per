package com.performance.service.impl;

import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.ProjectStatistic;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.ProjectStatisticServiceI;
import com.performances.model.TProjectStatistic;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxbing on 15-11-11.
 * 项目统计服务类
 */
@Transactional
@Service
public class ProjectStatisticService extends BaseServiceImpl implements ProjectStatisticServiceI {

	@Autowired
	private BaseDaoI<TProjectStatistic> proStaDao;

	//Excel导入服务
	@Autowired
	private ImportExcelServiceI<TProjectStatistic> _importExcel;

	private static final String tableName = "project_statistic";
	private String hsql;
	private Map<String, Object> params;

	@Override
	public DataGrid datagrid(ProjectStatistic proSta) {
		DataGrid dg = new DataGrid();

		String hql = "from TProjectStatistic t ";
		Map<String, Object> params = new HashMap<>();
		hql = addWhere(proSta, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(proSta, hql);
		try {
			this.hsql = hql;
			this.params = params;
			List<TProjectStatistic> tps = proStaDao.find(hql, params, proSta.getPage(), proSta.getRows());
			List<ProjectStatistic> ps = new ArrayList<>();
			changeModel(tps, ps);
			dg.setTotal(proStaDao.count(totalHql, params));
			dg.setRows(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}

	@Override
	public DataGrid datagrid(ProjectStatistic proSta, String usercode) {
		DataGrid dg = new DataGrid();

		String hql = "from TProjectStatistic t ";
		Map<String, Object> params = new HashMap<>();
		hql = addWhere(proSta, hql, params);
		hql += " and usercode = :usercode";
		params.put("usercode", usercode);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(proSta, hql);
		try {
			this.hsql = hql;
			this.params = params;

			List<TProjectStatistic> tps = proStaDao.find(hql, params, proSta.getPage(), proSta.getRows());
			List<ProjectStatistic> ps = new ArrayList<>();
			changeModel(tps, ps);
			dg.setTotal(proStaDao.count(totalHql, params));
			dg.setRows(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}

	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("ProjectStatistic", tableName, hsql, params);
	}

	private String addWhere(ProjectStatistic proSta, String hsql, Map<String, Object> params){
		StringBuilder sqlStr = new StringBuilder(hsql);
		sqlStr.append("where 1=1 ");
		String s_username = proSta.getS_username();
		if(s_username != null && !s_username.isEmpty()){
			sqlStr.append(" and username like :username");
			params.put("username", "%%" + s_username + "%%");
		}

		String s_domain = proSta.getS_domain();
		if(s_domain != null && !s_domain.isEmpty()){
			sqlStr.append(" and domain like :domain");
			params.put("domain", "%%" + s_domain + "%%");
		}

		//4. 用户编号(高级检索)
		String search_usercode = proSta.getSearch_usercode();
		if (search_usercode != null && !search_usercode.isEmpty()) {
			sqlStr.append(" and (");
			sqlStr.append(" t.usercode = :search_usercode ");
			sqlStr.append(")");
			params.put("search_usercode",  search_usercode);
		}

		return sqlStr.toString();
	}

	/**
	 * 执行“项目统计”存储过程
	 */
	@Override
	public boolean  calculateProject(){
		String hql = "delete from TProjectStatistic";
		proStaDao.executeHql(hql);

		String calProjectSQL = "CALL per.project_statistic_all()";
		int result = proStaDao.executeSqlQuery(calProjectSQL);
		if(result != -1){
			return true;
		}else{
			return false;
		}
	}

	private String addOrder(ProjectStatistic proSta, String hsql){

		return hsql;
	}

	private void changeModel(List<TProjectStatistic> l, List<ProjectStatistic> nl) {
		if (l != null && l.size() > 0) {
			for (TProjectStatistic t : l) {
				ProjectStatistic u = new ProjectStatistic();

				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}
}
