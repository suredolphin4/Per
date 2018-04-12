package com.performance.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.performances.model.TRight;

public interface BaseDaoI<T> {

	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void saveOrUpdate(T o);

	public T get(Class<T> c, Serializable id);

	public T get(String hql);

	public T get(String hql, Map<String, Object> params);

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, Object> params);

	public List<T> find(String hql, int page, int rows);

	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);

	public int executeHql(String hql);

	public int executeSqlQuery(String sql);
	
	public Object uniqueResult(String hql,Object...objects);

	public List<T> findEntityByHQL(String hql, Object[] objects);

	public void merge(T o);



}
