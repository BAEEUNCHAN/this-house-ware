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

import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.schedule.service.AttendanceService;
import com.contractor.app.schedule.service.AttendanceVO;
import com.contractor.app.schedule.service.ScheduleService;
import com.contractor.app.schedule.service.ScheduleVO;
import com.contractor.app.security.service.LoginUserVO;
import com.contractor.app.util.AttendancesUtil;
import com.contractor.app.util.EmpAuthUtil;
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
	
	// Google API key
	@Value("${apikey.googlekey}")
	String key;
	
	private final AttendanceService attendanceService;
	private final EmployeeService employeeService;
	private final EmpAuthUtil empAuthUtil;
	
	/**
	 * 출근, 퇴근, 외출, 복귀 기능을 위한 메서드이다.
	 */
	@PostMapping("attendance/modifyCode")
	@ResponseBody
	public String modifyAttendanceCode(@RequestBody AttendanceVO attendanceVO,
			Authentication authentication,  HttpSession session,
			HttpServletRequest request) {
		
		// 회사 컴퓨터로 로그인된것인지 확인한다.
		String requestIp = GetIPUtil.getPublicIP();
		// System.out.println("ip"+requestIp);
		if(!requestIp.contains(companyIpFront)) {
			return "error1";
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
		
		// 해당 페이지를 볼 권한이 없는경우
		if(!empAuthUtil.authChek(authentication, id , false)) {
			return "common/error/403";
		}
		
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
	
	/**
	 * 해당 요청은 팀장, 본부장, 사장 , 관리자만 요청할 수 있게한다. (스프링 시큐리티 url 권한으로 제한한다.)
	 * 이후 해당 부서값을 통해 보여줄 데이터를 제한한다.
	 */
	@GetMapping("attendance/emps")
	public String empsPage(Model model,Authentication authentication) {
		List<Integer> deptNos = empAuthUtil.searchDeptNos(authentication);
		List<EmployeeVO> emps = employeeService.getEmployees();
		
		if(empAuthUtil.getAuthEmp(authentication).getDepartmentNo() == 1) {
			emps.forEach(obj -> {
				String positionName = EmployeeUtil.getPostionName(obj.getPositionCode());
				obj.setPositionName(positionName);
			});
			
			model.addAttribute("employees" , emps);
			return "schedule/empsAttendances";
		}
		
		List<EmployeeVO> filterEmps = new ArrayList<EmployeeVO>();
		emps.forEach(obj ->{
			if(deptNos.contains(obj.getDepartmentNo())) {
				String positionName = EmployeeUtil.getPostionName(obj.getPositionCode());
				obj.setPositionName(positionName);
				filterEmps.add(obj);
			}
		});
		model.addAttribute("employees" , filterEmps);
		return "schedule/empsAttendances";
	}
}
