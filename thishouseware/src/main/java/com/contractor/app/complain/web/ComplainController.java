package com.contractor.app.complain.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contractor.app.complain.service.CompanysVO;
import com.contractor.app.complain.service.ComplainService;

@Controller
public class ComplainController {
	private ComplainService complainService;
	
	@Autowired
	public ComplainController(ComplainService complainService) {
		this.complainService = complainService;
	}
	
	// 회사(고객)정보 전체조회 : URI - companyLlist / return - complain/companyList
	@GetMapping("companyList")
	public String companyList(Model model) {
		List<CompanysVO>list = complainService.companyList();
		model.addAttribute("companys", list);
		return "complain/companyLIst";
	}
	
}
