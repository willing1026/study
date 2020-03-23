package com.example.jwt.domains.user.service;

import com.example.jwt.domains.user.domain.User;
import com.example.jwt.domains.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	public void signUp(SignUpRequest signUpRequest) {
		verifyDuplicateUser(signUpRequest.getEmail());

		User user = User.builder()
				.email(signUpRequest.getEmail())
				.password(signUpRequest.getPassword())
				.token(jwtUtil.createToken())
				.build();

		User result = userRepository.save(user);
		log.info("signUpUser = {}", result);
	}

	private void verifyDuplicateUser(String email) {
		if (userRepository.exists(Example.of(User.builder().email(email).build()))) {
			throw new IllegalArgumentException("중복된 유저입니다.");
		}
	}

	public SignInResponse signIn(SignInRequest signInRequest) {
		User findUser = userRepository.findByEmail(signInRequest.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다"));

		if (!findUser.getPassword().equals(signInRequest.getPassword())) {
			throw new IllegalArgumentException("패스워드 불일치");
		}

		jwtUtil.verifyToken(findUser.getToken());

		return SignInResponse.builder()
				.token(findUser.getToken())
				.build();
	}

	public User userInfo(String token) {
		return userRepository.findByToken(token).orElseThrow(() -> new IllegalArgumentException("일치하는 사용자가 없습니다."));
	}
}
