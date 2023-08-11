package com.dseagull.taskmanagement.shared.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${MAIL_USERNAME}")
    private String fromEmail;

    private final JavaMailSender javaMail;

    @Async
    public void sendEmail(
            String toEmail,
            String subject,
            String message
    ) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom(fromEmail);

        javaMail.send(mailMessage);
    }
}
