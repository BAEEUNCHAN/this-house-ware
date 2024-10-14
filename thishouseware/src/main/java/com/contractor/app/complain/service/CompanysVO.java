package com.contractor.app.complain.service;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CompanysVO {
	private Integer companyNo; 	  // 회사번호(PK)		, Not Null
	private String companyName;   // 회사명			, Not Null
	private String customerName;  // 고객명			, Not Null
	private String phone; 		  // 연락처			, Not Null
}
