package com.contractor.app.flutter.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;

import lombok.RequiredArgsConstructor;

@RestController // 전부 @ResponseBody 가 되며 데이터만 주고 받는다.
@CrossOrigin
@RequiredArgsConstructor
public class FlutterWithController {
	
	private final EmployeeService employeeService;
	private final BCryptPasswordEncoder encoder;
	
	@PostMapping("flutter/login")
	public EmployeeVO login(@RequestBody EmployeeVO empVO) {
		System.out.println(empVO);
		EmployeeVO finVo = new EmployeeVO();
		try {
			finVo = employeeService.getEmployee(empVO);
		}catch (Exception e) {
			return null;
		}
		
		
		if(!encoder.matches(empVO.getPassword(), finVo.getPassword())) {
			return null;
		}
		
		return finVo;
	}
}
