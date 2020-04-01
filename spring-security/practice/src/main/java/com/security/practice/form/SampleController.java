package com.security.practice.form;

import com.security.practice.account.AccountContext;
import com.security.practice.account.AccountRepository;
import com.security.practice.common.SecurityLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Slf4j
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

//		AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
//		sampleService.dashboard2(); //thread local을 이용한 account정보 확인
		sampleService.dashboard();
		return "dashboard";
	}

	@GetMapping("/admin")
	public String admin(Model model, Principal principal) {
		model.addAttribute("message", "Hello Admin " + principal.getName());
		return "admin";
	}

	@GetMapping("/user")
	public String user(Model model, Principal principal) {
		model.addAttribute("message", "Hello User " + principal.getName());
		return "admin";
	}

	@GetMapping("/async-hanlder")
	@ResponseBody
	public Callable<String> asyncHanlder() {
		SecurityLogger.log("MVC");

		return () -> {
				SecurityLogger.log("Callable");
				return "Async Handler";
		};
	}

	@GetMapping("/async-service")
	@ResponseBody
	public String asyncService() {
		SecurityLogger.log("before async service");
		sampleService.asyncService();
		SecurityLogger.log("after async service");
		return "Async Service";
	}
}
