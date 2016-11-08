package com.smart.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.service.UserService;

public class ServiceTest {
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
	 * 测试 login方法
	 */
	@Test
	public void test1(){
		try {
			UserService us = ac.getBean("userService",UserService.class);
			System.out.println(us.login("zhoujia", "123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
