package com.springframework.dependencyinjection.Service;

import org.springframework.stereotype.Component;

@Component
public class GreetingRepositoryImpl implements GreetingRepository {
    @Override
    public String getEnglishGreeting() {
        return "Hello";
    }

    @Override
    public String getFrenchGreeting() {
        return "Bonjour";
    }

    @Override
    public String getDefaultGreeting() {
        return "Hello World from Primary Bean";
    }
}