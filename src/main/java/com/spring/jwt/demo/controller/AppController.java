package com.spring.jwt.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@RequestMapping("/")
	public String test() {
		return "Hello";
	}

	@GetMapping("/user")
	public String getUser() {
		return "User";
	}

	@RequestMapping("/admin")
	public String getAdmin() {
		return "Admin";
	}

}
