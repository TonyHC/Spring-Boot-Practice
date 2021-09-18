package com.spring.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.aopdemo.dao.AccountDAO;
import com.spring.aopdemo.dao.MembershipDAO;

public class MainDemoApp {
	public static void main(String[] args) {
		// Read Spring Config Java Class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the Beans from Spring Container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// Create a Account object and Populate with data
		Account masterAccount = new Account();
		masterAccount.setName("master");
		masterAccount.setLevel("Platnium");
		
		// Call the business methods
		accountDAO.addAccount(masterAccount, true);
		accountDAO.isVipMember();
		
		// Call AccountDAO getter/setter methods
		accountDAO.setName("test");
		accountDAO.setServiceCode("testCode");
		
		String name = accountDAO.getName();
		String code = accountDAO.getServiceCode();
	
		membershipDAO.addAccount("Thomas");
		
		// Close the context
		context.close();
	}
}