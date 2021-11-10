package com.springframework.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsApplication {
    public static void main(String[] args) throws Exception {
        // Embedded ActiveMQ Server (normally run outside of Spring Boot, could use Docker to run ActiveMQ Server)
        /*
        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
                .setPersistenceEnabled(false)
                .setJournalDirectory("target/data/journal")
                .setSecurityEnabled(false)
                .addAcceptorConfiguration("invm", "vm://0"));
         */

        SpringApplication.run(JmsApplication.class, args);
    }
}