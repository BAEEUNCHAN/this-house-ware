package com.contractor.app.schedule.service;

import java.util.List;

public interface LeaveDetailService {
	// 휴가 상세 전체 조회
	public List<LeaveDetailVO> leaveDetailList();
	
	// 휴가 상세 단건 조회
	public LeaveDetailVO leaveDetailSelect(String id);
	
	// 휴가 상세 추가
	public boolean leaveDetailInsert(LeaveDetailVO leaveDetailVO);
	
	// 휴가 상세 수정	
	public boolean leaveDetailUpdate(LeaveDetailVO leaveDetailVO);
	
	// 휴가 상세 삭제
	public boolean leaveDetailDelete(String id);
}
