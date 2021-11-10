package com.springframework.jms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springframework.jms.config.JmsConfig;
import com.springframework.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {
    // Preconfigured to talk to the embedded ActiveMQ Server
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

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

    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {
        // Build the reply message
        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        // Receive a message from message queue, replied to the received message by creating a temporary message queue
        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_AND_RECEIVE_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = null;

                // Manually create (convert) and send the reply message back
                try {
                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type", "com.springframework.jms.model.HelloWorldMessage");

                    System.out.println("Sending Hello");

                    return helloMessage;
                } catch (JsonProcessingException exception) {
                    throw  new JMSException("Invalid JMS Message");
                }

            }
        });

        // Print out the reply message info
        System.out.println(receivedMessage.getBody(String.class));
    }
}