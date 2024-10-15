package com.contractor.app.company.service;

import java.util.List;

public interface CompanyService {
	
	// 회사(고객)정보 전체조회
	public List<CompanysVO> companyList();
	
	// 회사(고객)정보 등록
	public int insertCompany(CompanysVO companyVO);
}
