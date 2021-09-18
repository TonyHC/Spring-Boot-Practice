package com.spring.demo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
	@Override
	public String getFortune() {
		String[] fortunes = {"Good Fortune Today", "Okay Fortune Today", "Bad Fortune Today"};
		
		Random randomFortune = new Random();
		int indexOfRandomFortune = randomFortune.nextInt(fortunes.length);
		
		return fortunes[indexOfRandomFortune];
	}
}