package com.zhujunwei.dao.imp;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zhujunwei.dao.UserDao;
import com.zhujunwei.domain.User;

@Transactional
public class UserDaoImp extends BaseDaoImp<User> implements UserDao{

	@SuppressWarnings("unchecked")
	@Override
	//用户登录
	public User login(User user) {
		List<User> list = (List<User>) getHibernateTemplate().find("from User where user_code= ?0 and user_password = ?1", user.getUser_code(),user.getUser_password());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
