package com.contractor.app.schedule.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class ScheduleVO {	
	Integer scheduleNo;    	// 일정 번호
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date startDt; 			// 시작일시
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date endDt;				// 종료일시
	String title;			// 제목
	String content;			// 내용
	String ScheduleCode;	// 일정 코드
	Integer DepartmentNo;	// 부서 번호	
	String id;				// 아이디
}
