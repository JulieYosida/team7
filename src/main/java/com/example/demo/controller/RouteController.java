package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.LoginRepository;

@Controller

public class RouteController {

	@Autowired
	LoginRepository loginRepository;
	@RequestMapping(path = "/route", method = RequestMethod.GET)
	public String routeshow() {

		return "route";
	}
}