package com.simple.integration.platform.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.simple.integration.platform.controller", "com.simple.integration.platform.jms"})
public class IntegrationPlatformConfig {

}
