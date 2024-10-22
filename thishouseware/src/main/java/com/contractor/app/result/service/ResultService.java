package com.contractor.app.result.service;

import java.util.List;

import com.contractor.app.company.service.ResultsVO;

public interface ResultService {

	// 처리 등록
	public int insertResult(ResultsVO resultVO);
	
	// result 리스트
	public List<ResultsVO> resultList();
	
	// result 단건
	public ResultsVO resultInfo(ResultsVO resultVO);
}
