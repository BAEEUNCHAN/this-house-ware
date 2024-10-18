package com.contractor.app.company.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanysVO {
	private Integer companyNo; 	  // 회사번호(PK)		, Not Null
	private String companyName;   // 회사명			, Not Null
	
	// JOIN용
	private String customerName;
	private String phone;
	
}
