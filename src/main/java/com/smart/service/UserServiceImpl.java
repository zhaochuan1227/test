package com.smart.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.dao.UserDao;
import com.smart.entity.User;
import com.smart.util.Utils;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name = "userDao")
	private UserDao userDao;
	//@Transactional
	public User login(String name, String password) throws NameOrPasswordException {
		// 检查入口参数
		if (name == null || name.trim().isEmpty()) {
			throw new NameOrPasswordException(1,"用户名不能为空！");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new NameOrPasswordException(2,"密码不能为空！");
		}
		name = name.trim();
		password = password.trim();
		User user = userDao.findUserByName(name);
		if (user == null) {
			throw new NameOrPasswordException(1,"用户名不存在！");
		}
		String pwd = Utils.md5salt(password);
		//ba3693d58b44f998c7f58fb57989b5f4
		//System.out.println(pwd);
		if (!pwd.equals(user.getPassword())) {
			throw new NameOrPasswordException(2,"密码错误！");
		}
		return user;
	}
	
}
