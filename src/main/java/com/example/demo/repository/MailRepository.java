package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface MailRepository extends JpaRepository<User, Integer> {
    User findFirstByEmailIsNotNull();  // 最初に見つかるメールアドレスを取得
}
