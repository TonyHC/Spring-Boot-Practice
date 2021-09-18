package com.spring.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
}