package com.spring.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SoccerJavaConfigDemoApp {
	public static void main(String[] args) {
		// Read Spring Configuration Java Class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SoccerConfig.class);

		// Get the Bean from Spring Container
		Coach soccerCoach = context.getBean("soccerCoach", Coach.class);
		
		// Call methods on Bean
		System.out.println(soccerCoach.getDailyWorkout());
		System.out.println(soccerCoach.getDailyFortune());
	
		// Close the context
		context.close();
	}
}