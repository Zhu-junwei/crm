package com.zhujunwei.dao;

import java.util.List;

import com.zhujunwei.domain.BaseDict;

/**
 * 字典Dao的接口
 * @author zhujunwei
 * 2019年4月29日 下午12:21:27
 */
public interface BaseDictDao extends BaseDao<BaseDict>{

	List<BaseDict> findByTypeCode(String dict_type_code);

}
