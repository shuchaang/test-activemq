package com.example.produce;

import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.Topic;

@SpringBootApplication
@EnableJms
public class ProduceApplication implements ApplicationRunner {

    public static void main(String[] args)
    {
        SpringApplication.run(ProduceApplication.class, args);
    }

    @Autowired
    @Qualifier("topicTemplate")
    private JmsTemplate topicTemplate;

    @Autowired
    @Qualifier("queueTemplate")
    private JmsTemplate queueTemplate;


    @Autowired
    private Queue q;

    @Autowired
    private Topic t;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 10; i++) {
            ActiveMQTextMessage m = new ActiveMQTextMessage();
            m.setText("hellO"+i);
            m.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,10000);
            queueTemplate.convertAndSend(q,m);
            //topicTemplate.convertAndSend(t,"hello:"+i);
        }
    }
}
