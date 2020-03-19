package com.example.jwt.common.config;

import com.example.jwt.common.config.interceptor.JwtAuthInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	private String[] INTERCEPTOR_WHITE_LIST = {
			"/signup/**",
			"/signin/**"
	};

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtAuthInterceptor())
				.addPathPatterns("/*")
				.excludePathPatterns(INTERCEPTOR_WHITE_LIST);
	}
}
