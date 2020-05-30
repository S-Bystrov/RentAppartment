package com.bystrov.rent.service;

import com.bystrov.rent.domain.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    private static final Logger logger = Logger.getLogger(MailSender.class);

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender mailSender;

    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void send(User user, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText(message);


        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            e.printStackTrace();
            logger.warn("Email with activation code not sent to user :" + user.getUsername());
        }
    }
}
