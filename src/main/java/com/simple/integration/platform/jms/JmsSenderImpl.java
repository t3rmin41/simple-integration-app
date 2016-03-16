package com.simple.integration.platform.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class JmsSenderImpl implements JmsSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendText(String text) {
        jmsTemplate.convertAndSend(text);
    }

}
