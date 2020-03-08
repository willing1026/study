package com.security.practice.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AccountService accountService;

	@Test
	void index_anonymous() throws Exception {
		mockMvc.perform(get("/").with(anonymous()))
				.andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	@WithAnonymousUser
	void index_anonymous2() throws Exception {
		mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	void index_user() throws Exception {
		//이 계정으로 로그인한 상태라고 가정하고 진행   ( 이런 계정정보가 DB에 등록되어 있다---> X )
		mockMvc.perform(get("/").with(user("js").roles("USER")))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@WithUser
	void index_user2() throws Exception {
		//이 계정으로 로그인한 상태라고 가정하고 진행   ( 이런 계정정보가 DB에 등록되어 있다---> X )
		mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void admin_user() throws Exception {
		//이 계정으로 로그인한 상태라고 가정하고 진행   ( 이런 계정정보가 DB에 등록되어 있다---> X )
		mockMvc.perform(get("/admin").with(user("js").roles("USER")))
				.andDo(print())
				.andExpect(status().isForbidden());
	}

	@DisplayName("인증 확인")
	@Test
	@Transactional
	void login() throws Exception {
		final String username = "js";
		final String password = "1234";
		Account account = this.createUser(username, password);
		mockMvc.perform(formLogin().user(username).password(password))
				.andExpect(authenticated());

	}

	@Test
	@Transactional
	void login2() throws Exception {
		final String username = "js";
		final String password = "1234";
		Account account = this.createUser(username, password);
		mockMvc.perform(formLogin().user(username).password(password))
				.andExpect(authenticated());

	}

	private Account createUser(String username, String password) {
		Account account = new Account();
		account.setUsername("js");
		account.setPassword("1234");
		account.setRole("USER");
		return accountService.createAccount(account);
	}
}