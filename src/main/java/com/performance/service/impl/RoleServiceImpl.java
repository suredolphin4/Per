package com.performance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Right;
import com.performance.pagemodel.Role;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.RightServiceI;
import com.performance.service.RoleServiceI;
import com.performance.util.ValidateUtil;
import com.performances.model.TDomain;
import com.performances.model.TRight;
import com.performances.model.TRole;
@Transactional
@Service("roleService")
public class RoleServiceImpl   implements RoleServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);

	private BaseDaoI<TRole> roleDao;
	private RightServiceI rightService;
	public RightServiceI getRightService() {
		return rightService;
	}
	@Autowired
	private ImportExcelServiceI<TDomain> _importExcel;
	
	@Autowired
	public void setRightService(RightServiceI rightService) {
		this.rightService = rightService;
	}
//	private Set<TRight> trights =new HashSet<TRight>();
	public BaseDaoI<TRole> getRoleDao() {
		return roleDao;
	}
	@Autowired
	public void setRoleDao(BaseDaoI<TRole> roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role save(Role role) {
		TRole t = new TRole();
		BeanUtils.copyProperties(role, t);
		t.setRolename(role.getRolename());
		t.setRoledesc(role.getRoledesc());
		t.setRolevalue(role.getRolevalue());
		String[] roleRights=role.getRoledesc().split(",");
		List<TRight> rightList=rightService.findRightsInRange(roleRights);
		for(TRight tright:rightList){
			
			t.getTRights().add(tright);
			
		}
//		t.setTRights(trights);
		roleDao.save(t);
		BeanUtils.copyProperties(t, role);
		return role;
	}

	@Override
	public DataGrid datagrid(Role role) {
		DataGrid dg = new DataGrid();
		String hql = "from TRole t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(role, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(role, hql);
		/**
		 * 保存导出时会使用到3个参数
		 */
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        session.put("QUERY_TABLE", "t_role");
        session.put("QUERY_HSQL", hql);
        session.put("QUERY_PARAMS", params);
        
		List<TRole> l = roleDao.find(hql, params, role.getPage(), role.getRows());
		List<Role> nl = new ArrayList<Role>();
		changeModel(l, nl);
		dg.setTotal(roleDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}
	
	private void changeModel(List<TRole> l, List<Role> nl) {
		if (l != null && l.size() > 0) {
			for (TRole t : l) {
				Role r= new Role();

				Set<Right> rightes=new HashSet<Right>();
				Set<TRight> tr=t.getTRights();
				Iterator<TRight> itright=tr.iterator();
				while (itright.hasNext()) {
					TRight tRight = itright.next();
					Right right=new Right();
					BeanUtils.copyProperties(tRight, right);
					rightes.add(right);
					
				}
			//	BeanUtils.copyProperties(tr,rightes);
				
				BeanUtils.copyProperties(t, r);
				r.setRights(rightes);
				nl.add(r);
			}
		}
	}

	
	private String addOrder(Role role, String hql) {
		if (role.getSort() != null) {
			hql += " order by " + role.getSort() + " " + role.getOrder();
		}
		return hql;
	}
	
	private String addWhere(Role role, String hql, Map<String, Object> params) {
		if (role.getRolename() != null && !role.getRolename().trim().equals("")) {
			hql += " where t.rolename like :rolename";
			params.put("rolename", "%%" + role.getRolename().trim() + "%%");
		}
		return hql;
	}
	@Override
	public void remove(String ids) {
		
		String[] nids = ids.split(",");
		
		String hql = "delete TRole t where t.roleid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		//roleDao.executeHql("SET FOREIGN_KEY_CHECKS = 0");
		roleDao.executeHql(hql);

	}
	@Override
	public Role edit(Role role) {
		
		TRole t=roleDao.get(TRole.class,role.getRoleid());
		BeanUtils.copyProperties(role,t, new String[] {"roleid"});
		roleDao.saveOrUpdate(t);
		return  role;
	}
	@Override
	public List<TRole> findRoleInRange(String[] ids) {
			// TODO Auto-generated method stub
			if(ValidateUtil.isValid(ids)){
				String hql = "from TRole r where r.id in ("+com.performance.util.StringUtil.arr2String(ids)+")" ;
				return this.findEntityByHQL(hql);
			}
			return null ;
	}
	@Override
	public List<TRole> findEntityByHQL(String hql, Object... objects) {
//		List<TRole> list=roleDao.findEntityByHQL(hql, objects);
		return roleDao.findEntityByHQL(hql, objects);
	}
	@Override
	public String exportExcel() {
		// TODO Auto-generated method stub
		return _importExcel.exportExcel("Role");
	}

}
