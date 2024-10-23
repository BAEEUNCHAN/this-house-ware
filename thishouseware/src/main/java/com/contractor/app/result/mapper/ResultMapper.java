package com.contractor.app.result.mapper;

import java.util.List;

import com.contractor.app.company.service.ResultsVO;

public interface ResultMapper {

	// resultList
	public List<ResultsVO> selectResultAll();
	
	// 상황처리 등록
	public int insertResult(ResultsVO resultVO);
	
	// result 단건
	public ResultsVO selectResultInfo(ResultsVO resultVO);
}