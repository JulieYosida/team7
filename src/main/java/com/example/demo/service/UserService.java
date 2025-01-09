package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ユーザを作成
    public void createUser(String email, String password, String name) {
        int randomId = generateUniqueRandomId();
        Users user = new Users(randomId, email, password, name);
        userRepository.save(user);
    }

    // すべてのユーザを取得
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // ユーザIDで検索
    public Users getUserById(int id) {
        Optional<Users> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // ランダムな5桁IDを生成する
    private int generateUniqueRandomId() {
        Random random = new Random();
        int randomId;
        do {
            randomId = 10000 + random.nextInt(90000);
        } while (userRepository.existsById(randomId));
        return randomId;
    }
    
}
