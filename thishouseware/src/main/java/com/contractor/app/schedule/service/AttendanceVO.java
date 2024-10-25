package com.contractor.app.schedule.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class AttendanceVO {
	Integer attendancesNo;	// 근태 번호
	String attendancesCode;	// 근태 코드
	Integer departmentNo;	// 부서 번호
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date time;			// 입력시간
	int workingTime;			// 근무시간(분단위)
	String id;				// 아이디
}
