package com.example.jwt.demo.jwtdemo.config;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.jwt.demo.jwtdemo.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	@Value("{jwt.secret.key}")
	private String secretKey;

	private final static Long JWT_EXPIRATION_TIME = (long) (5 * 60 * 60);

	public String generateToken(User user) {

		Claims claims = Jwts.claims().setId(user.getUserId().toString())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME * 1000));

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public void verifyToken(String auth) throws AccessDeniedException {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(auth).getBody();
		} catch (Exception e) {
			throw new AccessDeniedException("Access Denied");
		}
	}
}
