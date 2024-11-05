package com.contractor.app.edms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EdmsService { // 전자결재

	// 결재문서함 전체조회
	public List<EdmsDocVO> edmsDocList();
	// 결재문서함 단건조회
	public EdmsDocVO edmsDocInfo(EdmsDocVO edmsDocVO);
	// 결재문서 등록
	public String edmsInsert(EdmsDocVO edmsDocVO);
	// 결재문서 임시저장
	public String edmsInsertSave(EdmsDocVO edmsDocVO);
	// 결재 승인
	public String approveDocument(String edmsDocNo, String approvalStatus);
	// 결재 반려
	public String rejectDocument(String edmsDocNo, String reason);
	// 파일명 업데이트
	public void updateFileName(EdmsDocVO edmsDocVO);
	// 결재 상태별 문서조회
	public List<EdmsDocVO> getDocumentsByStatusAndUserId(String approvalStatus, String userId);
	// 결재 승인 상태 업데이트
	public void updateDocumentApprovalStatus(EdmsDocVO edmsDoc);
	// 결재 반려 상태 업데이트
	public void updateDocumentRejectionStatus(EdmsDocVO edmsDoc);

	
	
	// 결재양식 전체조회
	public List<EdmsFormVO> edmsFormList();
	// 결재양식 단건조회
	public EdmsFormVO edmsFormInfo(EdmsFormVO edmsFormVO);

}