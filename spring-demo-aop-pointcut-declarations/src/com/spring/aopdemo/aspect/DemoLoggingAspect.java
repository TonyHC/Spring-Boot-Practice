package com.spring.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
	@Pointcut("execution (* com.spring.aopdemo.dao.*.*(..))")
	private void daoPackage() {}
	
	@Before("daoPackage()")
	public void beforeAddAccountAdvice() {
		System.out.println("Executing @Before advice on addAccount()");
	}
	
	@Before("daoPackage()")
	public void performApiAnalytics() {
		System.out.println("Performing Api Analytics");
	}
}