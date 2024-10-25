package com.contractor.app.schedule.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.schedule.service.AttendanceService;
import com.contractor.app.schedule.service.AttendanceVO;
import com.contractor.app.schedule.service.ScheduleService;
import com.contractor.app.schedule.service.ScheduleVO;
import com.contractor.app.security.service.LoginUserVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController2 {
	private final AttendanceService attendanceService;
	
	@PostMapping("attendance/modifyCode")
	@ResponseBody
	public String modifyAttendanceCode(@RequestBody AttendanceVO attendanceVO, Authentication authentication,  HttpSession session) {
		LoginUserVO loginUserVO = (LoginUserVO) authentication.getPrincipal();
		EmployeeVO employeeVO = loginUserVO.getEmpVO();
		attendanceVO.setId(employeeVO.getId());
		attendanceVO.setDepartmentNo(employeeVO.getDepartmentNo());
		
		if(!attendanceService.addAttendancesCode(attendanceVO)) {
			return "error";
		}
		
		// 근태코드를 새션상으로 올린다.(로그아웃시 모든 세션이 제거되므로 따로 제거할 필요 없다.)
        session.setAttribute("attendance",attendanceVO);
			
		return "success";
	}
}
