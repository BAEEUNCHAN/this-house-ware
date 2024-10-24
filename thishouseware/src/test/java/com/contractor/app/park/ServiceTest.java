package com.contractor.app.park;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.contractor.app.employee.service.EmployeeService;

@SpringBootTest
public class ServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private PasswordEncoder encoder;
	
	void canUpdateCheck() {
		String newPassword = "1234";
		String auth = "HLL7KWU3SQW0566";
		newPassword = encoder.encode(newPassword);
		
		// 값은 맞으나 시간 초과일경
		String answer = employeeService.canChangePw("emp115", auth);
		System.out.println(answer);
		
		// 실패할경우
		answer = employeeService.canChangePw("emp115", "wrong");
		
	}
}
