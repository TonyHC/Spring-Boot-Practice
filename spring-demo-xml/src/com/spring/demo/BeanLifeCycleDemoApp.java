package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

	public static void main(String[] args) {
		// Load the Configuration File
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
		
		// Retrieve the Singleton Scoped Beans from the Spring Container
		Coach theCoach = context.getBean("myTennisCoach", Coach.class);
		Coach assistantCoach = context.getBean("myTennisCoach", Coach.class);
		
		// Retrieve the Prototype Scoped Beans from the Spring Container
		Coach teamCoach = context.getBean("myCoach", Coach.class);
		Coach headCoach = context.getBean("myCoach", Coach.class);
		
		// Check if the Singleton Scoped Beans are the same
		boolean result = theCoach == assistantCoach;
		
		// Check if the Prototype Scoped Beans are the same
		boolean secondResult = teamCoach == headCoach;
		
		// Print out the result for Singleton Scoped Beans
		System.out.println("\nPointing to the same object: " + result);
		System.out.println("Memory Location for the theCoach: " + theCoach);
		System.out.println("Memory Location for the assistantCoach: " + assistantCoach);
		
		// Print out the result for Prototype Scoped Beans
		System.out.println("\nPointing to the same object: " + secondResult);
		System.out.println("Memory Location for the theCoach: " + teamCoach);
		System.out.println("Memory Location for the assistantCoach: " + headCoach);
		
		// Close the context
		context.close();
	}
}