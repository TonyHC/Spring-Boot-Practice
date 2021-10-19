package com.springframework.dependencyinjection.Service;

public interface GreetingRepository {
    String getEnglishGreeting();
    String getFrenchGreeting();
    String getDefaultGreeting();
}