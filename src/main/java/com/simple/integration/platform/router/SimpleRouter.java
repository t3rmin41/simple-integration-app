package com.simple.integration.platform.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //Access us using http://localhost:8888/camel/hello
        //from("servlet:///hello").transform().constant("Hello from Camel!");
        helloRoute();
        
        sampleRoute();
        
        //Trigger run right after startup. No Servlet request required.
        from("timer://foo?fixedRate=true&period=10s").log("Camel timer triggered.");
    }

    public void helloRoute() {
        from("servlet:///hello").transform().constant("Hello from Camel!");
    }
    
    public void sampleRoute() {
        from("servlet:///sample").transform().constant("Sample of Camel");
    }
}
