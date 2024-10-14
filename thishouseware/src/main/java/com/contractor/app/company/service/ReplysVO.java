package com.contractor.app.company.service;

import java.util.Date;

import lombok.Data;

@Data
public class ReplysVO {
	private Integer replyNo;	  // 문의답변번호(PK)		, Not Null
	private String replyContent;  // 답변내용				, Not Null
	private Date replyDt;		  // 답변날짜				, Default sysdate
	private Integer complainNO;	  // 문의번호(FK)			, Not Null
	private String id;			  // 직원아이디(FK)			, Not Null
}
