package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		// Load the Configuration File
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		// Retrieve the Bean from the Spring Container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		Coach assistantCoach = context.getBean("myCoach", Coach.class);

		
		// Check if the Beans are the same
		boolean result = theCoach == assistantCoach;
		
		// Print out the result
		System.out.println("Pointing to the same object: " + result);
		System.out.println("Memory Location for the theCoach: " + theCoach);
		System.out.println("Memory Location for the assistantCoach: " + assistantCoach);
		
		context.close();
	}
}
