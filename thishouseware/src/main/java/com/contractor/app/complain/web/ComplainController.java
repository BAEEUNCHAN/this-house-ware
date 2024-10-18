package com.contractor.app.complain.web;

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


import com.contractor.app.complain.service.ComplainService;
import com.contractor.app.complain.service.ComplainsVO;

@Controller
public class ComplainController {
	private ComplainService complainService;
	private CompanyService companyService;
	
	@Autowired
	public ComplainController(ComplainService complainService) {
		this.complainService = complainService;
		this.companyService = companyService;
	}
	
	// 문의 전체조회 : URI - complainList / return - complain/complainList
	@GetMapping("complainList")
	public String complainList(ComplainsVO complainVO, Model model) {
		List<ComplainsVO> list = complainService.complainList();
		model.addAttribute("complains", list);
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		return "complain/complainList";
	}
	
	// 문의 단건조회
	@GetMapping("complainInfo")
	public String complainInfo(ComplainsVO complainVO, Model model) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		return "complain/complainInfo";
	}
	
	// 문의 등록 : URI - insertComplain / RETURN - complain/insertComplain
	@GetMapping("insertComplain")
	public String insertComplainForm(ComplainsVO complainVO, Model model, CompanysVO companyVO) {
		List<ComplainsVO> list = complainService.complainList();
		List<CompanysVO> companyList = companyService.companyList();
		model.addAttribute("complains", list);
		model.addAttribute("companys", companyList);
		return "complain/insertComplain";
	}
	
	// 문의 등록 : 문의 등록 처리
	@PostMapping("insertComplain")
	public String insertComplainProcess(ComplainsVO complainVO) {
		
		int complainNo = complainService.insertComplain(complainVO);
		return "redirect:complainInfo?complainNo="+ complainNo;
	}
	
	// 문의 수정 - 페이지 : URI - complainUpdate / PARAMETER - ComplainsVO(QueryString)
	// Return - complain/complainUpdate
	// => 단건조회에서 수정
	@GetMapping("complainUpdate")
	public String complainUpdateForm(ComplainsVO complainVO, Model model) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain" ,findVO);
		return "complain/complainUpdate";
	}
	
	// 문의 수정 - 처리 : URI - complainUpdate / PARAMETER - ComplainsVO(JSON) => @ResponseBody 써야함
	// Return - 수정결과 데이터(Map)
	// => 등록(내부에서 수행하는 쿼리문 - UPDATE문)
	@PostMapping("complainUpdate")
	public String complainUpdateProcess(ComplainsVO complainVO){
		 complainService.updateComplain(complainVO);
		 return "redirect:complainInfo?complainNo="+complainVO.getComplainNo();
	}
	
	// 삭제 - 처리 : URI - complainDelete / PARAMETER - Integer
	//             RETURN - 전체조회 다시 호출
	@ResponseBody
	@DeleteMapping("complainDelete") // QueryString : @RequestParam
	public String complainDelete(@RequestParam Integer complainNo) {
		complainService.deleteComplain(complainNo);
		return "redirect:complainList";
	}
	
}
