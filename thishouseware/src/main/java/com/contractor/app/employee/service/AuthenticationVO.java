package com.contractor.app.employee.service;

import java.util.Date;

import lombok.Data;

@Data
public class AuthenticationVO {
	
	String id; // 직원 아이디
	String authenticationsValue; // 인증값
	Date authenticationsTime;// 인증값 발행 시간

}
