package com.example.jft_pr16.aspects;

import com.example.jft_pr16.service.EmailService;
import jakarta.annotation.PostConstruct;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmailAspect { //!
    private final ApplicationContext applicationContext;
    private EmailService emailService;

    @Autowired
    public EmailAspect(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void setEmailService() {
        this.emailService = applicationContext.getBean(EmailService.class);
    }

    @Before("saveServiceMethods()")
    public void emailParameters(JoinPoint joinPoint) {
        emailService.sendNotification(joinPoint.getArgs()[0]);
    }

    @Pointcut("@annotation(com.example.jft_pr16.annotation.SendEmail)")
    public void saveServiceMethods() {}
}
