package com.example.jft_pr16.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.example.jft_pr16.repository")
@EnableAspectJAutoProxy
public class DataConfiguration {

}
