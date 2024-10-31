package com.contractor.app.appr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.contractor.app.appr.service.ApprFavoriteVO;
import com.contractor.app.appr.service.ApprLineVO;
import com.contractor.app.appr.service.ApprVO;
import com.contractor.app.appr.service.ApproverVO;

public interface ApprMapper {
	// 결재선 전체조회
	public List<ApprLineVO> apprLineAllList();
	// 결재선 단건조회
	public ApprLineVO apprLineInfo(ApprLineVO apprLineVO);
	// 결재선 추가                                                       
	public int insertApprLine(ApprLineVO apprLineVO);
	// 결재선 삭제
	public int deleteApprLine(int approvalLineNo);
	// 결재선 수정
	public int updateApprLine(ApprLineVO apprLineVO);
	
	// 결재선 즐겨찾기 전체조회
	public List<ApprFavoriteVO> apprFavoriteAllList();
	// 즐겨찾기 존재 여부 확인 메서드
	int ifFavorite(Map<String, Object> params);
    // 즐겨찾기 업데이트
	public void updateFavorite(ApprFavoriteVO favorite);
    // 즐겨찾기 추가
	public void insertFavorite(ApprFavoriteVO favorite);
	// 즐겨찾기 삭제
	public int deleteFavorite(Map<String, Object> params);
	
		
	// 결재자 등록 정보 전체조회
	public List<ApproverVO> apprAllList(int approvalLineNo);
	// 결재자 단건조회
	public ApprVO apprInfo(ApprVO apprVO);
	// 결재자 추가 
	public int insertAppr(ApprVO apprVO);
	// 결재자 삭제
	public int deleteAppr(int approverNo);
	// 결재자 수정
	public int updateAppr(@Param("apprVO") ApprVO apprVO);
	
}
