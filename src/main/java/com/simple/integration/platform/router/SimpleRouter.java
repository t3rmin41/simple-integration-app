package com.simple.integration.platform.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //Access using http://localhost:8888/camel/hello
        from("servlet:///hello").startupOrder(2).
        transform().
        constant("Hello from Camel!");
        
        sampleRoute();
        
        //Comment out queueRoute() method to use queue without Camel
        queueRoute();
        
        processRoute();
        
        //heartbeat();
    }

    private void sampleRoute() {
        from("servlet:///sample").startupOrder(3).
        transform().
        constant("Sample of Camel");
    }
    
    private void queueRoute() {
        from("activemq:jms.queue").startupOrder(4).
        to("log:com.simple.integration.platform.router.SimpleRouter?level=INFO").
        to("activemq:jms.queue");
    }
    
    private void processRoute() {
        from("restlet:http://0.0.0.0:7090/process?restletMethod=GET").startupOrder(1). //port 7090 is started automatically
        bean("logProcessor").
        to("direct:com.simple.integration.platform.processor.businessProcessor").
        bean("responseProcessor").
        log("Response:");
    }
    
    private void heartbeat() {
        from("timer://heartbeat?fixedRate=true&period=10s").startupOrder(5).
        to("log:com.simple.integration.platform.router.SimpleRouter?level=INFO");
    }
}
