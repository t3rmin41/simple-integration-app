package com.simple.integration.platform.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleBusinessProcessRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:com.simple.integration.platform.processor.businessProcessor").
        to("direct:com.simple.integration.platform.adapter.getResponse");
    }

}
