package com.linkcheck.service;


import com.linkcheck.model.User;
import com.linkcheck.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.logging.Logger;

@Service
@Configuration
public class EmailService {
    private static final Logger logger = Logger.getLogger(EmailService.class.getName());
    private final UserRepository userRepository;
    private final UserService userService;

    public EmailService(UserRepository userRepository,UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void sendEmail(User user) {
        User foundUser = userRepository.findByEmail(user.getEmail());
        logger.info("founduser " + foundUser);
        if (foundUser != null) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("from mail");
            simpleMailMessage.setTo("to mail");
            simpleMailMessage.setSubject("Information e-mail: Link is full");
            simpleMailMessage.setText("Deneme maili");
            getMailSender().send(simpleMailMessage);
        }else{
            logger.info("User not found");
        }
    }

    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("username");
        mailSender.setPassword("password");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }



}
