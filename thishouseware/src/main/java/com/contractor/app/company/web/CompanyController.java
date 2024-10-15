package com.contractor.app.company.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.contractor.app.company.service.CompanyService;
import com.contractor.app.company.service.CompanysVO;

@Controller
public class CompanyController {
	private CompanyService companyService;
	
	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	// 회사(고객)정보 전체조회 : URI - companyLlist / return - company/companyList
	@GetMapping("companyList")
	public String companyList(Model model) {
		List<CompanysVO> list = companyService.companyList();
		model.addAttribute("companys", list);
		return "company/companyLIst";
	}
	
	// 회사(고객)정보 등록 : URI - insertCompany / RETURN - company/insertCompany
	@GetMapping("insertCompany")
	public String insertCompanyForm(CompanysVO companyVO) {
		return "company/insertCompany";
	}
	
	// 회사(고객)정보 등록 처리
	@PostMapping("insertCompany")
	public String insertCompanyProcess(CompanysVO companyVO) {
		int companyNo = companyService.insertCompany(companyVO);
		return "redirect:companyList";
	}
	
}
