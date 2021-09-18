package com.spring.demo;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class FileFortuneService implements FortuneService {
	private List<String> fortunes;
	private Random random;
	
	public FileFortuneService() {
		System.out.println("FileFortuneService: inside of Default Constructor");
		this.fortunes = new ArrayList<String>();
		this.random = new Random();
	}

	@PostConstruct
	private void loadFile() {
		System.out.println("FileFortuneService: inside of loadFile()");
		
		String filePath = "C:\\Users\\TonyC\\Desktop\\SpringUsingEclipse\\spring-demo-annotations\\src\\fortune-data.txt";
		
		try (Scanner scanner = new Scanner(Paths.get(filePath))) {
			while(scanner.hasNextLine()) {
				String fortune = scanner.nextLine();
				fortunes.add(fortune);
			}
			
			System.out.println("FileFortuneService: Successful added data from loadFile()");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public String getFortune() {
		int indexOfRandomFortune = random.nextInt(fortunes.size());
		return fortunes.get(indexOfRandomFortune);
	}
}