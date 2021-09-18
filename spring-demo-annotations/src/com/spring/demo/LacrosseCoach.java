package com.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LacrosseCoach implements Coach {
	@Autowired
	@Qualifier("fileFortuneService")
	private FortuneService fortuneService;

	public LacrosseCoach() {
		
	}
	
	@Override
	public String getDailyWorkout() {
		return "Perform 20 sets of 100 yard shuttle dashes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
}