package com.contractor.app.schedule.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.schedule.service.ScheduleService;
import com.contractor.app.schedule.service.ScheduleVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;
	
	// Google API key
	@Value("${apikey.googlekey}")
	String key;
	
	// 스케쥴 페이지 화면
	@GetMapping("schedule/scheduleList")
	public String scheduleList(Model model) {
		model.addAttribute("key", key);
		return "schedule/scheduleList";
	}
	
	// DB서버의 일정 가져오기 AJAX
	@PostMapping("schListAll")
	@ResponseBody
	public List<Map<String, Object>> schListJSON() {		
		return scheduleService.scheduleListAll();
	}
	
	// 일정 등록
	@PostMapping("schAdd")
	@ResponseBody
	public Map<String, Object> schAddJSON(@RequestBody ScheduleVO scheduleVO) {
		Map<String, Object> result = new HashMap<>();
		try {
			// 받은 데이터 확인용
	        System.out.println("Received Title: " + scheduleVO.getTitle());
	        System.out.println("Received Start Date: " + scheduleVO.getStart());
	        System.out.println("Received End Date: " + scheduleVO.getEnd());
	        System.out.println("Received Schedule Code: " + scheduleVO.getSheduleCode());
	        System.out.println("Received Department No: " + scheduleVO.getDepartmentNo());
	        System.out.println("Received Id: " + scheduleVO.getId());
	        
	        // DB에 저장
			if (scheduleService.scheduleInsert(scheduleVO) > 0) {
				result.put("success", true);
				System.out.println("Success");
			}
			else {
				result.put("success", false);
				System.out.println("Fail");
			}
		} catch (Exception e) {
			result.put("success", false);
		}
		return result;
	}
	
	// 일정 단건 조회
	@PostMapping("schInfo")
	@ResponseBody
	public Map<String, Object> schInfoJSON(Integer no) {		
		return scheduleService.scheduleInfo(no);
	}
}
