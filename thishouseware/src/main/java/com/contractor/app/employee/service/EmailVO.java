package com.contractor.app.employee.service;

import lombok.Data;

@Data
public class EmailVO {
	public String to; // 수신자
	public String subject; // 메일 제목
	public String message; // 메일 내용
}
