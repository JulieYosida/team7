package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.MailService;

@Controller
public class AllController {

    @Autowired
    private MailService mailService;

    @PostMapping("/submit3")  // ここを変更
    public String submitForm() {
        // 送信先と送信元を固定
        String to = "shitianzun28@gmail.com";  // 宛先（固定）
        String subject = "新しい申請が届きました";  // 件名
        String text = "新しい申請が届きました。内容をご確認ください。";  // 本文

        // メール送信
        mailService.sendMail(to, subject, text);

        
        System.out.println("メール送信が完了しました。");

        // 完了画面へ遷移
        return "complete";
    }
}
