package com.contractor.app.edms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contractor.app.edms.service.EdmsDocVO;
import com.contractor.app.edms.service.EdmsFormVO;
import com.contractor.app.edms.service.EdmsService;

@Controller
@RequestMapping("/edms")
public class EdmsController {

	private final EdmsService edmsService;

	@Autowired
	EdmsController(EdmsService edmsService) {
		this.edmsService = edmsService;
	}

	// 결재문서 전체조회
	@GetMapping("/edmsDocList")
	public void edmsDocList(Model model) {
		List<EdmsDocVO> list = edmsService.edmsDocList();
		model.addAttribute("edmsDocs", list);
		// return "edms/edmsDocList";
	}

	// 결재문서 단건조회
	@GetMapping("/edmsDocInfo")
	public void edmsDocInfo(EdmsDocVO edmsDocVO, Model model) {
		EdmsDocVO findVO = edmsService.edmsDocInfo(edmsDocVO);
		model.addAttribute("edmsDocs", findVO);
		// return "edms/edmsDocinfo";
	}

	// 결재문서 등록 - 페이지
	@GetMapping("/edmsInsert")
	public String empInsertForm() {
		return "edms/edmsInsert";
	}

	// 결재문서 등록 - 처리
	@PostMapping("/edmsInsert")
	public String edmsInsertProcess(EdmsDocVO edmsDocVO) {
		String edn = edmsService.edmsInsert(edmsDocVO);

		String url = null;

		if (edn == null) {
			// 정상적으로 등록된 경우
			url = "redirect:edmsDocInfo?edmsDocNo=" + edn;
			// "redirect:" 가 가능한 경우 GetMapping
		} else {
			// 등록되지 않은 경우
			url = "redirect:edmsDocList";
		}
		return url;
	}

	// 결재양식 전체조회
	@GetMapping("/edmsFormList")
	public void edmsFormList(Model model) {
		List<EdmsFormVO> list = edmsService.edmsFormList();
		model.addAttribute("edmsForms", list);
		// return "edms/edmsFormList";
	}

	// 결재양식 단건조회
	@GetMapping("/edmsFormInfo")
	public void empInfo(EdmsFormVO edmsFormVO, Model model) {
		EdmsFormVO findVO = edmsService.edmsFormInfo(edmsFormVO);
		model.addAttribute("edmsForms", findVO);
		// return "edms/edmsFormInfo";
	}

} // 끝
