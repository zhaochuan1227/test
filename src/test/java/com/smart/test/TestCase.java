package com.smart.test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestCase {
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
	 * 测试 数据库配置
	 */
	@Test
	public void test1(){
		try {
			Object obj = ac.getBean("db");
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试 数据库连接
	 */
	@Test
	public void test2(){
		try {
			DataSource ds = ac.getBean("dataSource",DataSource.class);
			Connection conn = ds.getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 测试 sqlSessionFactory
	 */
	@Test
	public void test3(){
		try {
			Object obj = ac.getBean("sqlSessionFactory");
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 测试 mapperScanner
	 */
	@Test
	public void test4(){
		try {
			Object obj = ac.getBean("mapperScanner");
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
