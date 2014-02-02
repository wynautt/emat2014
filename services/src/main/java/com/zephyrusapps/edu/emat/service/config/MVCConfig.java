package com.zephyrusapps.edu.emat.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.zephyrusapps.edu.emat.service.rest.controller")
public class MVCConfig {
}
