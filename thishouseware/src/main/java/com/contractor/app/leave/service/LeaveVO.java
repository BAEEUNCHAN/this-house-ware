package com.contractor.app.leave.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LeaveVO { // 휴가신청서
	
private String leaveApplication; // 휴가신청서 번호 Primary Key
private String id; // 기안자 Foreign Key
private String departmentName; // 부서이름
private String position; // 직책
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date submitDt; // 기안일
private String description; // 설명
private String leaveType; // 휴가종류
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date leaveStartDt;// 휴가 시작일자
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date leaveEndDt; // 휴가 종료일자
private String leaveReason; // 휴가 사유
private String content; // 내용
private String discard; // 폐기
private String edmsFormNo; // 결재양식 번호


	
}
