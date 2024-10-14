package com.contractor.app.company.service;

import java.util.Date;

import lombok.Data;

@Data
public class ComplainsVO {
	private Integer complainNo;		// 문의번호(PK)		, Not Null
	private Integer complainType;	// 불편구분			, Not Null
	private Date complainDt;		// 문제발생일시
	private String complainContent; // 상황입력			, Not Null
	private String complainRequest; // 요청사항			, Not Null
	private Date uploadDt;			// 등록일				, Default sysdate
	private Integer progress;		// 처리상황			, Not Null
	private Integer companyNO;		// 회사번호(FK)		, Not Null
}
