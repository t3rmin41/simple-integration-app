package com.simple.integration.platform.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //Access using http://localhost:8888/camel/hello
        from("servlet:///hello").
        transform().
        constant("Hello from Camel!");
        
        sampleRoute();
        
        queueRoute();
        
        //heartbeat();
    }

    public void sampleRoute() {
        from("servlet:///sample").
        transform().
        constant("Sample of Camel");
    }
    
    public void queueRoute() {
        from("activemq:jms.queue").
        to("log:com.simple.integration.platform.router.SimpleRouter?level=INFO").
        to("activemq:jms.queue");
    }
    
    private void heartbeat() {
        from("timer://heartbeat?fixedRate=true&period=10s").
        to("log:com.simple.integration.platform.router.SimpleRouter?level=INFO");
    }
}
