package com.security.practice.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//authorize - 인가.
		http.authorizeRequests()
				.mvcMatchers("/", "/info", "/account/**").permitAll()
				.mvcMatchers("/admin").hasRole("ADMIN")
				.mvcMatchers("/user").hasRole("USER")
				.anyRequest().authenticated()
				.accessDecisionManager(accessDecisionManager())
//                .expressionHandler() // accessDecisionManager 말고 expressionHandler를 커스터마이징 가능
		;
		http.formLogin();
		http.httpBasic();

		//현재 쓰레드에서 생성되는 하위 쓰레드에도 SpringSecurityContext를 공유.
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

	/**
	 * role hierarchy 를 지정할 수 있다.
	 */
	private AccessDecisionManager accessDecisionManager() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		handler.setRoleHierarchy(roleHierarchy);

		//voter list
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(handler);
		List<AccessDecisionVoter<? extends Object>> voters = Arrays.asList(webExpressionVoter);

		AccessDecisionManager accessDecisionManager = new AffirmativeBased(voters);
		return accessDecisionManager;
	}

    @Override
    public void configure(WebSecurity web) throws Exception {
	    //static resource 제외
        //정적자원인 경우 ignore를 통해 제외시키는게 좋고, 동적 페이지의 경우
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        /***
//         *  {noop} => spring security에서 사용하는 기본 인코딩 방식. prefix
//         *  noop -> 암호화를 하지 않았다는 의미 , 노 옵 (no op)
//         *  {~} 안에 패스워드 인코딩 방식을 넣어주면 됨
//         *
//         */
//        auth.inMemoryAuthentication()
//                .withUser("test").password("{noop}123").roles("USER").and()
//                .withUser("admin").password("{noop}!@#").roles("ADMIN");
//    }
}
