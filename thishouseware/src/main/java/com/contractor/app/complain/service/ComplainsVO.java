package com.contractor.app.complain.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data 
public class ComplainsVO {

	private Integer complainNo;		// 문의번호(PK)		, Not Null
	private Integer complainType;	// 불편구분			, Not Null

	@DateTimeFormat(pattern = "yyyy-MM-dd")


	private Date complainDt;		// 문제발생일시
	private String complainTitle;   // 문의제목 			, Not Null
	private String complainContent; // 상황입력			, Not Null
	private String complainRequest; // 요청사항			, Not Null

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date uploadDt;			// 등록일				, Default sysdate
	private Integer progress;		// 처리상황			, Not Null
	private Integer companyNo;		// 회사번호(FK)		, Not Null
	private String customerName;  // 고객명			, Not Null
	private String phone; 		  // 연락처			, Not Null
	
	// JOIN용 추가
	private String companyName;
	

	

}

