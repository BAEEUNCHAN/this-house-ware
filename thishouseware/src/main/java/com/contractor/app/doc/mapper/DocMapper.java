package com.contractor.app.doc.mapper;

import java.util.List;

import com.contractor.app.doc.service.DocJoinVO;

public interface DocMapper {

	// 개인문서함
	// 문서함 문서 개인전체조회
	public List<DocJoinVO> selectDocJoinList(String id);
	// 문서결과별 문서 조회
	public List<DocJoinVO> getApprovalStatus(String approvalStatus,String id);

	// 부서문서함
	// 문서함 문서 부서전체조회
	public List<DocJoinVO> selectDocDeptList(int departmentNo);
	// 부서문서 결재완료 조회
	public List<DocJoinVO> getDeptStatus(String approvalStatus, int departmentNo);
	// 부서문서 중요문서
	public List<DocJoinVO> getDeptImportant(String important, int departmentNo);
}