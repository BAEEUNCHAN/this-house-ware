package com.contractor.app.schedule.service;

public interface AttendanceService {

	AttendanceVO getLastAttendanceById(String id);

	boolean addAttendancesCode(AttendanceVO attendanceVO);
	
}
