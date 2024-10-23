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

	// 문서함 문서 전체조회
	@GetMapping("/docList")
	public void DocJoinList(Model model) {
		List<DocJoinVO> list = docService.DocJoinList();
		model.addAttribute("docBoxs", list);
		// return "docBoxs/docList";
	}

	// 문서결과별 문서 조회
	@GetMapping("/docApprovalStatusList")
	public void getApprovalStatus(@RequestParam String approvalStatus, Model model) {
		List<DocJoinVO> list;

		switch (approvalStatus) {
		case "임시저장":
			list = docService.docApprovalStatusList("임시저장");
			break;
		case "결재완료":
			list = docService.docApprovalStatusList("결재완료");
			break;
		case "결재대기":
			list = docService.docApprovalStatusList("결재대기");
			break;
		case "결재수신":
			list = docService.docApprovalStatusList("결재수신");
			break;
		default:
			list = new ArrayList<>(); // 기본값 또는 에러 처리
			break;
		}

		model.addAttribute("docBoxs", list);
	}

	// 부서문서함 문서 전체조회
	@GetMapping("/docDeftList")
	public void DocDetfList(Model model) {
		List<DocJoinVO> list = docService.DocDetfList();
		model.addAttribute("docBoxs", list);
		// return "docBoxs/docList";
	}

	// 부서문서문서 조회
	@GetMapping("/docDeftStatusList")
	public void getDeftStatus(@RequestParam String approvalStatus, @RequestParam Integer departmentNo, Model model) {
		List<DocJoinVO> list = docService.docDeftStatusList(approvalStatus, departmentNo);
		model.addAttribute("docBoxs", list);
	}

}// 끝
