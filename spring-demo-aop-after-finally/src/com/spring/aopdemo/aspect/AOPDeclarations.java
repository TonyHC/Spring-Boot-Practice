package com.spring.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPDeclarations {
	// Pointcut expression: Applies to all methods in the package specify 
	@Pointcut("execution(* com.spring.aopdemo.dao.*.*(..))")
	public void daoPackage() {}
	
	// Pointcut expression: Applies to all getter methods in the package specify
	@Pointcut("execution(* com.spring.aopdemo.dao.*.get*(..))")
	public void getter() {}
	
	// Pointcut expression: Applies to all setter methods in the package specify
	@Pointcut("execution(* com.spring.aopdemo.dao.*.set*(..))")
	public void setter() {}
	
	// Pointcut expression: Applies to all methods in package specify but excludes getter/setter methods
	@Pointcut("daoPackage() && !(getter() || setter())")
	public void daoPackageNotGetterOrSetter() {}
}