package com.example.jwt.demo.jwtdemo.controller;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.demo.jwtdemo.config.JwtUtils;
import com.example.jwt.demo.jwtdemo.dto.UserDTO;
import com.example.jwt.demo.jwtdemo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private JwtUtils util;

	@PostMapping("/Signup")
	public ResponseEntity<String> SignUser(@RequestBody UserDTO dto) {
		service.signupUser(dto);
		return new ResponseEntity<String>("User Registered Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
		return service.loginUser(email, password);
	}

	@GetMapping("/demo")
	public String demoUser(@RequestHeader(value = "authorization", defaultValue = "") String auth)
			throws AccessDeniedException {
//		util.verifyToken(auth);
		return "Byee from Java";
	}

	@GetMapping("/hello")
	public String helloUser() {
		return "Hello User";
	}
}
