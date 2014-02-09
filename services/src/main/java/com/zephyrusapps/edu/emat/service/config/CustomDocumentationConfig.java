package com.zephyrusapps.edu.emat.service.config;

import com.mangofactory.swagger.EndpointComparator;
import com.mangofactory.swagger.OperationComparator;
import com.mangofactory.swagger.configuration.DocumentationConfig;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Properties;

@Configuration
@Import(DocumentationConfig.class)
public class CustomDocumentationConfig {

    @Bean
    public static PropertyPlaceholderConfigurer swaggerProperties() {

        // Swagger expects these to property values to be replaced. We don't want to propagate these to consumers of
        // this configuration, so we derive reasonable defaults here and configure the properties programmatically.
        Properties properties = new Properties();
        properties.setProperty("documentation.services.basePath", "http://localhost:8080/api/");
        // this property will be overridden at runtime, so the value here doesn't matter
        properties.setProperty("documentation.services.version", "1.0");

        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setProperties(properties);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}