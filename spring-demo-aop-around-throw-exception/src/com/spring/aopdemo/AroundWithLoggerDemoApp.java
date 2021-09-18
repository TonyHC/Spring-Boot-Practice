package com.spring.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.aopdemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp {
	// Logger API found in JDK: Logger uses the Class Name
	private static Logger logger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());
	
	public static void main(String[] args) {
		// Read Spring Config Java Class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the Bean from Spring Container
		TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
	
		logger.info("Calling getFortune");
		
		// Call the method and display the results
		String data = trafficFortuneService.getFortune();
		logger.info("Fortune: " + data);
		
		// Close the context
		context.close();
	}
}