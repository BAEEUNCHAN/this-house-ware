package com.contractor.app.appr.mapper;

import java.util.List;

import com.contractor.app.appr.service.ApprFavoriteVO;
import com.contractor.app.appr.service.ApprLineVO;
import com.contractor.app.appr.service.ApprVO;

public interface ApprMapper {
	// 결재선 전체조회
	public List<ApprLineVO> apprLineAllList();
	// 결재선 단건조회
	public ApprLineVO apprLineInfo(ApprLineVO apprLineVO);
	// 결재선 추가                                                       
	public int insertApprLine(ApprLineVO apprLineVO);
	// 결재선 삭제
	public int deleteApprLine(int approvalLineNo);
	
	// 결재선 즐겨찾기 전체조회
	public List<ApprFavoriteVO> apprFavoriteAllList();
	// 즐겨찾기 단건조회
	public ApprFavoriteVO favoriteInfo(ApprFavoriteVO apprFavoriteVO);
	// 즐겨찾기 추가
	public int insertFavorite(ApprFavoriteVO apprFavoriteVO);
	// 즐겨찾기 삭제
	public int deleteFavorite(int favoriteNo);
	
	// 결재자 등록 정보 전체조회
	public List<ApprVO> apprAllList();
	// 결재자 단건조회
	public ApprVO apprInfo(ApprVO apprVO);
	// 결재자 추가 
	public int insertAppr(ApprVO apprVO);
	// 결재자 삭제
	public int deleteAppr(int approverNo);
}
