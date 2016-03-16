package com.simple.integration.platform.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simple.integration.platform.jms.JmsReceiver;
import com.simple.integration.platform.jms.JmsSender;

@RestController
public class SimpleController {

    @Autowired
    private ConfigurableApplicationContext context;
    
    @RequestMapping(value = "/rs/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello from REST";
    }
    
    @RequestMapping(value = "/queue/hello", method = RequestMethod.GET)
    public String queueHello() {
        JmsSender jmsSender = (JmsSender) context.getBean("jmsMqSender");
        jmsSender.sendText("hellooooo " + new Date());
        JmsReceiver jmsReceiver = (JmsReceiver) context.getBean("jmsMqReceiver");
        return jmsReceiver.getMessage();
    }
    
}
