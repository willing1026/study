package com.security.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PracticeApplication {
/**
 * document - https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/
 */

    /**
     * 이전버전의 SpringSecurity를 사용하면 평문으로 저장하는 경우가 있었다.
     * SpringSecurity 올리면 Encoding이 깨지는 문제가 생길 수 있어. 그래서 noOp 설정이 생김
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();  //deprecated,  no op을 없애주는 기능
//    }

    /**
     * spring security -> default password Encoding --> bcrypt 방식
     * 다양한 방식으로 적용 가능
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); //security5가 권장하는 방식
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        Map encoders = new HashMap();
//        encoders.put("scrypt", new SCryptPasswordEncoder());
//
//        return new DelegatingPasswordEncoder("scrypt", encoders);
//    }


    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }
}
