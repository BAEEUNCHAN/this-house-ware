package com.contractor.app.appr.service;

import java.util.List;

public interface ApprService {
	
	// 결재선 전체조회
	public List<ApprLineVO> apprLineList();
	
	// 결재선 즐겨찾기 전체조회
	public List<ApprFavoriteVO> apprFavoriteList();
	
	// 결재자 등록 정보 전체조회
	public List<ApprVO> apprList();

}
