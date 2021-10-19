package com.springframework.dependencyinjection.Controller;

import com.springframework.dependencyinjection.Service.ConstructorGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropertyInjectionControllerTest {
    PropertyInjectionController controller;

    @BeforeEach
    void setup() {
        controller = new PropertyInjectionController();

        controller.greetingService = new ConstructorGreetingService();
    }

    @Test
    void getGreeting() {
        System.out.println(controller.getGreeting());
    }
}