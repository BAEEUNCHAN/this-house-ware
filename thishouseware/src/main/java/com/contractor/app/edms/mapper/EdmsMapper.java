package com.contractor.app.edms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.contractor.app.edms.service.EdmsDocVO;
import com.contractor.app.edms.service.EdmsFormVO;

public interface EdmsMapper {

	// 결재문서 전체조회
	public List<EdmsDocVO> selectDocAllList();
	// 결재문서 단건조회
	public EdmsDocVO selectEdmsDocInfo(EdmsDocVO edmsDocVO);
	// 결재문서 등록
	public int insertEdmsInfo(EdmsDocVO edmsDocVO);
	// 결재문서 임시저장
	public String insertSaveDoc(EdmsDocVO edmsDocVO);
	// 결재 승인 상태 업데이트 - currentApproverId, approvalOrder도 함께 업데이트
	public int updateDocumentApprovalStatus(EdmsDocVO edmsDocVO);
	// 결재 반려 상태 업데이트 - approvalOrder와 currentApproverId를 초기화
	public int updateDocumentRejectionStatus(EdmsDocVO edmsDocVO);
	// 결재 상태에 따라 문서 조회
	public List<EdmsDocVO> selectDocumentsByStatusAndUserId(@Param("approvalStatus") String approvalStatus,
			@Param("userId") String userId);
	// 파일명 업데이트
	public int updateDocumentFileName(EdmsDocVO edmsDocVO);
	// 다음 결재자 조회 (다음 결재자가 있으면 결재자 ID 반환, 없으면 null 반환)
	public String findNextApprover(@Param("edmsDocNo") String edmsDocNo, @Param("currentOrder") int currentOrder);

	// 결재양식 전체조회
	public List<EdmsFormVO> selectFormAllList();
	// 결재양식 단건조회
	public EdmsFormVO selectEdmsFormInfo(EdmsFormVO edmsFormVO);
}