package com.contractor.app.edms.service;

import java.util.List;

public interface EdmsService { // 전자결재
	
	// 결재 문서함 전체 출력
	public List<EdmsDocVO> edmsDocList();
	
	// 결재 양식 전체 출력
	public List<EdmsFormVO> edmsFormList();
}
