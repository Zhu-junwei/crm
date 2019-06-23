package com.zhujunwei.service;

import org.hibernate.criterion.DetachedCriteria;

import com.zhujunwei.domain.PageBean;
import com.zhujunwei.domain.SaleVisit;

public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

}
