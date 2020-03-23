package com.security.practice.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SignUpControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void signUpForm() throws Exception {
		mockMvc.perform(get("/signup"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("_csrf")));
	}

	@Test
	void processSignUp() throws Exception {
		//csrf 토큰은 자동으로 들어가지 않아
		//위쪽 method에는 csrf토큰은 들어있으나, 과정이 분리되어 있어서 csrf 토큰이 이번 테스트에는 토큰이 없다.

		mockMvc.perform(post("/signup")
				.param("username", "js")
				.param("password", "1234")
				.with(csrf())) //csrf 토큰을 추가해주면 된다.
				.andDo(print())
				.andExpect(status().is3xxRedirection());
	}
}