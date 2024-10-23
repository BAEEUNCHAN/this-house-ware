package com.contractor.app.schedule.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.schedule.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;
	
	// Google API key
	@Value("${apikey.googlekey}")
	String key;
	
	// 테스트용
	// "[{"title":"Sample", "start":"2023-01-05"},
	// {"title":"회의", "start":"2023-01-21T13:00:00", "end":"2023-01-21T16:00:00"}]"
	@GetMapping("schedule/scheduleList")
	public String scheduleList(Model model) {
		model.addAttribute("key", key);
		return "schedule/scheduleList";
	}
	
	@PostMapping("schListAll")
	@ResponseBody
	public List<Map<String, Object>> schListJSON() {		
		return scheduleService.scheduleListAll();
	}
}
