package com.example.jwt.common.config.interceptor;

import com.example.jwt.domains.user.domain.User;
import com.example.jwt.domains.user.domain.UserRepository;
import com.example.jwt.domains.user.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	private String HEADER_TOKEN_KEY = "token";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Long id = Long.parseLong(request.getHeader("id"));

		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다."));

		String givenToken = request.getHeader(HEADER_TOKEN_KEY);
		verifyToken(givenToken, user.getToken());

		return true;
	}

	private void verifyToken(String givenToken, String membersToken) {
		if (!givenToken.equals(membersToken)) {
			throw new IllegalArgumentException("사용자의 Token과 일치하지 않습니다.");
		}

		jwtUtil.verifyToken(givenToken);
	}

}
