package com.csci5308.stocki5.Email;

import com.csci5308.stocki5.config.Stocki5AppEmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class Email implements EmailInterface{
    @Autowired
    Stocki5AppEmailConfig configEmail;

    @Override
    public boolean sendEmail(String toEmail, String subject, String content) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toEmail);
        email.setText(content);
        email.setSubject(subject);

        JavaMailSender mailSender = configEmail.configureJavaMail();
        mailSender.send(email);
        return true;
    }
}
