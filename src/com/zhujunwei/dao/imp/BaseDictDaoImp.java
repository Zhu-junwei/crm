package com.zhujunwei.dao.imp;

import java.util.List;
import com.zhujunwei.dao.BaseDictDao;
import com.zhujunwei.domain.BaseDict;

public class BaseDictDaoImp extends BaseDaoImp<BaseDict> implements BaseDictDao{

	@SuppressWarnings("unchecked")
	@Override
	//根据类型编码查询数据字典
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return (List<BaseDict>) getHibernateTemplate().find("from BaseDict where dict_type_code = ?0", dict_type_code);
	}

}
