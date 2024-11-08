package com.contractor.app.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.contractor.app.company.service.CompanysVO;

public interface CompanyMapper {
	
	// 회사정보 전체조회
	public List<CompanysVO> selectCompanyAll();
	
	// 회사정보 join 단건조회
	public List<CompanysVO> selectCompanyInfoList(CompanysVO companyVO);
	
	// 회사정보 등록
	public int insertCompanyInfo(CompanysVO companyVO);
	
	// 회사정보 삭제
	public int deleteCompany(int companyNo);
	
	// 회사이름 중복체크
	public boolean companyCheckName(@Param("companyName") String companyName);
}
