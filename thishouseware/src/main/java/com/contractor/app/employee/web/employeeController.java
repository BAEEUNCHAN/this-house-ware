package com.contractor.app.employee.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class employeeController {
	
	private final EmployeeService employeeService;
	private final PasswordEncoder encoder;

	@GetMapping("login")
	public String loginForm() {
		return "employee/login";
	}
	
	@GetMapping("employee/info/{id}")
	public String employeeInfo(@PathVariable String id,EmployeeVO empVO ,Model model) {
		empVO.setId(id);
		empVO = employeeService.getEmployee(empVO);
		model.addAttribute("employee",empVO);
		return "employee/employeeInfo";
	}
	
	@GetMapping("employee/modify/{id}")
	public String employeeModifyForm(@PathVariable String id,EmployeeVO empVO ,Model model) {
		empVO.setId(id);
		model.addAttribute("employee",empVO);
		return "employee/employeeModify";
	}
	
	@PostMapping("employee/modify/{id}")
	@ResponseBody
	public String employeeModify(@PathVariable String id
			,@RequestParam(required = false) String phone 
			,@RequestParam(required = false) String newPassword
			,@RequestParam(required = false) String checkNewPassword
			,@RequestParam(required = false) String checkPassword
			) {
		EmployeeVO empVO = new EmployeeVO();
		empVO.setId(id);
		empVO = employeeService.getEmployee(empVO);
		
		// 비밀번호로 본인이 맞는지 확인
		if(!encoder.matches(checkPassword, empVO.getPassword()) ) {
			return "error1"; // 확인 비밀번호 불일치
		}
		
		if(phone.length() <=0 && newPassword.length() <=0 ) {
			return "error2"; // 변경되는 정보가 없음을 알림
		}
		
		// 새로운 비밀번호가 있으면 비밀번호 변경작업 실행
		if(newPassword.length() > 0) {
			// 비밀번호 변경작업
			String encodedNewPassword = encoder.encode(newPassword);
			empVO.setPassword(encodedNewPassword);
		}
		
		// 전화번호 정보가 있으면 전화번호 변경작업 실행
		if(phone.length() > 0) {
			empVO.setPhone(phone);
		}
		
		try {
			employeeService.modifyEmployee(empVO);
		} catch (Exception e) {
			return "error3"; // 서버 오류 반환
		}
		
		return "success";
	}
}
