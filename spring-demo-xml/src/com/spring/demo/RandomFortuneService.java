package com.spring.demo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {
	@Override
	public String getFortune() {
		String[] fortunes = {"Good Fortune", "Okay Fortune", "Bad Fortune"};
		
		Random randomFortune = new Random();
		int randomFortuneIndex = randomFortune.nextInt(fortunes.length);
		
		return fortunes[randomFortuneIndex];
	}
}
