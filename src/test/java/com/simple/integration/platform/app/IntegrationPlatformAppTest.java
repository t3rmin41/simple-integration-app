package com.simple.integration.platform.app;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {IntegrationPlatformApp.class})
@WebAppConfiguration
public class IntegrationPlatformAppTest extends CamelTestSupport {

    @Test
    public void contextLoads() {
    }
}
