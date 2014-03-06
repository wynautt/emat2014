package com.zephyrusapps.edu.emat.service.config;

import com.zephyrusapps.edu.emat.service.auth.user.RepositoryUserDetailsService;
import com.zephyrusapps.edu.emat.service.auth.user.SimpleSocialUserDetailsService;
import com.zephyrusapps.edu.emat.service.auth.user.UserRepository;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.*;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityContext extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                //Spring Security ignores request to static resources such as CSS or JS files.
                .ignoring()
                .antMatchers(
                        //"/static/**",
                        "/_ah/**"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Configures form login
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/authenticate")
                .failureUrl("/login?error=bad_credentials")
                        //Configures the logout function
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                        //Configures url based authorization
                .and()
                .authorizeRequests()
                        //Anyone can access the urls
                .antMatchers(
                        "/auth/**",
                        "/login",
                        "/signin/**",
                        "/signup/**",
                        "/user/register/**"
                ).permitAll()
                //The rest of the our application is protected.
                .antMatchers("/**").hasRole("USER");
                //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
                //.and()
                //.apply(new SpringSocialConfigurer())
                //.and()
                //.setSharedObject(ApplicationContext.class, context);

        http
                .apply(new SpringSocialConfigurer());
                /*.and()
                .setSharedObject(ApplicationContext.class, context);*/


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SimpleSocialUserDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new RepositoryUserDetailsService(userRepository);
    }

}
