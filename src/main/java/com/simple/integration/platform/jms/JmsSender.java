package com.simple.integration.platform.jms;

import javax.jms.JMSException;
import javax.jms.Session;
//import javax.jms.TextMessage;
import javax.jms.Message;

//import org.apache.activemq.Message;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JmsSender {

    private JmsTemplate jmsTemplate;

    public void sendText(final String text) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(text);
            }
        });
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
