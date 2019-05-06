package com.zhujunwei.service.imp;

import com.zhujunwei.dao.UserDao;
import com.zhujunwei.domain.User;
import com.zhujunwei.service.UserService;
import com.zhujunwei.utils.MD5Utils;

public class UserServiceImp implements UserService {

	
	//注入Dao
	private UserDao userDao ;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	//业务层注册用户的方法
	public void regist(User user) {
		//对密码进行加密处理
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		//调用DAO
		userDao.save(user);
	}


	@Override
	public User login(User user) {
		//对密码进行加密处理
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

}
