package com.contractor.app.schedule.service;

import lombok.Data;
@Data
public class ScheduleVO {	
	Integer scheduleNo;    	// 일정 번호	
	String start; 			// 시작일시
	String end;				// 종료일시
	String title;			// 제목
	String content;			// 내용
	String sheduleCode;		// 일정 코드
	Integer departmentNo;	// 부서 번호	
	String id;				// 아이디
	String bgColor;			// 일정 배경색상
}
