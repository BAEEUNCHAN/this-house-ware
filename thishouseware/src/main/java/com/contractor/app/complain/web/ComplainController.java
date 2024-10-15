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
	private CompanyService companyService;
	
	@Autowired
	public ComplainController(ComplainService complainService, CompanyService companyService) {
		this.complainService = complainService;
		this.companyService = companyService;
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
	public String insertComplainForm(ComplainsVO complainsVO, Model model) {
		List<CompanysVO> list = companyService.companyList();
		model.addAttribute("companys", list);
		return "complain/insertComplain";
	}
	
	// 문의 등록 : 문의 등록 처리
	@PostMapping("insertComplain")
	public String insertComplainProcess(ComplainsVO complainVO) {
		int complainNo = complainService.insertComplain(complainVO);
		return "redirect:complainList";
	}
	
}
