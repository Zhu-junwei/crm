package com.zhujunwei.service;

import org.hibernate.criterion.DetachedCriteria;

import com.zhujunwei.domain.LinkMan;
import com.zhujunwei.domain.PageBean;

public interface LinkManService {

	PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
