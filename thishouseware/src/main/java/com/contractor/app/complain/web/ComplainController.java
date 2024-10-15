package com.contractor.app.complain.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.contractor.app.company.service.CompanyService;
import com.contractor.app.company.service.CompanysVO;
import com.contractor.app.company.service.ComplainsVO;
import com.contractor.app.complain.service.ComplainService;

@Controller
public class ComplainController {
	private ComplainService complainService;
	
	@Autowired
	public ComplainController(ComplainService complainService, CompanyService companyService) {
		this.complainService = complainService;
	}
	
	// 문의 단건조회
	@GetMapping("complainInfo")
	public String complainInfo(ComplainsVO complainVO, Model model) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		return "complain/complainInsert";
	}
	
	
	// 문의 전체조회 : URI - complainList / return - complain/complainList
	@GetMapping("complainList")
	public String complainList(Model model) {
		List<ComplainsVO> list = complainService.complainList();
		model.addAttribute("complains", list);
		return "complain/complainList";
	}
	
	// 문의 등록 : URI - insertComplain / RETURN - complain/insertComplain
	@GetMapping("insertComplain")
	public String insertComplainForm(ComplainsVO complainVO, Model model) {
		List<ComplainsVO> list = complainService.complainList();
		model.addAttribute("complains", list);
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		return "complain/insertComplain";
	}
	
	// 문의 등록 : 문의 등록 처리
	@PostMapping("insertComplain")
	public String insertComplainProcess(ComplainsVO complainVO) {
		
		int complainNo = complainService.insertComplain(complainVO);
		return "redirect:complainList";
	}
	
}
