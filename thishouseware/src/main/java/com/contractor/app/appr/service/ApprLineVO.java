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
public class ApprLineVO {
	private Integer approvalLineNo; // 결재선 번호 Primary Key
	private String id; // 아이디 Foreign Key
	private String approvalLineName; // 결재선 이름 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDt; // 생성일시
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date resentUserDt; // 최근 사용일시
	private String referrer; // 참조자
	private String favoriteChk; // 즐찾여부

}
