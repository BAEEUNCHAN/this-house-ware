package com.contractor.app.complain.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ComplainController(ComplainService complainService, CompanyService companyService) {
		this.complainService = complainService;
		this.companyService = companyService;
	}
	
	// 문의 전체조회 : URI - complainList / return - complain/complainList
	@GetMapping("complain/complainList")
	public String complainList(ComplainsVO complainVO, Model model) {
		List<ComplainsVO> list = complainService.complainList();
		model.addAttribute("complains", list);
		//ComplainsVO findVO = complainService.complainInfo(complainVO);
		//model.addAttribute("complain", findVO);
		return "complain/complainList";
	}
	
	// 문의 단건조회
	@GetMapping("complain/complainInfo")
	public String complainInfo(ComplainsVO complainVO, Model model) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		List<ComplainsVO> list = complainService.complainDeptInfo(complainVO);
		model.addAttribute("replys", list);
		return "complain/complainInfo";
	}
	
	// 문의 등록 : URI - insertComplain / RETURN - complain/insertComplain
	@GetMapping("complain/insertComplain")
	public String insertComplainForm(ComplainsVO complainVO, Model model, CompanysVO companyVO) {
		//List<ComplainsVO> list = complainService.complainList();
		List<CompanysVO> companyList = companyService.companyList();
		//model.addAttribute("complains", list);
		model.addAttribute("companys", companyList);
		return "complain/insertComplain";
	}
	
	// 문의 등록 : 문의 등록 처리
	@PostMapping("complain/insertComplain")
	public String insertComplainProcess(ComplainsVO complainVO) {
		
		int complainNo = complainService.insertComplain(complainVO);
		return "redirect:/complain/complainInfo?complainNo="+ complainNo;
	}
	
	// 문의 수정 - 페이지 : URI - complainUpdate / PARAMETER - ComplainsVO(QueryString)
	// Return - complain/complainUpdate
	// => 단건조회에서 수정
	@GetMapping("complain/complainUpdate")
	public String complainUpdateForm(ComplainsVO complainVO, Model model) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain" ,findVO);
		return "complain/complainUpdate";
	}
	
	// 문의 수정 - 처리 : URI - complainUpdate / PARAMETER - ComplainsVO(JSON) => @ResponseBody 써야함
	// Return - 수정결과 데이터(Map)
	// => 등록(내부에서 수행하는 쿼리문 - UPDATE문)
	@PostMapping("complain/complainUpdate")
	public String complainUpdateProcess(ComplainsVO complainVO){
		 complainService.updateComplain(complainVO);
		 return "redirect:/complain/complainInfo?complainNo="+complainVO.getComplainNo();
	}
	 
	// 삭제 - 처리 : URI - complainDelete / PARAMETER - Integer
	//             RETURN - 전체조회 다시 호출
	@ResponseBody
	@DeleteMapping("complain/complainDelete") // QueryString : @RequestParam
	public String complainDelete(@RequestParam Integer complainNo) {
		complainService.deleteComplain(complainNo);
		return "redirect:/complain/complainList";
	}
	 
	// 비밀번호 확인 - 페이지
	@ResponseBody
	@PostMapping("complainPwdCheck") 
	public String complainPwdCheck(@RequestParam(value = "complainNo", required = false) Integer complainNo, @RequestParam(value = "inputPwd", required = false) String complainPwd,  Model model) {
		ComplainsVO complainVO = new ComplainsVO();
		ComplainsVO findVO = complainService.getComplainPwd(complainNo);
		System.out.println(complainPwd);
		System.out.println(findVO);
		
		if(complainPwd.equals(findVO.getComplainPwd()) ) {
			System.out.println("비밀번호 같음");
			return "success";
		}else {
			System.out.println("비밀번호 다름");
			return "error";
		}
		
		
	}
	

	
}
