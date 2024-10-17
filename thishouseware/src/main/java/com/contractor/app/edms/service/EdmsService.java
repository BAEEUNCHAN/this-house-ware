package com.contractor.app.edms.service;

import java.util.List;

public interface EdmsService { // 전자결재
	
	// 결재문서함 전체조회
	public List<EdmsDocVO> edmsDocList();
	// 결재문서함 단건조회
	public EdmsDocVO edmsDocInfo(EdmsDocVO edmsDocVO);
	// 결재문서 등록
	public String edmsInsert(EdmsDocVO edmsDocVO);
	
	
	
	
	
	
	// 결재양식 전체조회
	public List<EdmsFormVO> edmsFormList();
	// 결재양식 단건조회
	public EdmsFormVO edmsFormInfo(EdmsFormVO edmsFormVO);
	
	
}
