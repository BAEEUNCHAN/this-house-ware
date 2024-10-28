package com.contractor.app.schedule.web;

import java.util.Date;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.schedule.service.AttendanceService;
import com.contractor.app.schedule.service.AttendanceVO;
import com.contractor.app.schedule.service.ScheduleService;
import com.contractor.app.schedule.service.ScheduleVO;
import com.contractor.app.security.service.LoginUserVO;
import com.contractor.app.util.AttendancesUtil;
import com.contractor.app.util.EmployeeUtil;
import com.contractor.app.util.GetIPUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController2 {
	@Value("${company.ip.front}")
	private String companyIpFront;
	private String mainIp = "127.0.0.1";
	
	// Google API key
	@Value("${apikey.googlekey}")
	String key;
	
	private final AttendanceService attendanceService;
	private final EmployeeService employeeService;
	
	@PostMapping("attendance/modifyCode")
	@ResponseBody
	public String modifyAttendanceCode(@RequestBody AttendanceVO attendanceVO,
			Authentication authentication,  HttpSession session,
			HttpServletRequest request) {
		
		// 회사 컴퓨터로 로그인된것인지 확인한다.
		String requestIp = GetIPUtil.getClientIp(request);
		if(!requestIp.contains(companyIpFront)) {
			// 매인 서버용 ip 확인 (실 서비스때는 지우자.)
			if(!requestIp.contains(mainIp)) {
				return "error1";
			}
		}
		
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
	
	@GetMapping("attendance/empPage")
	public String empPage(Model model,@RequestParam String id ,Authentication authentication) {
		model.addAttribute("key", key);
		model.addAttribute("id", id);
		return "schedule/attendanceInfo";
	}
	
	@GetMapping("attendance/empData")
	@ResponseBody
	public List<CalendarVO> empData(@RequestParam String id,Authentication authentication){
		List<AttendanceVO> list = attendanceService.getAttendancesById(id);
		
		// 달력에 출력하기 위한 형태로 형변환하기
		List<CalendarVO> calendarList = new ArrayList<CalendarVO>();
		for(AttendanceVO aVo : list) {
			String attendanceName = AttendancesUtil.getAttendanceCode(aVo.getAttendancesCode());
			CalendarVO vo = new CalendarVO();
			vo.setTitle(attendanceName);
			vo.setStart(aVo.getTime());
			vo.setEnd(aVo.getTime());
			calendarList.add(vo);
			
		}
		return calendarList;
	}
	
	@Data
	private class CalendarVO{
		String title;
		Date start;
		Date end;
	}
	
	@GetMapping("attendance/emps")
	public String empsPage(Model model) {
		List<EmployeeVO> emps = new ArrayList<EmployeeVO>();
		emps = employeeService.getEmployees();
		emps.forEach(obj ->{
			String positionName = EmployeeUtil.getPostionName(obj.getPositionCode());
			obj.setPositionName(positionName);
		});
		model.addAttribute("employees" , emps);
		return "schedule/empsAttendances";
	}
}
