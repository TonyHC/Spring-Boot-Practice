package com.spring.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp {
	public static void main(String[] args) {
		// Read Spring Config Java Class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the Beans from Spring Container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
	
		// Call method to find the Accounts
		List<Account> accounts = null;
		
		try {
			// Add boolean flag to stimulate an exception
			boolean tripWire = true;
			accounts = accountDAO.findAccounts(tripWire);
		} catch (Exception exception) {
			System.out.println("Exception caught: " + exception);
		}
		
		// Display the Accounts
		System.out.println("Accounts: " + accounts);
		
		// Close the context
		context.close();
	}
}