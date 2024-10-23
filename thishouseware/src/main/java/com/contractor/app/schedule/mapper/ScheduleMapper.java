package com.contractor.app.schedule.mapper;

import java.util.List;
import java.util.Map;

import com.contractor.app.schedule.service.ScheduleVO;

public interface ScheduleMapper {
	// 전체 일정 조회
	List<Map<String, Object>> listScheduleAll();
	
	// 사원별 일정 조회
	List<ScheduleVO> listSchedule();
	
	// 일정 단건 조회
	Map<String, Object> infoSchedule();
	
	// 일정 저장
	int insertSchedule(ScheduleVO scheduleVO);
	
	// 일정 수정
	int updateSchedule();
	
	// 일정 삭제
	int deleteSchedule();
}
