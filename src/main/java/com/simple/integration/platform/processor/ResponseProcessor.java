package com.simple.integration.platform.processor;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("responseProcessor")
public class ResponseProcessor implements Processor {

    private static Logger log = LoggerFactory.getLogger(ResponseProcessor.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        
        String soapResponseIn = exchange.getIn().getBody(String.class);
        Map<String, String> jsonMap = mapper.readValue(soapResponseIn, new TypeReference<HashMap<String,String>>() {});
        
        jsonMap.put("status", "OK");
        String messageOut = mapper.writeValueAsString(jsonMap);
        log.info("Response: {}", messageOut);
        
        // Chain the request
        exchange.getOut().setBody(messageOut);
    }
}
