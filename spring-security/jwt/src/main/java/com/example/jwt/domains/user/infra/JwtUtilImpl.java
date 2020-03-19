package com.example.jwt.domains.user.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.domains.user.service.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
public class JwtUtilImpl implements JwtUtil {
	private static final String TEST_SIGN_KEY = "testKey";
	private Date EXPIRED_TIME = new Date(System.currentTimeMillis() + 1_000 * 10);
	private String ISSUER = "ADMIN";

	@Override
	public String createToken() {
		return JWT.create()
				.withIssuer(ISSUER)
				.withExpiresAt(EXPIRED_TIME)
				.sign(Algorithm.HMAC256(TEST_SIGN_KEY));
	}

	@Override
	public void verifyToken(String givenToken) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TEST_SIGN_KEY))
				.withIssuer(ISSUER)
				.build();

		verifier.verify(givenToken);
	}
}
