package com.simple.integration.platform.app;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.simple.integration.platform.config.ActiveMqConfig;
import com.simple.integration.platform.config.CamelConfig;
import com.simple.integration.platform.config.IntegrationPlatformConfig;

@SpringBootApplication
@Import({IntegrationPlatformConfig.class, CamelConfig.class, ActiveMqConfig.class})
public class IntegrationPlatformApp {

    private static Logger log = LoggerFactory.getLogger(IntegrationPlatformApp.class);
    
    public static void main(String[] args) {
        BrokerService broker = new BrokerService();
        try {
            // configure the broker
            broker.addConnector("tcp://localhost:61616");
         
            broker.start();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        
        ApplicationContext context = SpringApplication.run(new Class<?>[] {IntegrationPlatformApp.class}, args);
        log.info("Application context ID : " + context.getId());
    }
}
