package com.example.produce;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class MQConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("ssl://b-f80a5cc9-31ab-4781-8914-ecb9fe93c94a-1.mq.us-west-2.amazonaws.com:61617");
        connectionFactory.setUserName("moego-active-mq-dev-v1-master");
        connectionFactory.setPassword("+S+22Y8FlzqazglDiXbwIA");
        return connectionFactory;
    }

    @Bean
    public JmsTemplate queueTemplate(@Autowired ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setPubSubDomain(false);
        return  jmsTemplate;
    }

    @Bean
    public JmsTemplate topicTemplate(@Autowired ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return  jmsTemplate;
    }

    @Bean
    public Queue q(){
        return new ActiveMQQueue("sc-test-queue");
    }
    @Bean
    public Topic t(){
        return new ActiveMQTopic("sc-test-topic");
    }

}
