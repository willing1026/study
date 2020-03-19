package com.example.jwt.domains.user.presentation;

import com.example.jwt.domains.user.domain.User;
import com.example.jwt.domains.user.service.SignInRequest;
import com.example.jwt.domains.user.service.SignInResponse;
import com.example.jwt.domains.user.service.SignUpRequest;
import com.example.jwt.domains.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public String signUp(@RequestBody SignUpRequest signUpRequest) {
		userService.signUp(signUpRequest);
		return "SignUp Ok";
	}

	@PostMapping("/signin")
	public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
		return userService.signIn(signInRequest);
	}

	@PostMapping("/userinfo")
	public User userInfo(@RequestBody String token) {
		return userService.userInfo(token);
	}

}
