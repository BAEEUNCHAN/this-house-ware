package com.contractor.app.leave.web;

import org.springframework.stereotype.Controller;

@Controller
public class LeaveController {
	/*
	// 해당 컨트롤러에서 제공하는 서비스
	private final LeaveService leaveService;

	@Autowired
	LeaveController(LeaveService leaveService) {
		this.leaveService = leaveService;
	}

	// 등록 - 페이지 : Get
	@GetMapping("leaveInsert")
	public String leaveInsertForm() {
		return "leave/insert";
	}

	// 수정 - 페이지 : Get, 조건이 필요 <=> 단건조회
	@GetMapping("leaveUpdate") // empUpdate?employeeId=value
	public String empUpdateForm(LeaveVO leaveVO, Model model) {
		LeaveVO findVO = leaveService.leaveInfo(leaveVO); // info 부분 결재로 연결해야됨
		model.addAttribute("leave", findVO);
		return "leave/update";
	}

	// 삭제 - 처리 : Get => QueryString( @RequestParam )
	@GetMapping("leaveDelete")
	public String leaveDelete(String leaveApplication) {
		leaveService.leaveDelete(leaveApplication);
		return "redirect:leave"; // 결재 목록으로 가게 수정해야됨
	}
	*/

}