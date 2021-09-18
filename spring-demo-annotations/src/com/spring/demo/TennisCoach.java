package com.spring.demo;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TennisCoach implements Coach, DisposableBean {
	// Field Injection
	//@Autowired
	//@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	/*
	public TennisCoach() {
		System.out.println("TennisCoach: inside default constructor");
	}
	*/

	// Constructor Injection 
	@Autowired
	public TennisCoach(@Qualifier("randomFortuneService") FortuneService fortuneService) {
		//System.out.println("TennisCoach: inside Constructor Injection using @Autowired and @Qualifier");
		this.fortuneService = fortuneService;
	}

	// Define the init method
	@PostConstruct
	public void doMyStartUpStuff() {
		System.out.println("TennisCoach: inside of doMyStartUpStuff");
	}
	
	/*
	// Define the destroy method
	@PreDestroy
	public void doMyCleanUpStuff() {
		System.out.println("TennisCoach: inside of doMyCleanUpStuff");
	}
	*/
	
	// Define the destroy method by implementing the DispoableBeans Interface
	// This allows us to successfully destroy any prototype scoped Beans created
	// using the custom class MyCustomBeanProcessor.java 
	@Override
	public void destroy() throws Exception {
		System.out.println("TennisCoach: inside of destroy");
	}
	
	/*
	 * Setter or Method Injection
	@Autowired
	public void updateFortuneService(FortuneService fortuneService) {
		System.out.println("TennisCoach: inside method updateFortuneService");
		this.fortuneService = fortuneService;
	}
	*/
	
	@Override
	public String getDailyWorkout() {
		return "Perform 300 drop shots";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
}