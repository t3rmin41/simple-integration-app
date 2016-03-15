package com.simple.integration.platform.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.simple.integration.platform.jms.JmsReceiver;
import com.simple.integration.platform.jms.JmsSender;

@Component
public class SimpleRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //Access us using http://localhost:8888/camel/hello
        //from("servlet:///hello").transform().constant("Hello from Camel!");
        helloRoute();
        
        sampleRoute();
        
        queueRoute();
        
        //Trigger run right after startup. No Servlet request required.
        //from("timer://foo?fixedRate=true&period=10s").log("Camel timer triggered.");
    }

    public void helloRoute() {
        from("servlet:///hello").transform().constant("Hello from Camel!");
    }

    public void sampleRoute() {
        from("servlet:///sample").transform().constant("Sample of Camel");
    }
    
    public void queueRoute() {
        from("activemq:jms.queue").bean(JmsSender.class)
        .log("Sending message from jmsMqSender to jmsMqReceiver")
        .to("activemq:jms.queue").bean(JmsReceiver.class);
    }
}
