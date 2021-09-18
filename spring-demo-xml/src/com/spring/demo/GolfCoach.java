package com.spring.demo;

public class GolfCoach implements Coach {
	private FortuneService fortuneService;

	public GolfCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice 300 tee shots";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
}