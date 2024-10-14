package com.contractor.app.schedule.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
	// 테스트용
	@GetMapping("scheduleList")
	public String scheduleList(Model model) {
		return "schedule/scheduleList";
	}
}
