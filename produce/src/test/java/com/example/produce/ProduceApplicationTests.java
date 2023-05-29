package com.example.produce;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

@SpringBootTest
class ProduceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void testTrans() throws JMSException {
        ConnectionFactory connectionFactory =jmsTemplate.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        try {
            MessageProducer producer = session.createProducer(new ActiveMQQueue("test-queue"));
            for (int i = 0; i < 10; i++) {
                producer.send(producer.getDestination(),new ActiveMQTextMessage());
            }
            session.commit();
        } catch (JMSException e) {
            session.rollback();
            throw new RuntimeException(e);
        }
    }

}
