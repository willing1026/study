package com.security.practice.form;

import com.security.practice.account.Account;
import com.security.practice.account.AccountContext;
import com.security.practice.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.plaf.SeparatorUI;
import java.util.Collection;

@Service
public class SampleService {
	public void dashboard() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		Collection<? extends GrantedAuthority> credentials = authentication.getAuthorities();
		boolean authenticated = authentication.isAuthenticated();
	}

	public void dashboard2() {
		Account account = AccountContext.getAccount();
		System.out.println("==============================");
		System.out.println(account.toString());
	}

	@Async
	public void asyncService() {
		SecurityLogger.log("AsyncService");
		System.out.println("asyncService is Called");
	}
}
