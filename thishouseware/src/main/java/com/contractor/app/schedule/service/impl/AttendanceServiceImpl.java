package com.contractor.app.schedule.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.contractor.app.schedule.mapper.AttendanceMapper;
import com.contractor.app.schedule.service.AttendanceService;
import com.contractor.app.schedule.service.AttendanceVO;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{

	private final AttendanceMapper attendanceMapper;

	@Override
	public AttendanceVO getLastAttendanceById(String id) {
		return attendanceMapper.selectLastAttendanceById(id);
	}

	@Override
	public boolean addAttendancesCode(AttendanceVO attendanceVO) {
		return attendanceMapper.insertAttendance(attendanceVO) == 1;
	}

	@Override
	public List<AttendanceVO> getAttendancesById(String string) {
		return attendanceMapper.selectAttendancesById(string);
	}

	@Override
	public List<AttendanceVO> getAttendances() {
		List<AttendanceVO> list = attendanceMapper.selectAttendances();

		return null;
	}
}
