package com.zhujunwei.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 通用的dao
 * @author zhujunwei
 * 2019年5月6日 下午4:52:37
 * @param <T>
 */
public interface BaseDao<T> {
	void save(T t);
	void update(T t);
	void delete(T t);
	
	T findById(Serializable id);
	//查询所有
	List<T> findAll();
	//统计个数的方法
	Integer findCount(DetachedCriteria detachedCriteria);
	//分页查询的方法
	List<T> findByPage(DetachedCriteria detachedCriteria,Integer begin,Integer pageSize);
}
