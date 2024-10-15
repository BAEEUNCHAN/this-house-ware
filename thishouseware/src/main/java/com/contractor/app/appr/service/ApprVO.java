package com.contractor.app.appr.service;

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
public class ApprVO {
private	Integer appriverNo; // 결재자번호 Primary Key
private	String id; // 결재자
private	Integer apprivalOrder; // 결재 순서
private	String apprivalStatus; // 결재상태
@DateTimeFormat(pattern = "yyyy-MM-dd")
private	Date apprivalDt; // 결재일시
private	String rejectedReason; // 반려사유
private	String edmsDocNo; //결재문서 번호 Foreign Key
private	Integer apprivalLineNo; // 결재선 번호 Foreign Key


	


}
