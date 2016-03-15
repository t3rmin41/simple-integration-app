package com.simple.integration.platform.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.simple.integration.platform.jms.JmsReceiver;
import com.simple.integration.platform.jms.JmsSender;

@Configuration
public class ActiveMqConfig {

    //Common part
    @Bean(name="jmsConnectionFactory")
    public ActiveMQConnectionFactory jmsConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return connectionFactory;
    }
    
    //JmsSender part
    @Bean 
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDefaultDestination(new ActiveMQQueue("jms.queue"));
        jmsTemplate.setConnectionFactory(jmsConnectionFactory());
        return jmsTemplate;
    }

    @Bean(name="jmsMqSender")
    public JmsSender jmsSender() {
        JmsSender jmsSender = new JmsSender();
        jmsSender.setJmsTemplate(jmsTemplate());
        return jmsSender;
    }

    //JmsReceiver part
    @Bean(name="jmsMqReceiver")
    public JmsReceiver jmsReceiver() {
        return new JmsReceiver();
    }
    
    @Bean
    public DefaultMessageListenerContainer messageListenerContainer() {
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(jmsConnectionFactory());
        messageListenerContainer.setDestinationName("jms.queue");
        messageListenerContainer.setMessageListener(jmsReceiver());
        return messageListenerContainer;
    }
}
