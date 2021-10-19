package com.springframework.dependencyinjection;

import com.springframework.dependencyinjection.Controller.*;
import com.springframework.dependencyinjection.examplebeans.FakeDataSource;
import com.springframework.dependencyinjection.examplebeans.FakeJmsBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {
    public static void main(String[] args) {
        // Spring Context
        ApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);

        // Get the Controller Object from Controller Bean Name
        MyController myController = (MyController) context.getBean("myController");
        System.out.println(myController.sayHello());

        I18nController i18nController = (I18nController) context.getBean("i18nController");
        System.out.println(i18nController.sayHello());

        PropertyInjectionController propertyInjectionController = (PropertyInjectionController) context.getBean("propertyInjectionController");
        System.out.println(propertyInjectionController.getGreeting());

        SetterInjectionController setterInjectionController = (SetterInjectionController) context.getBean("setterInjectionController");
        System.out.println(setterInjectionController.getGreeting());

        ConstructorInjectionController constructorInjectionController = (ConstructorInjectionController) context.getBean("constructorInjectionController");
        System.out.println(constructorInjectionController.getGreeting());

        FakeDataSource fakeDataSource = (FakeDataSource) context.getBean(FakeDataSource.class);
        System.out.println(fakeDataSource.getUser());
        System.out.println(fakeDataSource.getUrl());

        FakeJmsBroker fakeJmsBroker = (FakeJmsBroker) context.getBean("fakeJmsBroker");
        System.out.println(fakeJmsBroker.getUser());
        System.out.println(fakeJmsBroker.getPassword());
        System.out.println(fakeJmsBroker.getUrl());
    }
}