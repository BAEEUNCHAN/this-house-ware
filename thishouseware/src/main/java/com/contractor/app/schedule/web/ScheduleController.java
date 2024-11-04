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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.schedule.service.LeaveDetailService;
import com.contractor.app.schedule.service.LeaveDetailVO;
import com.contractor.app.schedule.service.LeaveService;
import com.contractor.app.schedule.service.LeaveVO;
import com.contractor.app.schedule.service.ScheduleService;
import com.contractor.app.schedule.service.ScheduleVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;
	private final EmployeeService employeeService;
	
	private final LeaveService leaveService;
	private final LeaveDetailService leaveDetailService;
	
	// Google API key
	@Value("${apikey.googlekey}")
	String key;
	
	String id = "emp102"; // 실제 로그인 id 가져오기
	
	// 스케쥴 페이지 화면
	@GetMapping("schedule/scheduleList")
	public String scheduleList(Model model) {
		model.addAttribute("key", key);
		return "schedule/scheduleList";
	}
	
	// DB서버의 일정 가져오기 AJAX
	@PostMapping("schListAll")
	@ResponseBody
	public List<ScheduleVO> schListJSON(@RequestParam(required = false) String id, String positionCode, int departmentNo) {
		//return scheduleService.scheduleListAll();
		System.out.println(id);
		System.out.println(positionCode);
		System.out.println(departmentNo);
		
		if(positionCode.equals("a1") || positionCode.equals("a2") || positionCode.equals("a3")) {
			// 직급 a1(사장), a2(관리자), a3(본부장)은 모든 일정확인가능
			return scheduleService.scheduleListAll();
		}else if(positionCode.equals("a4")) {
			// a4(팀장)은 자신의 부서 모든 일정 확인가능
			return scheduleService.scheduleListWhereDepartmentNo(departmentNo);
		} else{
			// 그 외 자신이 등록한 일정만 확인가능(인턴, 사원..)
			return scheduleService.scheduleList(id);
		}
		
	}
	
	// 일정 등록
	@PostMapping("schAdd")
	@ResponseBody
	public Map<String, Object> schAddJSON(@RequestBody ScheduleVO scheduleVO) {
		Map<String, Object> result = new HashMap<>();
		try {
			// 받은 데이터 확인용
	        System.out.println("Received Title: " + scheduleVO.getTitle());
	        System.out.println("Received Start Date: " + scheduleVO.getStart());
	        System.out.println("Received End Date: " + scheduleVO.getEnd());
	        System.out.println("Received Schedule Code: " + scheduleVO.getSheduleCode());
	        System.out.println("Received Department No: " + scheduleVO.getDepartmentNo());
	        System.out.println("Received Id: " + scheduleVO.getId());
	        System.out.println("Received bgColor: " + scheduleVO.getColor());
	        
	        // DB에 저장
	        int no = scheduleService.scheduleInsert(scheduleVO);	        
			if (no > 0) {
				result.put("scheduleNo", no);
				result.put("success", true);
				System.out.println("Success");
			}
			else {
				result.put("success", false);
				System.out.println("Fail");
			}
		} catch (Exception e) {
			result.put("success", false);
		}
		return result;
	}
	
	// 일정 단건 조회
	@PostMapping("schInfo")
	@ResponseBody
	public Map<String, Object> schInfoJSON(Integer no) {		
		return scheduleService.scheduleInfo(no);
	}
	
	// 일정 등록
	@PostMapping("schUpdate")
	@ResponseBody
	public Map<String, Object> schUpdateJSON(@RequestBody ScheduleVO scheduleVO) {
		Map<String, Object> result = new HashMap<>();
		try {
			// 받은 데이터 확인용
	        System.out.println("Received Title: " + scheduleVO.getTitle());
	        System.out.println("Received Start Date: " + scheduleVO.getStart());
	        System.out.println("Received End Date: " + scheduleVO.getEnd());
	        System.out.println("Received Content: " + scheduleVO.getContent());
	        System.out.println("Received Schedule Code: " + scheduleVO.getSheduleCode());
	        System.out.println("Received Department No: " + scheduleVO.getDepartmentNo());
	        System.out.println("Received Id: " + scheduleVO.getId());
	        System.out.println("Received bgColor: " + scheduleVO.getColor());
	        
	        // DB에 저장
			if (scheduleService.scheduleUpdate(scheduleVO) > 0) {
				result.put("success", true);
				System.out.println("Success");
			}
			else {
				result.put("success", false);
				System.out.println("Fail");
			}
		} catch (Exception e) {
			result.put("success", false);
		}
		return result;
	}
	
	// 일정 삭제
	@PostMapping("schDelete")
	@ResponseBody
	public Map<String, Object> schDelete(Integer no) {
		Map<String, Object> result = new HashMap<>();
		try {
			if(scheduleService.scheduleDelete(no)) {
				result.put("success", true);
				System.out.println("Success");
			}
			else {
				result.put("success", false);
				System.out.println("Fail");
			}
		} catch (Exception e) {
			result.put("success", false);
		}
		return result;
	}
	
	// 휴가 조회 페이지
	@GetMapping("schedule/leaveList")
	public String leavesList(LeaveDetailVO leaveDetailVO, Model model) {		
		LeaveDetailVO ldInfo = leaveDetailService.leaveDetailSelect(id);		
		model.addAttribute("ldInfo", ldInfo);
		model.addAttribute("key", key);
		return "schedule/leaveList";
	}
	
	
	// DB서버의 휴가 가져오기 AJAX
	@PostMapping("leaveListAll")
	@ResponseBody
	public List<Map<String, Object>> leaveListJSON(@RequestParam(required = false) String id, String positionCode, int departmentNo) {		
		System.out.println(id);
		System.out.println(positionCode);
		System.out.println(departmentNo);
		
		if(positionCode.equals("a1") || positionCode.equals("a2") || positionCode.equals("a3")) {
			// 직급 a1(사장), a2(관리자), a3(본부장)은 모든 일정확인가능
		}else if(positionCode.equals("a4")) {
			// a4(팀장)은 자신의 부서 모든 일정 확인가능
		} else{
			// 그 외 자신이 등록한 일정만 확인가능(인턴, 사원..)
		}
		return leaveService.leaveListAll();
	}
	
	
	/*
	// DB서버의 휴가 가져오기 AJAX
	@PostMapping("leaveListAll")
	@ResponseBody
	public List<LeaveVO> leaveListJSON() {		
		return leaveService.getLeaveListAll();
	}
	*/
	
	// DB서버의 자신의 휴가 가져오기 AJAX
	@PostMapping("getLeave")
	@ResponseBody
	public List<Map<String, Object>> getLeaveJSON() {		
		return leaveService.leaveList(id);
	}
	
		
	// 관리자 => 팀원 일정확인 리스트
	@GetMapping("/schedule/adminCheckOthersScheduleList")
	public String adminCheckOthersScheduleList(EmployeeVO employeeVO, Model model) {
		model.addAttribute("key", key);
		List<DepartmentVO> deptList = employeeService.getDepartmentList();
		List<EmployeeVO> list = employeeService.getEmployees();
		List<EmployeeVO> list2 = employeeService.getEmployeesWhereDept(employeeVO);
		model.addAttribute("depts", deptList);
		model.addAttribute("emps", list);
		model.addAttribute("empsdepts", list2);

		return "schedule/adminCheckOthersScheduleList";

	}
	

}
