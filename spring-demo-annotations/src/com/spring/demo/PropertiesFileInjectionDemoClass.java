package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertiesFileInjectionDemoClass {

	public static void main(String[] args) {
		// Load the Spring Configuration File
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// Retrieve the Bean from the Spring Container
		SwimCoach swimCoach = context.getBean("swimCoach", SwimCoach.class);
		
		// Call methods on the Bean
		System.out.println(swimCoach.getEmailAddress());
		System.out.println(swimCoach.getTeam());
		
		// Close the context
		context.close();
	}
}