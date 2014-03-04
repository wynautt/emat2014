package com.zephyrusapps.edu.emat.service.config;

import com.zephyrusapps.edu.emat.service.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.Filter;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new GoogleAccountsAuthenticationEntryPoint();
    }

    @Bean
    public UserRegistry gaeDatastoreUserRegistry() {
        return new GaeDatastoreUserRegistry();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new GoogleAccountsAuthenticationProvider();
    }

    @Bean
    public Filter authenticationFilter() {
        return new GaeAuthenticationFilter();
    }

    @Bean(name="defaultAuthenticationManager")
    @Qualifier("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("a").password("a").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/_ah/**").permitAll()
                .antMatchers("/login/**").permitAll();
                //.antMatchers("/**").hasAuthority("USER");

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
            .and()
                .addFilterAfter(authenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .authenticationProvider(authenticationProvider())
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("dev_appserver_login", "JSESSIONID")
            .and()
                .csrf()
                    .disable();
    }


}