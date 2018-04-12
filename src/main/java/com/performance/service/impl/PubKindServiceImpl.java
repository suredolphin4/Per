package com.performance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.performance.pagemodel.DataGrid;
import com.performance.pagemodel.PubKind;
import com.performance.pagemodel.PubPart;
import com.performance.service.PubKindServiceI;
import com.performance.dao.BaseDaoI;
import com.performances.model.TPubKind;
import com.performances.model.TPubPart;

/**
 * 刊物类别服务
 * 
 * @author zheng
 * 
 */
@Transactional
@Service("pubKindService")
public class PubKindServiceImpl implements PubKindServiceI {

	private BaseDaoI<TPubKind> pubKindDao;

	public BaseDaoI<TPubKind> getPubKindDao() {
		return pubKindDao;
	}
	
	@Autowired
	public void setPubKindDao(BaseDaoI<TPubKind> pubKindDao) {
		this.pubKindDao = pubKindDao;
	}

	@Override
	public DataGrid datagrid(PubKind pubKind) {
		DataGrid dg = new DataGrid();
		String hql = "from TPubKind t ";
		List<TPubKind> l = pubKindDao.find(hql);
		List<PubKind> nl = new ArrayList<PubKind>();
		changeModel(l, nl);
		dg.setTotal(0L);
		dg.setRows(nl);
		return dg;
	}
	
	private void changeModel(List<TPubKind> l, List<PubKind> nl) {
		if (l != null && l.size() > 0) {
			for (TPubKind t : l) {
				PubKind r = new PubKind();
				BeanUtils.copyProperties(t, r);
				nl.add(r);
			}
		}
	}
}
