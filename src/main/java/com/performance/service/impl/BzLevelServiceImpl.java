package com.performance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.BzLevel;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Lw;
import com.performance.service.BzLevelServiceI;
import com.performance.service.ThesisServiceI;
import com.performances.model.TBzLevel;
import com.performances.model.TLw;
@Transactional
@Service("bzLevelService")
public class BzLevelServiceImpl implements BzLevelServiceI {

	
	private BaseDaoI<TBzLevel> bzLevelDao;

	

	public BaseDaoI<TBzLevel> getBzLevelDao() {
		return bzLevelDao;
	}
	@Autowired
	public void setBzLevelDao(BaseDaoI<TBzLevel> bzLevelDao) {
		this.bzLevelDao = bzLevelDao;
	}

	@Override
	public BzLevel save(BzLevel bzlevel) {
		TBzLevel t = new TBzLevel();
		
		BeanUtils.copyProperties(bzlevel, t);
		
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
		bzLevelDao.save(t);
		BeanUtils.copyProperties(t, bzlevel);
		return bzlevel;
	}

	@Override
	public DataGrid datagrid(BzLevel bzlevel) {
		DataGrid dg = new DataGrid();
		String hql = "from TBzLevel t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(bzlevel, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(bzlevel, hql);
		List<TBzLevel> l = bzLevelDao.find(hql, params, bzlevel.getPage(), bzlevel.getRows());
		List<BzLevel> nl = new ArrayList<BzLevel>();
		changeModel(l, nl);
		dg.setTotal(bzLevelDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TBzLevel> l, List<BzLevel> nl) {
		if (l != null && l.size() > 0) {
			for (TBzLevel t : l) {
				BzLevel r= new BzLevel();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}
	private String addOrder(BzLevel bzlevel, String hql) {
		if (bzlevel.getSort() != null) {
			hql += " order by " + bzlevel.getSort() + " " + bzlevel.getOrder();
		}
		return hql;
	}
	private String addWhere(BzLevel bzlevel, String hql, Map<String, Object> params) {
		if (bzlevel.getName() != null && !bzlevel.getName().trim().equals("")) {
			hql += " where  t.name like :title";
			params.put("title", "%%" + bzlevel.getName().trim() + "%%");
		}
		return hql;
	}
	
	@Override
	public void remove(String ids) {
	String[] nids = ids.split(",");
		String hql = "delete TBzLevel t where t.bzlevelid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		//roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		bzLevelDao.executeHql(hql);

	}

	@Override
	public BzLevel edit(BzLevel bzlevel) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<TBzLevel> findEntityByHQL(String hql, Object[] objects) {
		// TODO Auto-generated method stub
		return null;
	}

}
