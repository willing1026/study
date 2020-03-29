package com.example.jwt.domains.user.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class SignInResponse {
	private String token;

	public SignInResponse(String token) {
		this.token = token;
	}
}
