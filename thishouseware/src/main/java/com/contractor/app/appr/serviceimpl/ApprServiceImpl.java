package com.contractor.app.appr.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.appr.mapper.ApprMapper;
import com.contractor.app.appr.service.ApprFavoriteVO;
import com.contractor.app.appr.service.ApprLineVO;
import com.contractor.app.appr.service.ApprService;
import com.contractor.app.appr.service.ApprVO;
import com.contractor.app.appr.service.ApproverVO;

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
	// 결재선 단건조회
	@Override
	public ApprLineVO apprLineInfo(ApprLineVO apprLineVO) {
		return apprMapper.apprLineInfo(apprLineVO);
	}
	// 결재선 추가
	@Override
	public int apprLineInsert(ApprLineVO apprLineVO) {
		int result = apprMapper.insertApprLine(apprLineVO);
		return result == 1 ? apprLineVO.getApprovalLineNo() : -1;
	}

	// 결재선 삭제
	@Override
	public Map<String, Object> apprLineDelete(int approvalLineNo) {
		Map<String, Object> map = new HashMap<>();
		int result = apprMapper.deleteApprLine(approvalLineNo);

		if (result == 1) {
			map.put("approvalLineNo", approvalLineNo);
		}
		return map;
	}

	// 결재선 즐겨찾기 전체조회
	@Override
	public List<ApprFavoriteVO> apprFavoriteList() {
		return apprMapper.apprFavoriteAllList();
	}
	// 즐겨찾기 단건조회
	@Override
	public ApprFavoriteVO favoriteInfo(ApprFavoriteVO apprFavoriteVO) {
		return apprMapper.favoriteInfo(apprFavoriteVO);
	}
	// 즐겨찾기 추가
	@Override
	public int favoriteInsert(ApprFavoriteVO apprFavoriteVO) {
		int result = apprMapper.insertFavorite(apprFavoriteVO);
		return result == 1 ? apprFavoriteVO.getFavoriteNo() : -1;
	}

	// 즐겨찾기 삭제
	@Override
	public Map<String, Object> favoriteDelete(int favoriteNo) {
		Map<String, Object> map = new HashMap<>();
		int result = apprMapper.deleteFavorite(favoriteNo);

		if (result == 1) {
			map.put("favoriteNo", favoriteNo);
		}
		return map;
	}

	// 결재자 등록 정보 전체조회
	@Override
	public List<ApproverVO> apprList(int approvalLineNo) {
		return apprMapper.apprAllList(approvalLineNo);
	}
	// 결재자 단건 조회
	@Override
	public ApprVO apprInfo(ApprVO apprVO) {
		return apprMapper.apprInfo(apprVO);
	}
	// 결재자 추가
	@Override
	public int apprInsert(ApprVO apprVO) {
		int result = apprMapper.insertAppr(apprVO);
		return result == 1 ? apprVO.getApproverNo() : -1;
	}

	// 결재자 삭제
	@Override
	public Map<String, Object> apprDelete(int approverNo) {
		Map<String, Object> map = new HashMap<>();
		int result = apprMapper.deleteAppr(approverNo);

		if (result == 1) {
			map.put("approverNo", approverNo);
		}
		return map;
	}
}// 끝
