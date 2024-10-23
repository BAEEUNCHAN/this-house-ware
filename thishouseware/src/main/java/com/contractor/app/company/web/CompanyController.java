package com.contractor.app.company.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.company.service.CompanyService;
import com.contractor.app.company.service.CompanysVO;

@Controller
public class CompanyController {
	private CompanyService companyService;

	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	// 회사정보 전체조회 : URI - companyLlist / return - company/companyList
	@GetMapping("companyList")
	public String companyList(Model model) {
		List<CompanysVO> list = companyService.companyList();
		model.addAttribute("companys", list);
		return "company/companyList";
	}

	// 회사(고객)정보 단건조회(고객명+연락처)
	@GetMapping("companyInfo")
	public String companyInfo(CompanysVO companyVO, Model model) {
		List<CompanysVO> list = companyService.companyInfoList(companyVO);
		model.addAttribute("companys", list);
		return "company/companyInfo";
	}

	// 회사정보 등록 : URI - insertCompany / RETURN - company/insertCompany
	@GetMapping("insertCompany")
	public String insertCompanyForm(CompanysVO companyVO) {
		return "company/insertCompany";
	}

	// 회사정보 등록 처리
	@PostMapping("insertCompany")
	public String insertCompanyProcess(CompanysVO companyVO, Model model) {
		boolean companyNameCheck = companyService.companyCheckName(companyVO.getCompanyName());

		if (companyNameCheck) {
			model.addAttribute("message", "이미 등록된 회사입니다.");
			return "company/insertCompany";
		}
		int companyNo = companyService.insertCompany(companyVO);
		return "redirect:companyList";
	}

	// 회사 삭제
	@ResponseBody
	@DeleteMapping("companyDelete")
	public String companyDelete(@RequestParam Integer companyNo) {
		companyService.deleteCompany(companyNo);
		return "redirect:companyList";
	}

}
