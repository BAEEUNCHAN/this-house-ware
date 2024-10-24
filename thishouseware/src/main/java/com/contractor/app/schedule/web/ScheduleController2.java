package com.contractor.app.schedule.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.schedule.service.AttendanceService;
import com.contractor.app.schedule.service.AttendanceVO;
import com.contractor.app.schedule.service.ScheduleService;
import com.contractor.app.schedule.service.ScheduleVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController2 {
	private final AttendanceService attendanceService;
	
	@PostMapping("attendance/modifyCode")
	@ResponseBody
	public String modifyAttendanceCode(@RequestBody AttendanceVO attendanceVO) {
		System.out.println(attendanceVO.getAttendancesCode());
		
		return "success";
	}
}
