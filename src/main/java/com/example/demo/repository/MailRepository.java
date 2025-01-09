package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public interface MailRepository extends JpaRepository<Users, Integer> {
    Users findFirstByEmailIsNotNull();  // 最初に見つかるメールアドレスを取得
}
