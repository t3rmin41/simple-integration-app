package com.simple.integration.platform.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component("responseProcessor")
public class ResponseProcessor implements Processor {

    private static Logger log = LoggerFactory.getLogger(ResponseProcessor.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        ObjectNode root = mapper.createObjectNode();
        ObjectNode response = mapper.createObjectNode();
        response.put("status", "OK");
        //TODO FIXME
        root.set("response", response); // put("response", response);
        String ret = mapper.writeValueAsString(root);
        log.info("Response: {}", ret);
        exchange.getOut().setBody(ret);
    }
}
