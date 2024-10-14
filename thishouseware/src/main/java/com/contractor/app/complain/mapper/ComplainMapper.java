package com.contractor.app.complain.mapper;

import java.util.List;

import com.contractor.app.complain.service.CompanysVO;

public interface ComplainMapper {
	
	// 회사(고객)정보 전체조회
	public List<CompanysVO> selectCompanyAll();
}
