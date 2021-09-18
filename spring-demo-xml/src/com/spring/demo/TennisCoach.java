package com.spring.demo;

public class TennisCoach implements Coach {
	private FortuneService fortuneService;
	
	public TennisCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Perform 300 backhand returns";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	
	public void startUpProcess() {
		System.out.println("TennisCoach: inside method startUpProcess");
	}
	
	public void cleanUpProcess() {
		System.out.println("TennisCoach: inside method cleanUpProcess");
	}
}