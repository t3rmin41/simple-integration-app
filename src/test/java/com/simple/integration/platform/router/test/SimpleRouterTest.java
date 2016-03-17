package com.simple.integration.platform.router.test;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simple.integration.platform.app.IntegrationPlatformApp;
import com.simple.integration.platform.router.SimpleRouter;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationPlatformApp.class)
public class SimpleRouterTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new SimpleRouter() {
            @Override
            public void configure() throws Exception {
                from("activemq:jms.queue").to("mock:test");
            }
        };
    }
    
    @Test
    public void camelTest() throws InterruptedException {
        MockEndpoint test = getMockEndpoint("mock:test");
        test.expectedBodiesReceived("Testing Endpoint");
        template.sendBody("activemq:jms.queue", "Testing Endpoint");
        test.assertIsSatisfied();
    }
}
