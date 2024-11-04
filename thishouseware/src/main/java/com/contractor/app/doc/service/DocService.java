package com.contractor.app.doc.service;

import java.util.List;
import java.util.Map;

public interface DocService {
	//문서함 문서 전체조회(메인화면)
	public List<DocJoinVO> DocJoinList(String id);
	// 문서결과별 문서 조회	
	public List<DocJoinVO> docApprovalStatusList(String approvalStatus, String userId);
	
	// 부서별 문서 전체조회
	public List<DocJoinVO> DocDeptList(int departmentNo);
	// 부서문서 결재완료 문서 조회	
	public List<DocJoinVO> docDeptStatusList(String approvalStatus, int departmentNo);
	// 부서문서 중요문서 업데이트
	public void docUpdateImportant(String edmsDocNo, String important);
	// 부서문서 중요문서
	public List<DocJoinVO> docDeptImportantList(String important, int departmentNo);
	
}
