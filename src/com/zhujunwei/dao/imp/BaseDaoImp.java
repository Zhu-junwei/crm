package com.zhujunwei.dao.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.zhujunwei.dao.BaseDao;

@Transactional
public class BaseDaoImp<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<?> clazz;

	//提供构造方法：在构造方法中传入具体类型的class
	public BaseDaoImp() {
		//反射：获取得到Class
		Class clazz = this.getClass() ;
		//获取参数化类型：BaseDaoImp<Customer>、BaseDaoImp<LinkMan>
		Type type = clazz.getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) type;
		//获取实际类型参数
		Type[] types = pType.getActualTypeArguments();
		this.clazz = (Class<?>) types[0];
	}
	
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		//设置统计个数的条件
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0) {
			return list.get(0).intValue();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}

}
