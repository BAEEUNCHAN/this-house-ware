package com.contractor.app.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.company.mapper.CompanyMapper;
import com.contractor.app.company.service.CompanysVO;
import com.contractor.app.company.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	private CompanyMapper companyMapper;
	
	@Autowired
	public CompanyServiceImpl(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}
	
	@Override
	public List<CompanysVO> companyList() {
		return companyMapper.selectCompanyAll();
	}
	
	
	@Override
	public int insertCompany(CompanysVO companyVO) {
		int result = companyMapper.insertCompanyInfo(companyVO);
		return result == 1 ? companyVO.getCompanyNo() : -1;
	}
}
