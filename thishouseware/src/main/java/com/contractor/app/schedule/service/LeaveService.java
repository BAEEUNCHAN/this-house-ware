package com.contractor.app.schedule.service;

import java.util.List;
import java.util.Map;

public interface LeaveService {
	// 전체 휴가 조회
	public List<Map<String, Object>> leaveListAll();
	public List<LeaveVO> getLeaveListAll();
	// 부서별 휴가 조회
	public List<LeaveVO> leaveListAllWhereDepartmentNo(int departmentNo);
	
	//사원별 휴가 조회
	public List<LeaveVO> leaveListWhereId(String id);
	
	// 휴가 수정
	public int leaveUpdate(LeaveVO leaveVO);
	
	// 개인별 휴가 조회
	public List<Map<String, Object>> leaveList(String id);
	
	// 휴가 저장
	public int leaveInsert(LeaveVO leaveVO);
	
	// 휴가 삭제
	boolean leaveDelete(Integer leaveNo);
}
