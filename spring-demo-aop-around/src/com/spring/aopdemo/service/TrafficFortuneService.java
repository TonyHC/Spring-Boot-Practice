package com.spring.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {
	public String getFortune() {
		try {
			// Stimulate a delay: Sleeps for 5 seconds
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		
		return "Today will have bad weather";
	}
}