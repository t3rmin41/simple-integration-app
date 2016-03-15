package com.simple.integration.platform.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmsReceiver implements MessageListener {

    private static Logger log = LoggerFactory.getLogger(JmsReceiver.class);
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String text = textMessage.getText();
                System.out.println("Received: " + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Received: " + message);
        }
    }

}
