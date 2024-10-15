package com.contractor.app.manager.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.manager.service.ManagerService;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ManagerController {

	private final ManagerService managerService; 
	private final EmployeeService employeeService;
	
	@GetMapping("manager/addUser")
	public String addUser(Model model ) {
		
		List<DepartmentVO> departments = employeeService.getDepartmentList();
		departments.forEach(obj ->{
			System.out.println(obj);
		});
		
		model.addAttribute("departments" , departments);
		
		return "home";
		
	}
}
