package com.contractor.app.schedule.mapper;

import java.util.List;
import java.util.Map;

import com.contractor.app.schedule.service.LeavesVO;

public interface LeavesMapper {
	// 휴가 전체 조회
	List<Map<String, Object>> listLeave();
	
	// 휴가 추가
	int insertLeave(LeavesVO leaveVO);
	
	// 휴가 삭제
	int deleteLeave(Integer no);
}
