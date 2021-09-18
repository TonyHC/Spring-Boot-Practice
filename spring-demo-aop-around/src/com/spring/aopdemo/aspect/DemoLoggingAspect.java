package com.spring.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	@After("execution(* com.spring.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdivce(JoinPoint joinPoint) {
		// Print out which method we are advising on
		String methodSignature = joinPoint.getSignature().toShortString();
		System.out.println("Executing @After (finally) on Method: " + methodSignature);
	}
	
	@AfterReturning(pointcut = "execution(* com.spring.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		// Print out which method we are advising on
		String methodSignature = joinPoint.getSignature().toShortString();
		System.out.println("Executing @AfterReturning on Method: " + methodSignature);
		
		// Prints the results of method call
		System.out.println("Result is: " + result);
		
		// Post-Process the Data by modifying it
		// Check if result (List of Accounts) exits,
		// then we convert all Account names to uppercase
		if (!result.isEmpty()) {
			convertAccountNamesToUpperCase(result);
			System.out.println("Result is: " + result);
		}
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		for (Account account : result) {
			String upperCase = account.getName().toUpperCase();
			account.setName(upperCase);
		}
	}
	
	@AfterThrowing(pointcut = "execution(* com.spring.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "exception")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {
		// Print out which method we are advising on
		String methodSignature = joinPoint.getSignature().toShortString();
		System.out.println("Executing @AfterThrowing on Method: " + methodSignature);
				
		// Log the exception
		System.out.println("Execution: " + exception);
	}
	
	@Around("execution(* com.spring.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// Print out method we are advising on
		String methodSignature = proceedingJoinPoint.getSignature().toShortString();
		System.out.println("Executing @Around on Method: " + methodSignature);
		
		// Get begin timestamp
		long begin = System.currentTimeMillis();
		
		// Execute the method
		Object result = proceedingJoinPoint.proceed();
		
		// Get end timestamp
		long end = System.currentTimeMillis();
		
		// Compute duration and display it
		long duration = end - begin;
		System.out.println("Time Duration: " + duration / 1000 + " seconds");
		
		return result;
	}
}