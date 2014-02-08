package com.zephyrusapps.edu.emat.service.config;

import com.zephyrusapps.edu.emat.service.services.IQuestionServices;
import com.zephyrusapps.edu.emat.service.services.QuestionServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    @Bean
    public IQuestionServices createQuestionServices() {
        return new QuestionServices();
    }
}
