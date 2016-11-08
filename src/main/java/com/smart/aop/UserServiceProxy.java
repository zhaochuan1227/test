package com.smart.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.entity.User;
import com.smart.service.NameOrPasswordException;
import com.smart.service.UserService;

/**
 * 用于讲解AOP的原理做的静态代理
 */
@Service("target")
public class UserServiceProxy implements UserService {

	@Autowired
	UserService target;

	public User login(String name, String password) throws NameOrPasswordException {
		System.out.println("hello Proxy");
		return target.login(name, password);
	}

}
