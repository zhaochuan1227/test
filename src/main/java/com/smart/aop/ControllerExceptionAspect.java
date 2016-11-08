package com.smart.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.smart.service.NameOrPasswordException;
import com.smart.util.JsonResult;

@Component
@Aspect
public class ControllerExceptionAspect {

	/*
	 * public ControllerExceptionAspect() { System.out.println(
	 * "init ControllerExceptionAspect"); }
	 * 
	 * // 通知(切入点) 通知+切入点 = 植入位置 // @Before(
	 * "execution(* com.smart.service.*Service.*(..))")
	 * // @Before("bean(userService)")
	 * 
	 * @Before("within(com.smart.service.*)") public void hello() {
	 * System.out.println("hello World!"); }
	 * 
	 * @Around("bean(userService)") public Object testAround(ProceedingJoinPoint
	 * pjp) throws Throwable { System.out.println("开始调用"); // 执行目标方法 Object obj
	 * = pjp.proceed(); System.out.println("调用结束"); return obj; }
	 */
	//
	/**
	 * 统一异常处理
	 * 
	 * @param target
	 * @return
	 */
	@Around("within(com.smart.web.*Controller)")
	public Object process(ProceedingJoinPoint target) {
		try {
			Object obj = target.proceed();
			return obj;
		} catch (Throwable e) {
			if(e instanceof NameOrPasswordException){
				NameOrPasswordException nope = (NameOrPasswordException)e;
				return new JsonResult<Object>(nope.getField(),nope.getMessage(),null);
			}
			return new JsonResult<Object>(e);
		}
	}

}
