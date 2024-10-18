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
					.requestMatchers("/","/all").permitAll()
					.anyRequest().authenticated()
			);
		http
			.formLogin(formlogin-> formlogin.defaultSuccessUrl("/"))
			.logout(logout -> logout.logoutSuccessUrl("/"));
		// csrf 비활성화 (개발 동안만 사용)
		http
			.csrf(csrf-> csrf.disable());
		return http.build();
	}
}
