package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    private Integer id;  // ユーザーID (主キー)

    @Column(nullable = false, unique = true)
    private String email;  // メールアドレス

    @Column(nullable = false)
    private String password;  // パスワード

    @Column(nullable = false)
    private String name;  // 名前
    
    @Column(nullable = false)
    private String data;  // データ

    public User() {}

    public User(Integer id, String email, String password, String name ,String data) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.data = data;
    }

 
    // ゲッターとセッターは@Dataで自動生成されます
}
