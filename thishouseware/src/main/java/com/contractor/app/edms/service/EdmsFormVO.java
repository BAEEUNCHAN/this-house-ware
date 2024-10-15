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
	private String edmsFormNo; // 결재양식 번호 Primary Key
	private String edmsFormName; // 결재양식 이름
	private String edmsFormType; // 결재양식 분류
	private String edmsFormPath; // 결재양식 경로
	private String description; // 설명
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDt; // 생설일시
	

}
