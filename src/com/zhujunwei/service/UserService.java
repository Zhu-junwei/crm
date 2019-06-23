package com.zhujunwei.service;

import java.util.List;

import com.zhujunwei.domain.User;

public interface UserService {

	void regist(User user);

	User login(User user);

	List<User> findAll();

}
