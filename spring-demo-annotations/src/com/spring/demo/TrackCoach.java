package com.spring.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {
	private FortuneService fortuneService;
	
	// Field Injection
	@Value("${test.email}")
	private String emailAddress;
	@Value("${test.team}")
	private String team;
	
	// Constructor Injection
	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	// Getter Methods for Private Fields (Field Injection)
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getTeam() {
		return team;
	}

	@Override
	public String getDailyWorkout() {
		return "Perform 10 sets of 200m dash";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
}