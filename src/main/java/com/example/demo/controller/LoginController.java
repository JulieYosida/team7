package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.User;
import com.example.demo.repository.LoginRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class LoginController {

	@Autowired
	LoginRepository loginRepository;
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String showLoginForm() {

		return "login";
	}
	
	
	

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String processLogin(String id, String password, Model model, HttpSession session) {

		model.addAttribute("id", id);
		model.addAttribute("password", password);
		session.setAttribute("id", id);
		session.setAttribute("password", password);
		
		User user = loginRepository.findById(Integer.parseInt(id)).orElse(null);
			
		if (user != null && user.getPassword().equals(password)) {
            return "redirect:/administrator";
        } else {
            model.addAttribute("message", "ログインに失敗しました");
            return "redirect:/login";
		}
	}
}
