package com.contractor.app.schedule.mapper;

import java.util.List;
import java.util.Map;

import com.contractor.app.schedule.service.LeaveVO;

public interface LeaveMapper {
	// 휴가 전체 조회
	List<Map<String, Object>> listLeave();
	
	// 휴가 개인별 조회
	List<Map<String, Object>> selectLeave(String id);
	
	// 휴가 추가
	int insertLeave(LeaveVO leaveVO);
	
	// 휴가 삭제
	int deleteLeave(Integer no);
}
