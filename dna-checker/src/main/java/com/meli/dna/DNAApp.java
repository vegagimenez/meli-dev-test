package com.meli.dna;

import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootApplication
public class DNAApp extends SpringBootServletInitializer{

	@Autowired
    private ICfgDNA config;
	
	public static void main(String[] args) {
		SpringApplication.run(DNAApp.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DNAApp.class);
    }
	
	@Bean
	public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	        
        	@Override
	        protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
	            tomcat.enableNaming();
	            return super.getTomcatWebServer(tomcat);
	        }
	         
	        @Override
            protected void postProcessContext(Context context) {
            	log.info("Loading ContextResource....");
                ContextResource resource = new ContextResource();
                resource.setName(config.getDatasourceJNDIName());
                resource.setType(DataSource.class.getName());
                resource.setProperty("driverClassName", config.getJdbcDriver());
                resource.setProperty("url", config.getJdbcUrl());
                resource.setProperty("username", config.getJdbcUser());
                resource.setProperty("password", config.getJdbcPass());
                context.getNamingResources().addResource(resource);
                log.info("ContextResource: Created JNDI object:" + config.getDatasourceJNDIName());
            }
        };
        //tomcat.addAdditionalTomcatConnectors(redirectConnector());
        return tomcat;
	}
}
