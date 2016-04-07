package com.simple.integration.platform.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //Access using http://localhost:8888/camel/hello
        from("servlet:///hello").startupOrder(2)
        .transform()
        .constant("Hello from Camel!");
        
        sampleRoute();
        
        //Comment out queueSenderRoute() method to use queue without Camel
        //and also use the same queue name (e.g. "jms.queue" for both sender and receiver)
        //, not separate like now : "jms.sender.queue" for "jmsMqSender" bean & "jms.receiver.queue" for "jmsMqReceiver" bean)
        queueSenderRoute();
        
        restletRouteProcessGet();
        
        restletRouteProcessPost();
        
        //heartbeat();
    }

    private void sampleRoute() {
        from("servlet:///sample")
        .transform()
        .constant("Sample of Camel");
    }
    
    private void queueSenderRoute() {
        from("activemq:queue:jms.sender.queue").setProperty("operation", simple("getCountryQueue"))
        .to("direct:com.simple.integration.platform.log");
    }
    
    private void restletRouteProcessGet() {
        from("restlet:http://0.0.0.0:7090/processGet?restletMethod=GET").setProperty("operation", simple("processGet")) //port 7090 is started automatically
        .to("direct:com.simple.integration.platform.log");
    }
    
    private void restletRouteProcessPost() {
        from("restlet:http://0.0.0.0:7090/processPost?restletMethod=POST").setProperty("operation", simple("processPostCountry")) //port 7090 is started automatically
        .to("direct:com.simple.integration.platform.log");
    }
    
    private void heartbeat() {
        from("timer://heartbeat?fixedRate=true&period=10s")
        .to("log:com.simple.integration.platform.router.SimpleRouter?level=INFO");
    }
}
