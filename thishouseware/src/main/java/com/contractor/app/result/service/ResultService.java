package com.contractor.app.result.service;

import java.util.List;
import java.util.Map;

import com.contractor.app.company.service.ResultsVO;

public interface ResultService {

	// 처리 등록
	public int insertResult(ResultsVO resultVO);
	
	// result 리스트
	public List<ResultsVO> resultList();
	public List<ResultsVO> getResultListAll();
	
	// result 단건
	public ResultsVO resultInfo(ResultsVO resultVO);
	
	// result 수정
	public Map<String, Object> updateResult(ResultsVO resultVO);
	
	// 삭제
	public int resultDelete(Integer complainNo);
}
