package com.springframework.dependencyinjection.Controller;

import com.springframework.dependencyinjection.Service.ConstructorGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConstructorInjectionControllerTest {
    ConstructorInjectionController controller;

    @BeforeEach
    void setUp() {
        controller =  new ConstructorInjectionController(new ConstructorGreetingService());
    }

    @Test
    void getGreeting() {
        System.out.println(controller.getGreeting());
    }
}