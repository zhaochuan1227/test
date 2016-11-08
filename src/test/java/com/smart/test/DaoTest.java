package com.smart.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.dao.UserDao;
import com.smart.entity.User;

public class DaoTest {
	ApplicationContext ac;
	@Before
	public void init(){
		System.out.println("spring start");
		ac = new ClassPathXmlApplicationContext(
				"spring-mybatis.xml",
				"spring-web.xml",
				"spring-service.xml"			
				);
		System.out.println("spring start success");
	}
	/**
	 * 测试 新建用户
	 */
	@Test
	public void test1(){
		try {
			UserDao userDao = ac.getBean("userDao",UserDao.class);
			User user = new User("2","admin","123456","admin","admin");
			userDao.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * 测试 按用户名查找用户
	 * ba3693d58b44f998c7f58fb57989b5f4
	 * ba3693d58b44f998c7f58fb57989b5f4
	 * ba3693d58b44f998c7f58fb57989b5f4
	 */
	@Test
	public void test2(){
		try {
			UserDao userDao = ac.getBean("userDao",UserDao.class);
			User user = userDao.findUserByName("tom");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试 按用户Id查找用户
	 */
	@Test
	public void test3(){
		try {
			UserDao userDao = ac.getBean("userDao",UserDao.class);
			User user = userDao.findUserById("333c6d0b-e4a2-4596-9902-a5d98c2f665a");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
