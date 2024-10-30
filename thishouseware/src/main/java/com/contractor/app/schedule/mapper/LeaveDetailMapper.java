package com.contractor.app.schedule.mapper;

import java.util.List;

import com.contractor.app.schedule.service.LeaveDetailVO;

public interface LeaveDetailMapper {
	// 휴가 상세 전체 조회
	List<LeaveDetailVO> listLeaveDetail();
	
	// 휴가 상세 단건 조회
	LeaveDetailVO getLeaveDetail(String id);
	
	// 휴가 상세 추가
	int insertLeaveDetail(LeaveDetailVO leaveDetailVO);
	
	// 휴가 상세 수정
	int updateLeaveDetail(LeaveDetailVO leaveDetailVO);
	
	// 휴가 상세 삭제
	int deleteLeaveDetail(String id);
}
