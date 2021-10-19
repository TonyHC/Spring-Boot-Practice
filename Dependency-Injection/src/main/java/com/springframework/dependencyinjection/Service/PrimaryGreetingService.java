package com.springframework.dependencyinjection.Service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// Spring will use this GreetingService as Primary Service if no @Qualifier is specified
@Primary
@Service
public class PrimaryGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World from Primary Bean";
    }
}