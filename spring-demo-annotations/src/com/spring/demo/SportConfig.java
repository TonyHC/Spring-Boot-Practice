package com.spring.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan("com.spring.demo")
@PropertySource("classpath:sport.properties")
public class SportConfig {
	// Define Bean for SadFortuneService
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	
	// Define Bean for TrackCoach and Inject Dependency (Constructor Injection)
	@Bean
	public Coach trackCoach() {
		TrackCoach myTrackCoach = new TrackCoach(sadFortuneService());
		return myTrackCoach;
	}
}