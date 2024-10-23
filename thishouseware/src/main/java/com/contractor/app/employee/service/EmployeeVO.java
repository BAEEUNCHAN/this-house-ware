package com.contractor.app.employee.service;

import java.awt.Image;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmployeeVO {
	String id; // 직원 아이디
	String password; // 비밀번호
	String name; // 이름
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date hireDt; // 입사 날짜
	String phone; // 전화번호
	String email; // 이메일
	String imageLink; // 이미지 링크
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date resignDt; // 퇴사 날짜
	int departmentNo; // 부서 번호
	String positionCode; // 직급 코드
	Image dSignature; // 전자 서명
	
	// DepartmentVO
	String departmentName; // 부서 이름
	
	// AuthenticationVO
	String authenticationsValue;
	
	// 직급 이름
	String positionName; // 포지션 이름
}
