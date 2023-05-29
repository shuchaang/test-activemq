package com.example.cosnumer;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.Session;

@SpringBootApplication
@EnableJms
public class CosnumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosnumerApplication.class, args);
    }


    @JmsListener(destination = "sc-test-topic",containerFactory = "topicListenerFactory")
    public void onMessage(ActiveMQTextMessage message, Session session) throws JMSException {
        System.out.println("topic:"+message.getText());
        message.acknowledge();
    }
    @JmsListener(destination = "sc-test-queue",containerFactory = "queueListenerFactory")
    public void onMessage2(ActiveMQTextMessage message){
        try {
            System.out.println("queue:"+message.getText());
            message.acknowledge();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
