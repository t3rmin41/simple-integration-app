package com.simple.integration.platform.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.simple.integration.platform.controller", "com.simple.integration.platform.jms", "com.simple.integration.platform.processor"})
//NB: package com.simple.integration.platform.router is not included as all routers from this package are included in CamelConfig.java
//as beans and added to Camel context as routes
public class IntegrationPlatformConfig {

}
