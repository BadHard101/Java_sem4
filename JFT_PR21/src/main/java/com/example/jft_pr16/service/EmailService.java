package com.example.jft_pr16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class EmailService {
    public static final String EMAIL = "BadHard101@yandex.ru";
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSenderImpl javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendNotification(Object o) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(EMAIL);
        mailMessage.setFrom(EMAIL);
        mailMessage.setSubject(
                "Object " + o.getClass().getSimpleName() + " time " +
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now())
        );
        mailMessage.setText(o.toString());
        javaMailSender.send(mailMessage);
    }
}
