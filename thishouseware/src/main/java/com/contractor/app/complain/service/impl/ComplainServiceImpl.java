package com.contractor.app.complain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.complain.mapper.ComplainMapper;
import com.contractor.app.complain.service.CompanysVO;
import com.contractor.app.complain.service.ComplainService;

@Service
public class ComplainServiceImpl implements ComplainService {
	private ComplainMapper complainMapper;
	
	@Autowired
	public ComplainServiceImpl(ComplainMapper complainMapper) {
		this.complainMapper = complainMapper;
	}
	
	@Override
	public List<CompanysVO> companyList() {
		return complainMapper.selectCompanyAll();
	}
}
