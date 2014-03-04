package com.zephyrusapps.edu.emat.service.config;

import com.zephyrusapps.edu.emat.service.auth.user.GaeUserRepository;
import com.zephyrusapps.edu.emat.service.auth.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceContext {

    @Bean
    public UserRepository gaeUserRepository() {
        return new GaeUserRepository();
    }
}
