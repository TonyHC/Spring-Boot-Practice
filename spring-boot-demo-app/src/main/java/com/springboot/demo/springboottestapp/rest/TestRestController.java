package com.springboot.demo.springboottestapp.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
	// Inject Properties: coach.name and team.name
	@Value("${coach.name}")
	private String coachName;
	
	@Value("${team.name}")
	private String teamName;
	
	// REST endpoint '/'
	@GetMapping("/")
	public String currentTime() {
		return "Hello World! Time on server is " + LocalDateTime.now();
	}
	
	// REST endpoint '/workout'
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "Perform a 5km run";
	}
	
	// REST endpoint '/teaminfo'
	@GetMapping("/teaminfo")
	public String getTeamInfo() {
		return "Coach: " + coachName + ", Team: " + teamName;
	}
}