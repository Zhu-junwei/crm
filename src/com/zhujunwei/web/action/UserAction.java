package com.zhujunwei.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zhujunwei.domain.User;
import com.zhujunwei.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = 1L;
	
	//使用模型驱动
	private User user = new User() ;
	@Override
	public User getModel() {
		return user;
	}
	
	//注入Service：
	private UserService userService ;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 用户注册的方法：regist 
	 */
	public String regist(){
		userService.regist(user);
		return LOGIN;
	}
	
	/**
	 * 用户登录的方法：login
	 */
	public String login() {
		User existUser = userService.login(user);
		if(existUser == null) {
			//登录失败
			//添加错误信息
			this.addActionError("用户名或密码错误，请重新登录");
			return LOGIN ;
		} else {
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS ;
		}
	}
	
}
