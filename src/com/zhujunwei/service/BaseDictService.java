package com.zhujunwei.service;

import java.util.List;

import com.zhujunwei.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
