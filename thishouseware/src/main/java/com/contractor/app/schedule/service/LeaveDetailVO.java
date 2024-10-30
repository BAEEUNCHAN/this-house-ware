package com.contractor.app.schedule.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LeaveDetailVO {
	String id; 				// 아이디
	Integer leaveTotalDays; // 휴가 총 일수
	Integer leaveUsedDays; // 휴가 사용 일수
	Integer leaveRemainDays; // 휴가 잔여 일수
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date leaveStartDt; // 사용 시작 일시
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date leaveEndDt; // 사용 종료 일시
}
