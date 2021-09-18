package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		// Load the configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// Retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		Coach theTennisCoach = context.getBean("myTennisCoach", Coach.class);
		
		// Call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theTennisCoach.getDailyWorkout());
		
		// Call the new method for fortunes
		System.out.println(theCoach.getDailyFortune());
		
		// Close the context
		context.close();
	}
}