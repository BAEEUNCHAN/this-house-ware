package com.contractor.app.appr.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.appr.mapper.ApprMapper;
import com.contractor.app.appr.service.ApprFavoriteVO;
import com.contractor.app.appr.service.ApprLineVO;
import com.contractor.app.appr.service.ApprService;
import com.contractor.app.appr.service.ApprVO;


@Service
public class ApprServiceImpl implements ApprService {
	private ApprMapper apprMapper;

	@Autowired
	ApprServiceImpl(ApprMapper apprMapper) {
		this.apprMapper = apprMapper;
	}

	// 결재선 전체조회
	@Override
	public List<ApprLineVO> apprLineList() {
		return apprMapper.apprLineAllList();
	}
	// 결재선 추가
	@Override
	public int apprLineInsert(ApprLineVO apprLineVO) {
		return 0;
	}
	// 결재선 삭제

	// 결재선 즐겨찾기 전체조회
	@Override
	public List<ApprFavoriteVO> apprFavoriteList() {
		return apprMapper.apprFavoriteAllList();
	}
	// 즐겨찾기 추가
	@Override
	public int favoriteInsert(ApprFavoriteVO apprFavoriteVO) {
		return 0;
	}
	// 즐겨찾기 삭제
	
	// 결재자 등록 정보 전체조회
	@Override
	public List<ApprVO> apprList() {
		return apprMapper.apprAllList();
	}
	// 결재자 추가
	@Override
	public int apprInsert(ApprVO apprVO) {
		return 0;
	}
	// 결재자 삭제
}// 끝
