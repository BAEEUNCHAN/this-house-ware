package com.contractor.app.company.mapper;

import java.util.List;

import com.contractor.app.company.service.CompanysVO;

public interface CompanyMapper {
	
	// 회사(고객)정보 전체조회
	public List<CompanysVO> selectCompanyAll();
}
