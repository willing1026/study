package com.security.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorize - 인가.
        http.authorizeRequests()
                .mvcMatchers("/", "/info", "/account/**").permitAll()
                .mvcMatchers("admin").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.formLogin();
        http.httpBasic();

        http.exceptionHandling()
//                .accessDeniedPage("/access-denied");
                //뭔가 더 많은 처리를 하고 싶으면 "accessDeniedHandler" 로 처리
                //별도 클래스로 분리하는게 좋다.
            .accessDeniedHandler(new AccessDeniedHandler() {
                @Override
                public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username = principal.getUsername();
                    System.out.println(username + "is denied to access " + request.getRequestURI() );
                    response.sendRedirect("/access-denied");
                }
            });

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
