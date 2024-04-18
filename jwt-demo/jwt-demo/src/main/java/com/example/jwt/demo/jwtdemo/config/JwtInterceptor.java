package com.example.jwt.demo.jwtdemo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private JwtUtils utils;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("I am starting from this method");

		if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signup"))) {
			utils.verifyToken(request.getHeader("authorization"));
		}
		return super.preHandle(request, response, handler);
	}
}
