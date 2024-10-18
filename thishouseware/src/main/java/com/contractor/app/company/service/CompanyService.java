package com.contractor.app.company.service;

import java.util.List;

public interface CompanyService {
	
	// 회사정보 전체조회
	public List<CompanysVO> companyList();
	
	// 회사정보+고객명+연락처 join 리스트
	public List<CompanysVO> companyInfoList(CompanysVO companyVO);
	
	// 회사정보 등록
	public int insertCompany(CompanysVO companyVO);
	
	// 회사정보 삭제
	public int deleteCompany(int companyNo);
}
