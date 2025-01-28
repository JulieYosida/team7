package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);  // 宛先（固定）
            message.setSubject(subject);  // 件名
            message.setText(text);  // 本文

            javaMailSender.send(message);  // メール送信
            System.out.println("メールが送信されました。");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("メール送信に失敗しました。");
        }
    }
}
