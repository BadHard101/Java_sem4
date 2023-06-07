package com.example.jft_pr16.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableJpaRepositories("com.example.jft_pr16.repository")
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
public class DataConfig { //!
}
