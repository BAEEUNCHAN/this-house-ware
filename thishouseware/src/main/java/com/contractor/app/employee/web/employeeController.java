package com.contractor.app.employee.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class employeeController {

	@GetMapping("login")
	public String loginForm() {
		return "employee/login";
	}
}
