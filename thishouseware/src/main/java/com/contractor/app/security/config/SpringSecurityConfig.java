package com.contractor.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean// 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests(authorize 
					-> authorize
					.requestMatchers("/login","/login/**").permitAll() // 로그인 관련 요청은 허가한다. 로그아웃도 추가할것!
					.requestMatchers("/assets/**","/park/**","/templates/**").permitAll() // 정적파일 경로허가
					.anyRequest().authenticated()
			);
		http
			.formLogin(formlogin-> formlogin.defaultSuccessUrl("/")
					.loginPage("/login") // 로그인 페이지 지정
					.failureUrl("/login?login=failed") // 로그인 실패시 경로
					)
			.logout(logout -> logout.logoutSuccessUrl("/"));
		// csrf 비활성화 (개발 동안만 사용)
		http
			.csrf(csrf-> csrf.disable());
		return http.build();
	}
}
