package com.performance.service.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Var;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.performance.dao.BaseDaoI;
import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.Right;
import com.performance.service.ImportExcelServiceI;
import com.performance.service.RightServiceI;
import com.performance.util.ValidateUtil;
import com.performances.model.TRight;
import com.performances.model.TUserCate;

@Transactional
@Service("rightService")
public class RightServiceImpl implements RightServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(RightServiceImpl.class);
	@Autowired
	private ImportExcelServiceI<TUserCate> _importExcel;
	
	private BaseDaoI<TRight> rightDao;

	public BaseDaoI<TRight> getRightDao() {
		return rightDao;
	}

	@Autowired
	public void setRightDao(BaseDaoI<TRight> rightDao) {
		this.rightDao = rightDao;
	}

	@Override
	public DataGrid datagrid(Right right) {
		DataGrid dg = new DataGrid();
		String hql = "from TRight t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(right, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(right, hql);
		/**
		 * 保存导出时会使用到3个参数
		 */
		ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();
        session.put("QUERY_TABLE", "t_right");
        session.put("QUERY_HSQL", hql);
        session.put("QUERY_PARAMS", params);
        
		List<TRight> l = rightDao.find(hql, params, right.getPage(),
				right.getRows());
		List<Right> nl = new ArrayList<Right>();
		changeModel(l, nl);
		dg.setTotal(rightDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<TRight> l, List<Right> nl) {
		if (l != null && l.size() > 0) {
			for (TRight t : l) {
				Right r = new Right();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}

	private String addOrder(Right right, String hql) {
		if (right.getSort() != null) {
			hql += " order by " + right.getSort() + " " + right.getOrder();
		}
		return hql;
	}

	private String addWhere(Right right, String hql, Map<String, Object> params) {
		if (right.getRightname() != null
				&& !right.getRightname().trim().equals("")) {
			hql += " where t.rightname like :rightname";
			params.put("rightname", "%%" + right.getRightname().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete TRight t where t.rightid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		rightDao.executeHql(hql);

	}

	@Override
	public Right save(Right right) {
		TRight t = new TRight();
		BeanUtils.copyProperties(right, t);
		t.setRightname(right.getRightname());
		t.setRightdesc(right.getRightdesc());
		t.setRighturl(right.getRighturl());
		t.setRightcode(right.getRightcode());
		t.setRightpos(right.getRightpos());
		rightDao.save(t);
		BeanUtils.copyProperties(t, right);
		return right;
	}

	@Override
	public Right saveOrUpdateRight(Right r) {
		// 插入操作
		int pos = 0;
		long code = 1L;
		String hql = "from TRight r order by r.rightpos desc,r.rightcode desc";
		List<TRight> rights = this.findEntityByHQL(hql);
		if (!ValidateUtil.isValid(rights)) {
			pos = 0;
			code = 1L;
		} else {
			TRight top = rights.get(0);

			int topPos = top.getRightpos();
			long topCode = top.getRightcode();
			if (topCode >= (1L << 60)) {
				pos = topPos + 1;
				code = 1;
			} else {
				pos = topPos;
				code = topCode << 1;
			}

			r.setRightcode(code);
			r.setRightpos(pos);
		}
		// this.saveOrUpdateEntity(r);
		return this.save(r);

	}

	public List<TRight> findEntityByHQL(String hql, Object... objects) {
		return rightDao.findEntityByHQL(hql, objects);
	}

	private void saveOrUpdateEntity(Right right) {
		// private Right saveOrUpdateEntity(Right right) {
		// TRight t = new TRight();
		// BeanUtils.copyProperties(right, t);
		// t.setRightname(right.getRightname());
		// t.setRightdesc(right.getRightdesc());
		// t.setRighturl(right.getRighturl());
		// // rightDao.save(t);
		// rightDao.saveOrUpdate(t);
		// BeanUtils.copyProperties(t, right);
		// return right;
		//
		//
		int pos = 0;
		long code = 1L;
		String hql = "from TRight r order by r.rightpos desc,r.rightcode desc";
		List<TRight> rights = this.findEntityByHQL(hql);
		if (!ValidateUtil.isValid(rights)) {
			pos = 0;
			code = 1L;
		} else {
			TRight top = rights.get(0);
			int topPos = top.getRightpos();
			long topCode = top.getRightcode();
			if (topCode >= (1L << 60)) {
				pos = topPos + 1;
				code = 1;
			} else {
				pos = topPos;
				code = topCode << 1;
			}

			right.setRightcode(topCode);
			right.setRightpos(topPos);
		}
		this.saveOrUpdateEntity(right);

	}

	@Override
	public Object uniqueResult(String hql, Object... objects) {
		// TODO Auto-generated method stub

		return rightDao.uniqueResult(hql, objects);
	}

	/**
	 * 按照url追加权限
	 */
	public void appendRightByURL(String url) {
		String hql = "select count(*) from TRight r where r.righturl = ?";
		List list = (List) this.uniqueResult(hql, url);
		Long count = (Long) list.get(0);

		if (count == 0) {
			Right r = new Right();
			r.setRighturl(url);
			this.saveOrUpdateRight(r);
		}
	}

	@Override
	public Right edit(Right right) {
		// TODO Auto-generated method stub
		TRight t = rightDao.get(TRight.class, right.getRightid());
		BeanUtils.copyProperties(right, t, new String[] { "rightid" });
		return right;
	}

	@Override
	public List<TRight> findRightsInRange(String[] ids) {
		// TODO Auto-generated method stub
		if (ValidateUtil.isValid(ids)) {
			String hql = "from TRight r where r.id in ("
					+ com.performance.util.StringUtil.arr2String(ids) + ")";
			return this.findEntityByHQL(hql);
		}
		return null;
	}

	@Override
	public int getMaxRightPos() {
		String hql = "select max(r.rightpos) from TRight r";

		List list = (List) this.uniqueResult(hql);

		Integer pos = (Integer) list.get(0);

		return pos == null ? 0 : pos;
	}

	public List<Right> findAllEntities() {
		// TODO Auto-generated method stub
		List<Right> rights = new ArrayList<Right>();
		String hql = "from TRight";
		List<TRight> tRightes = rightDao.find(hql);
		for (int i = 0; i < tRightes.size(); i++) {
			Right right = new Right();
			BeanUtils.copyProperties(tRightes.get(i), right);
			rights.add(right);
		}
		return rights;
	}

	@Override
	public String exportExcel() {
		return _importExcel.exportExcel("right");
	}

}
