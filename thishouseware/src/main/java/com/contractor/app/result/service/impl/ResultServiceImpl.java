package com.contractor.app.result.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.company.service.ResultsVO;
import com.contractor.app.result.mapper.ResultMapper;
import com.contractor.app.result.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {
	private ResultMapper resultMapper;
	
	@Autowired
	public ResultServiceImpl(ResultMapper resultMapper) {
		this.resultMapper = resultMapper;
	}
	
	// resultList
	@Override
	public List<ResultsVO> resultList() {
		return resultMapper.selectResultAll();
	}
	
	// result 단건
	@Override
	public ResultsVO resultInfo(ResultsVO resultVO) {
		return resultMapper.selectResultInfo(resultVO);
	}
	
	// 문의 보고처리
	@Override
	public int insertResult(ResultsVO resultVO) {
		int result = resultMapper.insertResult(resultVO);
		return result == 1 ? resultVO.getComplainNo() : -1;
	}
}
