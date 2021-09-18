package com.spring.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp {
	public static void main(String[] args) {
		// Read Spring Config Java Class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the Beans from Spring Container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
	
		// Call method to find the Accounts
		List<Account> accounts = accountDAO.findAccounts();
		
		System.out.println("Accounts: " + accounts);
		
		// Close the context
		context.close();
	}
}