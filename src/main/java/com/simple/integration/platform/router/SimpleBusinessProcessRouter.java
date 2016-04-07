package com.simple.integration.platform.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleBusinessProcessRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:com.simple.integration.platform.log")
        .bean("logProcessor")
        .choice()
            .when(header("operation").isEqualTo("getCountryQueue"))//Logical route from queue message
                .to("direct:com.simple.integration.platform.adapter.getResponse")
                .to("activemq:queue:jms.receiver.queue")
            .when(header("operation").isEqualTo("processGet")) //from restlet GET
                .log("Request with empty body")
                .bean("responseProcessor")
            .when(header("operation").isEqualTo("processPostCountry")) //from restlet POST
                .to("direct:com.simple.integration.platform.adapter.getResponse")
            .otherwise() // undefined route
                .log("Respond with 'no route'")
                .bean("responseProcessor");
    }

}
