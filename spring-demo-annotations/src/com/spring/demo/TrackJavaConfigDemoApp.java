package com.spring.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TrackJavaConfigDemoApp {
	public static void main(String[] args) {
		// Read Spring Configuration Java Class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(SportConfig.class);

		// Get the Bean from Spring Container
		TrackCoach theCoach = context.getBean("trackCoach", TrackCoach.class);
		
		// Call methods on the Bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		
		// Call the TrackCoach's getter methods
		System.out.println("Email: " + theCoach.getEmailAddress());
		System.out.println("Team: " + theCoach.getTeam());
		
		// Close the context
		context.close();
	}
}