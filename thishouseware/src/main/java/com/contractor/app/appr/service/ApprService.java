package com.contractor.app.appr.service;

import java.util.List;

public interface ApprService {
	
	// 결재선 전체조회
	public List<ApprLineVO> apprLineList();
	// 결재선 추가
	public int apprLineInsert(ApprLineVO apprLineVO);
	// 결재선 삭제
	
	// 결재선 즐겨찾기 전체조회
	public List<ApprFavoriteVO> apprFavoriteList();
	// 즐겨찾기 추가
	public int favoriteInsert(ApprFavoriteVO apprFavoriteVO);
	// 즐겨찾기 삭제
	
	// 결재자 등록 정보 전체조회
	public List<ApprVO> apprList();
	// 결재자 추가
	public int apprInsert(ApprVO apprVO);
	// 결재자 삭제
	

}
