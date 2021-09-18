package com.spring.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
	// Add all of our related Advices for logging 
	
	// @Before advice
	// @Before("execution(public void addAccount())")
	// @Before("execution(public void com.spring.aopdemo.dao.AccountDAO.addAccount())")
	// @Before("execution(public void add*())")
	// @Before("execution(* add*())")
	// @Before("execution(* add*(com.spring.aopdemo.Account, ..))")
	
	@Before("execution(* com.spring.aopdemo.dao.*.*(..))")
	public void beforeAddAccountAdvice() {
		// Add our custom code, executes before this addAccount() method signature
		System.out.println("Executing @Before advice on addAccount()");
	}
}