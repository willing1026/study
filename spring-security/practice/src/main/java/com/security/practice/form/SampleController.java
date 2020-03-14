package com.security.practice.form;

import com.security.practice.account.AccountContext;
import com.security.practice.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class SampleController {

	@Autowired
	private SampleService sampleService;

	@Autowired
	private AccountRepository accountRepository;

	@GetMapping("/")
	public String index(Model model, Principal principal) {
		if (principal == null) {
			model.addAttribute("message", "Hello Spring Security");
		} else {
			model.addAttribute("message", "Hello " + principal.getName());
		}

		return "index";
	}

	@GetMapping("/info")
	public String info(Model model) {
		model.addAttribute("message", "Hello Spring Security");
		return "info";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("message", "Hello " + principal.getName());

		AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
		sampleService.dashboard2(); //thread local을 이용한 account정보 확인
//		sampleService.dashboard();
		return "dashboard";
	}

	@GetMapping("/admin")
	public String admin(Model model, Principal principal) {
		model.addAttribute("message", "Hello Admin " + principal.getName());
		return "admin";
	}
}
