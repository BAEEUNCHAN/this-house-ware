package com.contractor.app.company.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ResultsVO {
	private Integer complainNo;		// 문의번호(FK)	, Not Null
	private Integer resultCost;		// 소모비용		
	private Integer resultPeople;	// 투입인원
	private Integer departmentNo;	// 담당부서		, Not Null
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date receiveDt;			// 글접수일시
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date completeDt;		// 해결완료일시
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date resultDt;			// 해결기간
	private String complainContent;	// 상황입력		, Not Null
	private String resultSolution;	// 해결방법		, Not Null
	private String resultFeedback;	// 피드백내용		, Not Null
	private Integer companyNo;		// 회사번호(FK)	, Not Null
}
