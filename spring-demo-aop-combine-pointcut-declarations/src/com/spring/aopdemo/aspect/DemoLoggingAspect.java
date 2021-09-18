package com.spring.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
	// Pointcut expression: Applies to all methods in the package specify 
	@Pointcut("execution(* com.spring.aopdemo.dao.*.*(..))")
	private void daoPackage() {}
	
	// Pointcut expression: Applies to all getter methods in the package specify
	@Pointcut("execution(* com.spring.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	// Pointcut expression: Applies to all setter methods in the package specify
	@Pointcut("execution(* com.spring.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	// Pointcut expression: Applies to all methods in package specify but excludes getter/setter methods
	@Pointcut("daoPackage() && !(getter() || setter())")
	private void daoPackageNotGetterOrSetter() {}
	
	@Before("daoPackageNotGetterOrSetter()")
	public void beforeAddAccountAdvice() {
		System.out.println("Executing @Before advice on addAccount()");
	}
	
	@Before("daoPackageNotGetterOrSetter()")
	public void performApiAnalytics() {
		System.out.println("Performing Api Analytics");
	}
}