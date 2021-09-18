package com.spring.demo;

public class BaseballCoach implements Coach {
	// Define a private field for the dependency
	private FortuneService fortuneService;
	
	// Define a constructor for dependency injection
	public BaseballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on fielding";
	}

	@Override
	public String getDailyFortune() {
		// Use my fortuneSerivce  to get a fortune
		return fortuneService.getFortune();
	}
}
