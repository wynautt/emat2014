package com.zephyrusapps.edu.emat.service.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
//@ComponentScan(basePackages = {})
@Import({
        CoreConfig.class,
        CustomDocumentationConfig.class,
        WebAppContext.class,
        PersistenceContext.class,
        SocialContext.class,
        SecurityContext.class})
@PropertySource("classpath:application.properties")
public class EmatApplicationContext {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
