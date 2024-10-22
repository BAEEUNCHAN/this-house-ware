package com.contractor.app.employee.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class employeeController {
	
	private final EmployeeService employeeService;

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
}
