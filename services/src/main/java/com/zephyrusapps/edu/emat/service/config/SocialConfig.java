package com.zephyrusapps.edu.emat.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInAttempt;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;



import javax.inject.Inject;

/**
 * Spring Social Configuration.
 * Allows Greenhouse users to connect to SaaS providers such as Twitter and Facebook.
 * @author Keith Donald
 */
@Configuration
public class SocialConfig {

    @Inject
    private Environment environment;

    /**
     * The locator for SaaS provider connection factories.
     * When support for a new provider is added to Greenhouse, simply register the corresponding {@link ConnectionFactory} here.
     * The current Environment is used to lookup the credentials assigned to the Greenhouse application by each provider during application registration.
     * This bean is defined as a scoped-proxy so it can be serialized in support of {@link ProviderSignInAttempt provier sign-in attempts}.
     */
    @Bean
    @Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
    public SocialAuthenticationServiceLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new SocialAuthenticationServiceRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory( "637265156339597",  "7e395830085a784942913e47b5066a96"));
        return (SocialAuthenticationServiceLocator) registry;
    }

    /**
     * The shared store for users' connection information.
     * Uses a RDBMS-based store accessed with Spring's JdbcTemplate.
     * The returned repository encrypts the data using the configured {@link TextEncryptor}.
     */
    @Bean
    public UsersConnectionRepository usersConnectionRepository() {
        return new InMemoryUsersConnectionRepository(connectionFactoryLocator());
    }


    @Bean
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }


    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository() {
        return usersConnectionRepository().createConnectionRepository(getUserIdSource().getUserId());
    }



    @Bean
    public ConnectController connectController() {
        ConnectController controller = new ConnectController(connectionFactoryLocator(), connectionRepository());
        return controller;
    }



}