package com.simple.integration.platform.router;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.simple.integration.platform.jms.JmsReceiver;
import com.simple.integration.platform.jms.JmsSender;

@Component
public class SimpleRouter extends RouteBuilder {

    private static Logger log = LoggerFactory.getLogger(SimpleRouter.class);
    
    @Override
    public void configure() throws Exception {
        //Access us using http://localhost:8888/camel/hello
        //from("servlet:///hello").transform().constant("Hello from Camel!");
        helloRoute();
        
        sampleRoute();
        
        queueRoute();
        
        heartbeat();
        
        //Trigger run right after startup. No Servlet request required.
        //from("timer://foo?fixedRate=true&period=10s").log("Camel timer triggered.");
    }

    public void helloRoute() {
        from("servlet:///hello").
        transform().
        constant("Hello from Camel!");
    }

    public void sampleRoute() {
        from("servlet:///sample").
        transform().
        constant("Sample of Camel");
    }
    
    public void queueRoute() {
        from("activemq:jms.queue").bean(JmsSender.class).
        log("Sending message from jmsMqSender to jmsMqReceiver").
        to("activemq:jms.queue").bean(JmsReceiver.class);
    }
    
    private void heartbeat() {
        from("timer://heartbeat?fixedRate=true&period=10s").
        to("log:com.simple.integration.platform.router.SimpleRouter?level=INFO");
        //log.info("ActiveMQ heartbeat");
    }
}
