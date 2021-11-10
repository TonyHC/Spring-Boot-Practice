package com.springframework.jms.listener;

import com.springframework.jms.config.JmsConfig;
import com.springframework.jms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloMessageListener {
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
}