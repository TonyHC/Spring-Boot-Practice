package com.spring.demo;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {
	@Override
	public String getDailyWorkout() {
		return "Perform 100 punt returns";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return null;
	}
}