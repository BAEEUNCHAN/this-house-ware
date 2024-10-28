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
	public int scheduleInsert(ScheduleVO scheduleVO);
	
	// 일정 단건 조회
	public Map<String, Object> scheduleInfo(Integer no);
	
	// 일정 수정
	public int scheduleUpdate(ScheduleVO scheduleVO);
	
	// 일정 삭제
	boolean scheduleDelete(Integer no);
}
