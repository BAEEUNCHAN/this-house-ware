package com.contractor.app.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.contractor.app.schedule.service.AttendanceVO;

public interface AttendanceMapper {

	AttendanceVO selectLastAttendanceById(String id);

	int insertAttendance(AttendanceVO attendanceVO);

	List<AttendanceVO> selectAttendancesById(String string);

	List<AttendanceVO> selectAttendances();

}
