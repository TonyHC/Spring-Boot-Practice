package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PracticeAnnotationDemoClass {
	public static void main(String[] args) {
		// Read the Spring Configuration File
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// Retrieve the Bean from the Spring Container
		Coach lacrosseCoach = context.getBean("lacrosseCoach", Coach.class);
		
		// Call methods on the Bean
		System.out.println(lacrosseCoach.getDailyFortune());
		
		// Close the context
		context.close();
	}
}