package com.contractor.app.complain.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contractor.app.company.service.ComplainsVO;
import com.contractor.app.complain.service.ComplainService;

@Controller
public class ComplainController {
	private ComplainService complainService;
	
	@Autowired
	public ComplainController(ComplainService complainService) {
		this.complainService = complainService;
	}
	
	// 문의 전체조회 : URI - complainList / return - complain/complainList
	@GetMapping("complainList")
	public String complainList(Model model) {
		List<ComplainsVO> list = complainService.complainList();
		model.addAttribute("complains", list);
		return "complain/complainList";
	}
	
	// 문의 등록
}
