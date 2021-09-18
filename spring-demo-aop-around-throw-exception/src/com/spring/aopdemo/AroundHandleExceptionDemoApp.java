package com.spring.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {
	// Logger API found in JDK: Logger uses the Class Name
	private static Logger logger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());
	
	public static void main(String[] args) {
		// Read Spring Config Java Class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the Bean from Spring Container
		TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
	
		logger.info("Calling getFortune");
		
		// Call the method and display the results
		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);
		logger.info("Fortune: " + data);
		
		// Close the context
		context.close();
	}
}