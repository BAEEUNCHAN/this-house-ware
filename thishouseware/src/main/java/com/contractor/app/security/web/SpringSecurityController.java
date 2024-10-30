package com.contractor.app.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SpringSecurityController {

	@GetMapping("authorization-fail")
	public String forbiddenPage(){
		return "common/error/403";
	}
}
