package com.contractor.app.security.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.contractor.app.security.handler.LoginSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
	
	private final LoginSuccessHandler loginSuccessHandler;

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
					// 그외에도 허가하고 싶은 경로들
					.requestMatchers("/flutter/**").permitAll()// 플러터 연계 파트
					// 고객 관리 파트
					.requestMatchers("/complain/complainList",
							"/complain/complainInfo",
							"/complain/insertComplain",
							"/complain/complainUpdate",
							"/complain/complainDelete",
							"/complainPwdCheck").permitAll()
					.requestMatchers("/assets/**","/park/**","/templates/**").permitAll() // 정적파일 경로허가
					// 메니저 기능은 사장과 관리자만 들어갈 수 있게한다.
					.requestMatchers("/manager/**").hasAnyAuthority("a1","a2")
					// 직원 리스트 관련 조회는 팀장 이상급만 접근하게 한다.(부서별 제한은 자바 Util 클래스가 담당)
					.requestMatchers("/attendance/emps").hasAnyAuthority("a1","a2","a3","a4")
					.anyRequest().authenticated()
			);
		http
			.formLogin(formlogin-> formlogin
					.loginPage("/login") // 로그인 페이지 지정
					.failureUrl("/login?login=failed") // 로그인 실패시 경로
					.successHandler(loginSuccessHandler) // 로그인 성공시 실행할 핸들러
					)
			.logout(logout -> logout.logoutSuccessUrl("/login")
					.invalidateHttpSession(true) 
					);
		// csrf 비활성화 (개발 동안만 사용)
		http
			.csrf(csrf-> csrf.disable());
		// 권한에 맞지 않는 경로를 사용할 경우
		http
			.exceptionHandling()
			.accessDeniedHandler(new MyAccessDeniedHandler());
		return http.build();
	}
	
	private class MyAccessDeniedHandler implements AccessDeniedHandler{

		@Override
		public void handle(HttpServletRequest request, HttpServletResponse response,
				AccessDeniedException accessDeniedException) throws IOException, ServletException {
			response.sendRedirect("/authorization-fail");
		}
	}
}
