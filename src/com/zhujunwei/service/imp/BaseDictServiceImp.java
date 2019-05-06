package com.zhujunwei.service.imp;

import java.util.List;

import com.zhujunwei.dao.BaseDictDao;
import com.zhujunwei.domain.BaseDict;
import com.zhujunwei.service.BaseDictService;

public class BaseDictServiceImp implements BaseDictService {
	
	//注入Dao
	private BaseDictDao baseDictDao ;
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}
	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}
	
	
}
