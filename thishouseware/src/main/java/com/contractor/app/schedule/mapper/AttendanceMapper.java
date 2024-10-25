package com.contractor.app.schedule.mapper;

import org.apache.ibatis.annotations.Param;

import com.contractor.app.schedule.service.AttendanceVO;

public interface AttendanceMapper {

	AttendanceVO selectLastAttendanceById(String id);

	int insertAttendance(AttendanceVO attendanceVO);

}
