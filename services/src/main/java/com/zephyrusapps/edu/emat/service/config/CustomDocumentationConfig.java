package com.zephyrusapps.edu.emat.service.config;

import com.mangofactory.swagger.EndpointComparator;
import com.mangofactory.swagger.OperationComparator;
import com.mangofactory.swagger.configuration.DocumentationConfig;
import com.mangofactory.swagger.configuration.ExtensibilityModule;
import com.sun.accessibility.internal.resources.accessibility_en;
import com.zephyrusapps.edu.emat.service.config.swagger.EmatExtensibilityModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
//@Import(DocumentationConfig.class)
public class CustomDocumentationConfig extends DocumentationConfig {

    @Autowired
    @Bean
    public static PropertyPlaceholderConfigurer swaggerProperties(Environment env) {
        // Swagger expects these to property values to be replaced. We don't want to propagate these to consumers of
        // this configuration, so we derive reasonable defaults here and configure the properties programmatically.
        Properties properties = new Properties();
        String path = env.getProperty("swagger.documentation.services.basePath");
        String version = env.getProperty("swagger.documentation.services.version");

        properties.setProperty("documentation.services.basePath",
                path != null ? path : "http://emat2alpha.exames-matematica.appspot.com/api/");

        // this property will be overridden at runtime, so the value here doesn't matter
        properties.setProperty("documentation.services.version",
                version != null ? version : "1.0");

        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setProperties(properties);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

    @Override
    public ExtensibilityModule extensibilityModule() {
        return new EmatExtensibilityModule();
    }

}