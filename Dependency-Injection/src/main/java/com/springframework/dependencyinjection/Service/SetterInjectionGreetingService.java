package com.springframework.dependencyinjection.Service;

import org.springframework.stereotype.Service;

@Service
public class SetterInjectionGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World in Setter";
    }
}