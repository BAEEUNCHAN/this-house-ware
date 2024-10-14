package com.contractor.app.company.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contractor.app.company.service.CompanysVO;
import com.contractor.app.company.service.CompanyService;

@Controller
public class CompanyController {
	private CompanyService companyService;
	
	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	// 회사(고객)정보 전체조회 : URI - companyLlist / return - complain/companyList
	@GetMapping("companyList")
	public String companyList(Model model) {
		List<CompanysVO>list = companyService.companyList();
		model.addAttribute("companys", list);
		return "company/companyLIst";
	}
	
}
