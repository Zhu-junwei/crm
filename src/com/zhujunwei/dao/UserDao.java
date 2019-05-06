package com.zhujunwei.dao;

import com.zhujunwei.domain.User;

public interface UserDao extends BaseDao<User>{

	User login(User user);

}
