package com.kj.formularzKontaktowy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {


    private final String ownerMail;
    private final JavaMailSender javaMailSender;

    public MailService(@Value("${spring.mail.username}")String ownerMail, JavaMailSender javaMailSender) {
        this.ownerMail = ownerMail;
        this.javaMailSender = javaMailSender;
    }


    public void sendMail(String senderName, String senderEmail, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setFrom(ownerMail);
            helper.setTo(ownerMail);
            helper.setReplyTo(senderEmail);
            helper.setSubject("Wiadomość od " + senderName + " z adresu " + senderEmail);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
