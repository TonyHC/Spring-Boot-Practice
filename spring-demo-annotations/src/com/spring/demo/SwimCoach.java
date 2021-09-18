package com.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SwimCoach implements Coach {
	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	@Value("${test.email}")
	private String emailAddress;
	
	@Value("${test.team}")
	private String team;
	
	public SwimCoach() {
		
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getTeam() {
		return team;
	}

	@Override
	public String getDailyWorkout() {
		return "Perform 2 sets of 1000m back stroke";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
}