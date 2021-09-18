package com.spring.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoccerConfig {
	@Bean
	public FortuneService fileFortuneService() {
		return new FileFortuneService();
	}
	
	@Bean
	public Coach soccerCoach() {
		SoccerCoach theSoccerCoach = new SoccerCoach(fileFortuneService());
		return theSoccerCoach;
	}
}