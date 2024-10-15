package com.contractor.app.edms.mapper;

import java.util.List;

import com.contractor.app.edms.service.EdmsDocVO;
import com.contractor.app.edms.service.EdmsFormVO;

public interface EdmsMapper {
	// 결재문서 전체조회
	public List<EdmsDocVO> selectDocAllList();

	// 결재양식 전체조회
	public List<EdmsFormVO> selectFormAllList();
}