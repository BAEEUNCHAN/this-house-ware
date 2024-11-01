package com.contractor.app.appr.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void deleteApprovalLineAndRelatedRecords(Integer approvalLineNo) {
		// APPROVER 테이블에서 approvalLineNo 삭제
		apprMapper.deleteApproversByLineNo(approvalLineNo);

		// APPROVAL_LINE 테이블에서 approvalLineNo 삭제
		apprMapper.deleteApprLine(approvalLineNo);
	}

	// 결재선 수정
	@Override
	public Map<String, Object> apprLineUpdate(ApprLineVO apprLineVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;

		// 기본값 설정: approvalLineName이 null일 경우 기본값으로 "기본결재라인명"을 사용
		if (apprLineVO.getApprovalLineName() == null || apprLineVO.getApprovalLineName().trim().isEmpty()) {
			apprLineVO.setApprovalLineName("기본결재라인명");
		}

		int result = apprMapper.updateApprLine(apprLineVO);

		if (result == 1) {
			isSuccessed = true;
		}

		map.put("result", isSuccessed);
		map.put("target", apprLineVO);
		return map;
	}

	// 결재선 즐겨찾기 전체조회
	@Override
	public List<ApprFavoriteVO> apprFavoriteList() {
		return apprMapper.apprFavoriteAllList();
	}

	// 즐겨찾기 존재 여부 확인
	public boolean ifFavorite(int approvalLineNo, String id) {
		Map<String, Object> params = new HashMap<>();
		params.put("approvalLineNo", approvalLineNo);
		params.put("id", id);
		int count = apprMapper.ifFavorite(params);
		return count > 0;
	}

	// 즐겨찾기 업데이트
	public void favoriteUpdate(ApprFavoriteVO favorite) {
		apprMapper.updateFavorite(favorite);
	}

	// 즐겨찾기 추가
	public void insertFavorite(ApprFavoriteVO favorite) {
		apprMapper.insertFavorite(favorite);
	}

	// 즐겨찾기 삭제
	@Override
	public void favoriteDelete(int approvalLineNo, String id) {
		Map<String, Object> params = new HashMap<>();
		params.put("approvalLineNo", approvalLineNo);
		params.put("id", id);
		apprMapper.deleteFavorite(params);
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

	// 결재자 수정
	@Override
	public Map<String, Object> apprUpdate(ApprVO apprVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;

		int result = apprMapper.updateAppr(apprVO);

		if (result == 1) {
			isSuccessed = true;
		}

		map.put("result", isSuccessed);
		map.put("target", apprVO);
		return map;
	}

	// 결재자 순서 변경
	@Override
	@Transactional
	public void updateApprovalOrder(String approverNo, Integer approvalOrder) {
		apprMapper.updateApprovalOrder(approverNo, approvalOrder);
	}

}// 끝
