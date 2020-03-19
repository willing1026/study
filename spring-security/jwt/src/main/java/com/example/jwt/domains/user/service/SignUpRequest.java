package com.example.jwt.domains.user.service;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
	private String email;
	private String password;
}
