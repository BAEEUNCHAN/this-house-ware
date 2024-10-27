package com.contractor.app.schedule.service;

import java.util.List;

public interface AttendanceService {

	AttendanceVO getLastAttendanceById(String id);

	boolean addAttendancesCode(AttendanceVO attendanceVO);

	List<AttendanceVO> getAttendancesById(String name);
	
}
