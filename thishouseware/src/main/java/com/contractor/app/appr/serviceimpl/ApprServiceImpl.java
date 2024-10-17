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

	// 결재선 즐겨찾기 전체조회
	@Override
	public List<ApprFavoriteVO> apprFavoriteList() {
		return apprMapper.apprFavoriteAllList();
	}

	// 결재자 등록 정보 전체조회
	@Override
	public List<ApprVO> apprList() {
		return apprMapper.apprAllList();
	}

}// 끝
