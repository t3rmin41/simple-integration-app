package com.simple.integration.platform.router.test;

import org.apache.activemq.broker.BrokerService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simple.integration.platform.app.IntegrationPlatformApp;
import com.simple.integration.platform.router.SimpleRouter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationPlatformApp.class)
public class SimpleRouterTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new SimpleRouter();
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        BrokerService brokerSvc = new BrokerService();
        brokerSvc.setBrokerName("TestBroker");
        brokerSvc.addConnector("tcp://localhost:61616");
        brokerSvc.start();
    }

    @Test
    public void camelTest() throws InterruptedException {
        template.sendBody("activemq:jms.queue", "Testing Endpoint");
    }

}
