package com.performance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.Menu;
import com.performance.pagemodel.User;
import com.performance.service.MenuServiceI;
import com.performance.util.UserAware;
import com.performances.model.Tmenu;



@Transactional
@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {

	private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private BaseDaoI<Tmenu> menuDao;

	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<Menu> getTreeNode(String id) {
		List<Menu> nl = new ArrayList<Menu>();
		String hql = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (id == null || id.equals("")) {
			// 查询所有根节点
			hql = "from Tmenu t where t.tmenu is null";
		} else {
			// 异步加载当前id下的子节点
			hql = "from Tmenu t where t.tmenu.id = :id ";
			params.put("id", id);
		}
		List<Tmenu> l = menuDao.find(hql, params);
		if (l != null && l.size() > 0) {
			for (Tmenu t : l) {
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				Set<Tmenu> set = t.getTmenus();
				if (set != null && !set.isEmpty()) {
					m.setState("closed");// 节点以文件夹的形式体现
				} else {
					m.setState("open");// 节点以文件的形式体现
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public List<Menu> getAllTreeNode() {
		List<Menu> nl = new ArrayList<Menu>();
		String hql = "from Tmenu t";
		List<Tmenu> l = menuDao.find(hql);
		if (l != null && l.size() > 0) {
			for (Tmenu t : l) {
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", t.getUrl());
				m.setAttributes(attributes);
				Tmenu tm = t.getTmenu();// 获得当前节点的父节点对象
				if (tm != null) {
					m.setPid(tm.getId());
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public void saveMenu() {
		// TODO Auto-generated method stub
		Tmenu tmenu=new Tmenu();
		tmenu.setText("首页");
		tmenu.setId("0");
		tmenu.setUrl("");
		
		menuDao.saveOrUpdate(tmenu);
		

		Tmenu jxglTmenu=new Tmenu();
		jxglTmenu.setId("jxgl");
		jxglTmenu.setTmenu(tmenu);
		jxglTmenu.setText("绩效管理");
		
		menuDao.saveOrUpdate(jxglTmenu);
		
		
		Tmenu xtglTmenu=new Tmenu();
		xtglTmenu.setId("xtgl");
		xtglTmenu.setTmenu(tmenu);
		xtglTmenu.setText("系统管理");
		
		menuDao.saveOrUpdate(xtglTmenu);
		
		
		Tmenu yhglTmenu=new Tmenu();
		yhglTmenu.setId("yhgl");
		yhglTmenu.setTmenu(xtglTmenu);
		yhglTmenu.setText("用户管理");
		yhglTmenu.setUrl("/admin/yhgl.jsp");
		menuDao.saveOrUpdate(yhglTmenu);
		
		Tmenu ryglTmenu=new Tmenu();
		ryglTmenu.setId("rygl");
		ryglTmenu.setTmenu(xtglTmenu);
		ryglTmenu.setText("人员管理");
		ryglTmenu.setUrl("/admin/rygl.jsp");
		menuDao.saveOrUpdate(ryglTmenu);
		
		Tmenu jsTmenu=new Tmenu();
		jsTmenu.setId("jsgl");
		jsTmenu.setTmenu(xtglTmenu);
		jsTmenu.setText("角色管理");
		jsTmenu.setUrl("/admin/jsgl.jsp");
		menuDao.saveOrUpdate(jsTmenu);
		
		Tmenu qxTmenu=new Tmenu();
		qxTmenu.setId("qxgl");
		qxTmenu.setTmenu(xtglTmenu);
		qxTmenu.setText("权限管理");
		qxTmenu.setUrl("/admin/qxgl.jsp");
		menuDao.saveOrUpdate(qxTmenu);
		
		Tmenu ghTmenu=new Tmenu();
		ghTmenu.setId("ghgrade");
		ghTmenu.setTmenu(xtglTmenu);
		ghTmenu.setText("规划类别");
		ghTmenu.setUrl("/admin/ghgrade.jsp");
		menuDao.saveOrUpdate(ghTmenu);
		
		Tmenu lyTmenu=new Tmenu();
		lyTmenu.setId("domain");
		lyTmenu.setTmenu(xtglTmenu);
		lyTmenu.setText("领域管理");
		lyTmenu.setUrl("/admin/domain.jsp");
		
		Tmenu qkfqTmenu=new Tmenu();
		lyTmenu.setId("pubpart");
		lyTmenu.setTmenu(xtglTmenu);
		lyTmenu.setText("期刊分区管理");
		lyTmenu.setUrl("/admin/pubpart.jsp");
		try{
			
			menuDao.saveOrUpdate(lyTmenu);
			menuDao.saveOrUpdate(qkfqTmenu);
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		
		Tmenu labTmenu=new Tmenu();
		labTmenu.setId("lab");
		labTmenu.setTmenu(xtglTmenu);
		labTmenu.setText("研究室管理");
		labTmenu.setUrl("/admin/lab.jsp");
		menuDao.saveOrUpdate(labTmenu);
		
		Tmenu dtTmenu=new Tmenu();
		dtTmenu.setId("mapcate");
		dtTmenu.setTmenu(xtglTmenu);
		dtTmenu.setText("地图类别");
		dtTmenu.setUrl("/admin/mapcate.jsp");
		menuDao.saveOrUpdate(dtTmenu);
		
		Tmenu bzTmenu=new Tmenu();
		bzTmenu.setId("bzlevel");
		bzTmenu.setTmenu(xtglTmenu);
		bzTmenu.setText("标准级别管理");
		bzTmenu.setUrl("/admin/bzlevel.jsp");
		menuDao.saveOrUpdate(bzTmenu);
		
		Tmenu lwTmenu=new Tmenu();
		lwTmenu.setId("lwstutas");
		lwTmenu.setTmenu(xtglTmenu);
		lwTmenu.setText("论文状态管理");
		lwTmenu.setUrl("/admin/lwstutas.jsp");
		menuDao.saveOrUpdate(lwTmenu);
		
		Tmenu yhTmenu=new Tmenu();
		yhTmenu.setId("usercate");
		yhTmenu.setTmenu(xtglTmenu);
		yhTmenu.setText("申报级别管理");
		yhTmenu.setUrl("/admin/usercate.jsp");
		menuDao.saveOrUpdate(yhTmenu);
		
		Tmenu jbTmenu=new Tmenu();
		jbTmenu.setId("usertype");
		jbTmenu.setTmenu(xtglTmenu);
		jbTmenu.setText("用户类型管理");
		jbTmenu.setUrl("/admin/userType.jsp");
		menuDao.saveOrUpdate(jbTmenu);
		
		
		Tmenu xmTmenu=new Tmenu();
		xmTmenu.setId("xmcate");
		xmTmenu.setTmenu(xtglTmenu);
		xmTmenu.setText("项目类别管理");
		xmTmenu.setUrl("/admin/xmcate.jsp");
		menuDao.saveOrUpdate(yhTmenu);
		
		Tmenu zlTmenu=new Tmenu();
		zlTmenu.setId("zllevel");
		zlTmenu.setTmenu(xtglTmenu);
		zlTmenu.setText("专利级别管理");
		zlTmenu.setUrl("/admin/zllevel.jsp");
		menuDao.saveOrUpdate(zlTmenu);
		
		Tmenu bgTmenu=new Tmenu();
		bgTmenu.setId("zxbglevel");
		bgTmenu.setTmenu(xtglTmenu);
		bgTmenu.setText("报告级别管理");
		bgTmenu.setUrl("/admin/zxbglevel.jsp");
		menuDao.saveOrUpdate(bgTmenu);
	
		Tmenu zzTmenu=new Tmenu();
		zzTmenu.setId("zzcate");
		zzTmenu.setTmenu(xtglTmenu);
		zzTmenu.setText("著作类别管理");
		zzTmenu.setUrl("/admin/zzcate.jsp");
		menuDao.saveOrUpdate(zzTmenu);
				
	}

	

}
