package com.zhujunwei.service;

import com.zhujunwei.domain.User;

public interface UserService {

	void regist(User user);

	User login(User user);

}
