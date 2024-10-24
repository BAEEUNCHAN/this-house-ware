package com.contractor.app.schedule.mapper;

import com.contractor.app.schedule.service.AttendanceVO;

public interface AttendanceMapper {

	AttendanceVO selectLastAttendanceById(String id);

}
