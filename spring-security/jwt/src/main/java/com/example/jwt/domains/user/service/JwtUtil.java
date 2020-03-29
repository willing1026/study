package com.example.jwt.domains.user.service;

public interface JwtUtil {
	String createToken();
	void verifyToken(String givenToken);
}
