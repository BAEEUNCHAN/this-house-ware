package com.contractor.app.schedule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.schedule.mapper.ScheduleMapper;
import com.contractor.app.schedule.service.ScheduleService;
import com.contractor.app.schedule.service.ScheduleVO;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleMapper scheduleMapper;
	
	// 일정 전체 조회
	@Override
	public List<Map<String, Object>> scheduleListAll() {		
		List<Map<String, Object>> schedules = scheduleMapper.listScheduleAll();
		
		return schedules;
	}
	
	// 일정 개인별 조회
	@Override
	public List<ScheduleVO> scheduleList() {
		return scheduleMapper.listSchedule();
	}

	// 일정 등록
	@Override
	public int scheduleInsert(ScheduleVO scheduleVO) {
		int result = scheduleMapper.insertSchedule(scheduleVO);
		return result == 1 ? scheduleVO.getScheduleNo() : -1;
	}
	
	// 일정 단건 조회
	@Override
	public Map<String, Object> scheduleInfo(Integer no) {		
		return scheduleMapper.infoSchedule();
	}
	
	// 일정 수정
	@Override
	public int scheduleUpdate(ScheduleVO scheduleVO) {
		int result = scheduleMapper.updateSchedule(scheduleVO);
		return result == 1 ? scheduleVO.getScheduleNo() : -1;
	}
	
	// 일정 삭제
	@Override
	public boolean ScheduleDelete(Integer no) {
		return scheduleMapper.deleteSchedule(no) == 1;
	}
}
