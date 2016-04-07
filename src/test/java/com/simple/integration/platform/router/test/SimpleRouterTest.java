package com.simple.integration.platform.router.test;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.simple.integration.platform.app.IntegrationPlatformApp;
import com.simple.integration.platform.processor.ResponseProcessor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {IntegrationPlatformApp.class})
@WebAppConfiguration
public class SimpleRouterTest extends CamelTestSupport {

    @Autowired
    private ResponseProcessor responseProcessor;
    
    @Test
    public void testGetCountryInfoWithRestlet() throws Exception {
        String messageBody = "Poland";
        
        template.sendBody("restlet:http://127.0.0.1:7090/processPost?restletMethod=POST", messageBody);
        Thread.sleep(1000);
        
        assertEquals("{\"response\":\"Poland38186860WarsawPLN\",\"status\":\"OK\"}", responseProcessor.getResponse());
    }
    
    //ActiveMQ must be launched - embedded doesn't work
    @Test
    public void testGetCountryInfoWithQueue() throws Exception {
        String messageBody = "Spain";
        
        template.sendBody("activemq:queue:jms.sender.queue", messageBody);
        Thread.sleep(1000);
        
        assertEquals("{\"response\":\"Spain46704314MadridEUR\",\"status\":\"OK\"}", responseProcessor.getResponse());
    }
}
