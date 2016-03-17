package com.simple.integration.platform.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("logProcessor")
public class LogProcessor implements Processor {

    private static Logger log = LoggerFactory.getLogger(LogProcessor.class);
    
    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("Message body : " + exchange.getIn().getBody(String.class));

        // Chain the request
        exchange.getOut().setBody(exchange.getIn().getBody());
    }

}
