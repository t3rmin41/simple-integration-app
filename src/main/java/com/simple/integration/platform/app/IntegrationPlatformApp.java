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

//since IntegrationPlatformApp is placed in .app package (folder)
//which is not root relative to .config, .controller, .router etc
//need to explicitly scan packages. If package was com.simple.integration.platform and others would be
//subpackages of com.simple.integration.platform, e.g. com.simple.integration.platform.controller, com.simple.integration.platform.processor
//then config import is not needed and all Camel routers would be visible as @Component of subpackages and direct: can be used
//because Spring scans @Components recursively
@Import({IntegrationPlatformConfig.class, CamelConfig.class, ActiveMqConfig.class })
@SpringBootApplication
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
