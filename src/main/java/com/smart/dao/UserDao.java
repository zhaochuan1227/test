package com.smart.dao;

import com.smart.entity.User;

/**
 *	封装用户user对象的CRUD方法
 *	MapperScanner 会扫描这个接口，自动为接口创建实现类，
 *	并且实例化接口的子类Bean对象
 */
public interface UserDao {
	/**
	 * 将用户对象保存到数据库
	 */
	public void saveUser(User user);
	public User findUserByName(String name);
	public User findUserById(String id);
}
