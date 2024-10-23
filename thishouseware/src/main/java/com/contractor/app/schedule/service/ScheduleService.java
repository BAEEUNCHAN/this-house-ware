package com.contractor.app.schedule.service;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
	// 전체 일정 조회
	// List<ScheduleVO> scheduleListAll();
	public List<Map<String, Object>> scheduleListAll();
	
	// 사원별 일정 조회
	List<ScheduleVO> scheduleList();
	
	// 일정 저장
	boolean scheduleSave();
	
	// 일정 수정
	boolean scheduleUpdate();
	
	// 일정 삭제
	boolean ScheduleDelete();
}
