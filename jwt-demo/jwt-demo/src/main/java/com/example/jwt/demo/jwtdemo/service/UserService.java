package com.example.jwt.demo.jwtdemo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt.demo.jwtdemo.config.JwtUtils;
import com.example.jwt.demo.jwtdemo.dto.UserDTO;
import com.example.jwt.demo.jwtdemo.entity.User;
import com.example.jwt.demo.jwtdemo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private JwtUtils util;

	public void signupUser(UserDTO dto) {
		convertDtoToEntity(dto);
	}

	public void convertDtoToEntity(UserDTO dto) {
		User user = User.builder().userName(dto.getUserName()).email(dto.getEmail()).password(dto.getPassword())
				.gender(dto.getGender()).build();
		repo.save(user);
	}

	public String loginUser(String email, String password) {
		User user = repo.findByEmail(email);
		if (Objects.nonNull(user)) {
			String result = user.getPassword().equals(password) ? util.generateToken(user) : "Invalid Password";
			if (result.length() > 20) {
				user.setIsActive(true);
				repo.save(user);
			}
			return result;
		}
		return "Invalid User";
	}
}
