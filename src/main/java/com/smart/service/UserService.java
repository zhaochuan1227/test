package com.smart.service;

import com.smart.entity.User;

/**
 * 服务接口
 */
public interface UserService {
	/**
	 * 封装登录逻辑
	 * 如果登录成功就返回登录的成功的用户信息
	 * 否则登录失败就抛出异常
	 * @param name
	 * @param password
	 * @return 登陆成功的用户名
	 * @throws NameOrPasswordException
	 */
	User login(String name,String password) throws NameOrPasswordException;
}
