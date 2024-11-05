package com.contractor.app.appr.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ApprService {
	
	// 결재선 전체조회
	public List<ApprLineVO> apprLineList();
	// 결재선 단건조회
	public ApprLineVO apprLineInfo(ApprLineVO apprLineVO);
	// 결재선 추가
	public int apprLineInsert(ApprLineVO apprLineVO);
	// 결재선 삭제
	public Map<String, Object> apprLineDelete(int approvalLineNo);
	// 결재선 및 관련 레코드 삭제 메서드
	public void deleteApprovalLineAndRelatedRecords(Integer approvalLineNo);
	// 결재선 수정
	public Map<String, Object> apprLineUpdate(ApprLineVO apprLineVO);
	
	// 결재선 즐겨찾기 업데이트
	public boolean apprLineFavorUpdate(ApprLineVO apprLineVO);
		
	
	// 결재선 즐겨찾기 전체조회
	public List<ApprFavoriteVO> apprFavoriteList();
    // 즐겨찾기 존재 확인
    boolean ifFavorite(int approvalLineNo, String id);
    // 즐겨찾기 업데이트
    void favoriteUpdate(ApprFavoriteVO favorite);
    // 즐겨찾기 추가
    void insertFavorite(ApprFavoriteVO favorite);
	// 즐겨찾기 삭제
	public void favoriteDelete(int approvalLineNo, String id);
	// 즐겨찾기 이름을 결재선 이름으로 가져오기
	public String getApprovalLineName(@Param("approvalLineNo") int approvalLineNo);
	
	// 즐겨찾기 번호 가져오기
	public int getFavoriteNo(ApprFavoriteVO favorite);
	
	
	// 결재자 등록 정보 전체조회
	public List<ApproverVO> apprList(int approvalLineNo);
	// 결재자 단건조회
	public ApprVO apprInfo(ApprVO apprVO);
	// 결재자 추가
	public int apprInsert(ApprVO apprVO);
	// 결재자 삭제
	public Map<String, Object> apprDelete(int approverNo);
	// 결재자 수정
	public Map<String, Object> apprUpdate(ApprVO apprVO);
	// 결재자 순서 변경
	public void updateApprovalOrder(String approverNo, Integer approvalOrder);
	
	// 결재선의 결재자 삭제
	public boolean apprRemove(int approvalLineNo, String id); 
	
}
