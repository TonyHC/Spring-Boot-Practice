package com.springframework.jms.sender;

import com.springframework.jms.config.JmsConfig;
import com.springframework.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {
    // Preconfigured to talk to the embedded ActiveMQ Server
    private final JmsTemplate jmsTemplate;

    // Sends message every 2000 milliseconds
    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("Sending a message");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World")
                .build();

        // Use the Message Converter to send the specific message to the
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        System.out.println("Message Sent");
    }
}