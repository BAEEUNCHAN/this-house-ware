package com.contractor.app.appr.mapper;

import java.util.List;

import com.contractor.app.appr.service.ApprFavoriteVO;
import com.contractor.app.appr.service.ApprLineVO;
import com.contractor.app.appr.service.ApprVO;

public interface ApprMapper {
	// 결재선 전체조회
	public List<ApprLineVO> apprLineAllList();

	// 결재선 즐겨찾기 전체조회
	public List<ApprFavoriteVO> apprFavoriteAllList();

	// 결재자 등록 정보 전체조회
	public List<ApprVO> apprAllList();

}
