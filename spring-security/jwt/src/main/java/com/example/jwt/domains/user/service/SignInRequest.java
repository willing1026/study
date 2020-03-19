package com.example.jwt.domains.user.service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {
	private String email;
	private String password;
}
