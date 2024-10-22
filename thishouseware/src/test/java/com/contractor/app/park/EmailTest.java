package com.contractor.app.park;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.contractor.app.employee.service.EmailService;
import com.contractor.app.employee.service.EmailVO;

@SpringBootTest
public class EmailTest {
	@Autowired
	private EmailService emailService;
	
	@Test
	void pwdEncoderTest() {
		emailService.sendEmailNotice("test@naver.com");
	}
}
