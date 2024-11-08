package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserRepository userrepository;
    
    @Autowired
    private UserService userservice;

    // ユーザを作成するためのPOSTリクエスト（JSON形式を受け取る）
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userservice.createUser(user.getEmail(), user.getPassword(), user.getName());
        return ResponseEntity.ok("User created successfully");
    }

    
    @RequestMapping(path = "/administrator", method = RequestMethod.GET)
	public String showAttendance(Model model) {
		List<User> user = userrepository.findAll();
		model.addAttribute("user", user);
		return "administrator";
	}

    // 特定のユーザをIDで取得するためのGETリクエスト
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userservice.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
