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

	@Override
	public List<Map<String, Object>> scheduleListAll() {		
		List<Map<String, Object>> schedules = scheduleMapper.listScheduleAll();
		
		return schedules;
	}

	@Override
	public List<ScheduleVO> scheduleList() {
		return scheduleMapper.listSchedule();
	}

	@Override
	public boolean scheduleSave() {
		return scheduleMapper.insertSchedule() == 1;
	}

	@Override
	public boolean scheduleUpdate() {
		return scheduleMapper.updateSchedule() == 1;
	}

	@Override
	public boolean ScheduleDelete() {
		return scheduleMapper.deleteSchedule() == 1;
	}
	
}
