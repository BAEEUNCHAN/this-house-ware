package com.contractor.app.edms.service;

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
public class EdmsFormVO { 
	private String edmsFormNo; // 결재양식 번호 Primary Keyn / not null
	private String edmsFormName; // 결재양식 이름 / not null
	private String edmsFormType; // 결재양식 분류
	private String edmsFormPath; // 결재양식파일(데이터로 저장되는)위치 경로
	private String description; // 설명
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDt; // 생성일시
	

}
