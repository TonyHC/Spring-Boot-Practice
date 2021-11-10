package com.springframework.jms.listener;

import com.springframework.jms.config.JmsConfig;
import com.springframework.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMessageListener {
    private final JmsTemplate jmsTemplate;

    // Set up a JMS Listener to listen to this Message Queue and
    // received message to this method
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    // @Payload: Deserialize the message from message queue (expect a HelloWorldMessage object)
    // Annotation that binds a method parameter to the payload of a message
    // Can also be used to associate a payload to a method invocation
    // The payload may be passed through a MessageConverter to convert it from serialized form with a
    // specific MIME type to an Object matching the target method parameter
    // @Headers: Annotation which indicates that a method parameter should be bound to the headers of a message
    // The annotated parameter must be assignable to Map with String keys and Object values
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) {
        System.out.println("Received a message");
        System.out.println(helloWorldMessage);

        // Uncomment and view to see retry count in debugger
        // If an exception occurs, JMS will resend the message x amount of times till the message is received
        // throw new RuntimeException("foo");
    }

    @JmsListener(destination = JmsConfig.MY_SEND_AND_RECEIVE_QUEUE)
    public void listenForHello (@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) throws JMSException {
        // Build the payload of the reply message
        HelloWorldMessage payloadMessage = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        // Set the payload of the reply message from temporarily message queue
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMessage);
    }
}