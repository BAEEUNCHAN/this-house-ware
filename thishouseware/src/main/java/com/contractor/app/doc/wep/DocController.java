package com.contractor.app.doc.wep;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contractor.app.doc.service.DocJoinVO;
import com.contractor.app.doc.service.DocService;

@Controller
@RequestMapping("/docBox")
public class DocController {

	private final DocService docService;

	@Autowired
	DocController(DocService docService) {
		this.docService = docService;
	}

	// 개인문서함 문서 전체조회
	@GetMapping("/docList")
	public void DocJoinList(@RequestParam String id, Model model) {
		List<DocJoinVO> list = docService.DocJoinList(id);
		model.addAttribute("docBoxs", list);
		// return "docBoxs/docList";
	}
	
	// 문서결과별 문서 조회
	@GetMapping("/docApprovalStatusList")
	public void getApprovalStatus(@RequestParam String approvalStatus, String id, Model model) {
		List<DocJoinVO> list;

		switch (approvalStatus) {
		case "임시저장":
			list = docService.docApprovalStatusList("임시저장",id);
			break;
		case "결재완료":
			list = docService.docApprovalStatusList("결재완료",id);
			break;
		case "결재대기":
			list = docService.docApprovalStatusList("결재대기",id);
			break;
		case "결재수신":
			list = docService.docApprovalStatusList("결재수신",id);
			break;
		default:
			list = new ArrayList<>(); // 기본값 또는 에러 처리
			break;
		}

		model.addAttribute("docBoxs", list);
	}

	// 부서문서함 문서 전체조회
	@GetMapping("/docDeptList")
	public String DocDeptList(@RequestParam Integer departmentNo, Model model) {
	    List<DocJoinVO> list = docService.DocDeptList(departmentNo); 
	    model.addAttribute("docBoxs", list);
	    return "docBox/docDeptList";
	}
	
	

	// 부서문서 결재완료 조회
	@GetMapping("/docDeptStatusList")
	public String getDeftStatus(@RequestParam String approvalStatus, @RequestParam Integer departmentNo, Model model) {
		List<DocJoinVO> list = docService.docDeptStatusList(approvalStatus, departmentNo);
		model.addAttribute("docBoxs", list);
		return "docBox/docDeptStatusList";
	}

}// 끝
