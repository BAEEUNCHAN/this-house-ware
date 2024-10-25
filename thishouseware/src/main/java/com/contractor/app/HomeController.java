package com.contractor.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* 테스트용 */
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/home2")
	public String homePage2() {
		return "home2";
	}
}
