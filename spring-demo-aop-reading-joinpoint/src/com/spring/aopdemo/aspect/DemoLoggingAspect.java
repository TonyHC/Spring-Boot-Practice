package com.spring.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spring.aopdemo.Account;

@Aspect
@Component
@Order(6)
public class DemoLoggingAspect {
	@Before("com.spring.aopdemo.aspect.AOPDeclarations.daoPackageNotGetterOrSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {
		System.out.println("Executing @Before advice on addAccount()");
		
		// Display the Method Signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		
		System.out.println("Method: " + methodSignature);
		
		// Get Method Arguments
		Object[] args = joinPoint.getArgs();
		
		// Display Method Arguments
		for (Object arg : args) {
			System.out.println(arg);
			
			if (arg instanceof Account) {
				// Downcast and print Account information
				Account account = (Account) arg;
				
				System.out.println("Account Name: " + account.getName());
				System.out.println("Account Level: " + account.getLevel());
			}
		}
	}
}