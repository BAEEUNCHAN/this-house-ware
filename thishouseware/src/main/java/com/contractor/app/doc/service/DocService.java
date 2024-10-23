package com.contractor.app.doc.service;

import java.util.List;

public interface DocService {
	//문서함 문서 전체조회(메인화면)
	public List<DocJoinVO> DocJoinList();
	// 문서결과별 문서 조회	
	public List<DocJoinVO> docApprovalStatusList(String approvalStatus);
	
	// 부서별 문서 전체조회
	public List<DocJoinVO> DocDetfList();
	// 부서문서결과별 문서 조회	
	public List<DocJoinVO> docDeftStatusList(String approvalStatus, Integer departmentNo);

}
