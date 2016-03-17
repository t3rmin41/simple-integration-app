package com.simple.integration.platform.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simple.integration.platform.router.ResponseRouterAdapter;
import com.simple.integration.platform.router.SimpleBusinessProcessRouter;
import com.simple.integration.platform.router.SimpleRouter;

@Configuration
public class CamelConfig {

    private static final String CAMEL_URL_MAPPING = "/camel/*";
    private static final String CAMEL_SERVLET_NAME = "CamelServlet";

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }

    @Bean
    public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception {
        SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
        //Adding routes to Camel context so that direct: will be visible and direct: routes (from,to) can be used
        camelContext.addRoutes(routeBuilder());
        camelContext.addRoutes(businessRouteBuilder());
        camelContext.addRoutes(responseRouterAdapter());
        return camelContext;
    }

    @Bean(name = "simpleRouter")
    public RouteBuilder routeBuilder() {
        return new SimpleRouter();
    }
    
    @Bean(name = "businessRouter")
    public RouteBuilder businessRouteBuilder() {
        return new SimpleBusinessProcessRouter();
    }
    
    @Bean(name = "responseRouterAdapter")
    public RouteBuilder responseRouterAdapter() {
        return new ResponseRouterAdapter();
    }
}
