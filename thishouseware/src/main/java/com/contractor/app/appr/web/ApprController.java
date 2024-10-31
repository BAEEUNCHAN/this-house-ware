package com.contractor.app.appr.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contractor.app.appr.service.ApprFavoriteVO;
import com.contractor.app.appr.service.ApprLineVO;
import com.contractor.app.appr.service.ApprService;
import com.contractor.app.appr.service.ApprVO;
import com.contractor.app.appr.service.ApproverVO;

@Controller
@RequestMapping("/appr")
public class ApprController {

	private final ApprService apprService;

	@Autowired
	ApprController(ApprService apprService) {
		this.apprService = apprService;
	}

	// 결재선 전체조회
	@GetMapping("/apprLineList")
	public void apprLineList(Model model) {
		List<ApprLineVO> list = apprService.apprLineList();
		model.addAttribute("apprLines", list);
		// return "appr/apprLineList";
	}

	// 결재선 단건조회
	@GetMapping("/apprLineInfo") //
	public String apprLineInfo(ApprLineVO apprLineVO, Model model) {
		ApprLineVO findVO = apprService.apprLineInfo(apprLineVO);
		model.addAttribute("apprLines", findVO);
		return "appr/apprLineInfo";
	}

	// 결재선 추가 - 페이지(GET)
	@GetMapping("/apprLineInsert")
	public String apprLineForm() {
		return "appr/apprLineInsert";
	}

	// 결재선 추가 - 처리(POST)
	@PostMapping("/apprLineInsert")
	public String apprLineProcess(ApprLineVO apprLineVO) {
		int apprLine = apprService.apprLineInsert(apprLineVO);

		String url = null;

		if (apprLine > -1) {
			// 정상적으로 등록된 경우
			url = "redirect:apprLineInfo?approvalLineNo=" + apprLine;
			// "redirect:" 가 가능한 경우 GetMapping
		} else {
			// 등록되지 않은 경우
			url = "redirect:apprLineList";
		}
		return url;
	}

	// 결재선 삭제
	@GetMapping("/apprLineDelete")
	public String apprLineDelete(Integer approvalLineNo) {
		apprService.apprLineDelete(approvalLineNo);
		return "redirect:apprLineList";
	}

	// 결재선 수정 폼 조회 - GET 요청
	@GetMapping("/apprLineModify")
	public String apprLineUpdateForm(ApprLineVO apprLineVO, Model model) {
		ApprLineVO apprLineData = apprService.apprLineInfo(apprLineVO);
		model.addAttribute("apprLine", apprLineData);
		return "appr/apprLineModify";
	}

	// 결재선 수정 처리 - POST 요청
	@PostMapping("/apprLineModify")
	@ResponseBody // AJAX
	public Map<String, Object> apprLineUpdateAJAXJSON(@RequestBody ApprLineVO apprLineVO) {
		return apprService.apprLineUpdate(apprLineVO);
	}

	// 결재선 즐겨찾기 전체조회
	@GetMapping("/apprFavoriteList")
	public String apprFavoriteList(Model model) {
		List<ApprFavoriteVO> list = apprService.apprFavoriteList();
		model.addAttribute("apprFavorites", list);
		return "appr/apprFavoriteList";

	}

	// 즐겨찾기 단건조회
	@GetMapping("/favoriteInfo") //
	public String favoriteInfo(ApprFavoriteVO apprFavoriteVO, Model model) {
		ApprFavoriteVO findVO = apprService.favoriteInfo(apprFavoriteVO);
		model.addAttribute("apprFavorites", findVO);
		return "appr/favoriteInfo";
	}

	// 즐겨찾기 추가 - 페이지(GET)
	@GetMapping("/favoriteInsert")
	public String favoriteForm() {
		return "appr/favoriteInsert";
	}

	// 즐겨찾기 추가 - 처리(POST)
	@PostMapping("/favoriteInsert")
	public String favoriteProcess(ApprFavoriteVO apprFavoriteVO) {
		int favorite = apprService.favoriteInsert(apprFavoriteVO);

		String url = null;

		if (favorite > -1) {
			// 정상적으로 등록된 경우
			url = "redirect:favoriteInfo?favoriteNo=" + favorite;
			// "redirect:" 가 가능한 경우 GetMapping
		} else {
			// 등록되지 않은 경우
			url = "redirect:apprFavoriteList";
		}
		return url;
	}

	// 즐겨찾기 삭제
	@GetMapping("/favoriteDelete")
	public String favoriteDelete(Integer favoriteNo) {
		apprService.favoriteDelete(favoriteNo);
		return "redirect:apprFavoriteList";
	}

	// 결재자 정보 전체조회
	@GetMapping("/apprList")
	public void apprList(@RequestParam Integer approvalLineNo, Model model) {
		List<ApproverVO> list = apprService.apprList(approvalLineNo);
		System.out.println(list);
		model.addAttribute("approvers", list);
		// return "appr/apprList";
	}

	// 결재자 정보 단건조회
	@GetMapping("/apprInfo") //
	public String apprInfo(ApprVO apprVO, Model model) {
		ApprVO findVO = apprService.apprInfo(apprVO);
		model.addAttribute("approvers", findVO);
		return "appr/apprInfo";
	}

	// 결재자 추가 - 페이지(GET)
	@GetMapping("/approverInsert")
	public String apprForm() {
		return "appr/approverInsert";
	}

	// 결재자 추가 - 처리(POST)
	@PostMapping("/approverInsert")
	public String apprProcess(ApprVO apprVO) {
		int approver = apprService.apprInsert(apprVO);

		String url = null;

		if (approver > -1) {
			// 정상적으로 등록된 경우
			url = "redirect:apprInfo?approverNo=" + approver;
			// "redirect:" 가 가능한 경우 GetMapping
		} else {
			// 등록되지 않은 경우
			url = "redirect:apprList";
		}
		return url;
	}

	// 결재자 삭제
	@GetMapping("/approverDelete")
	public String apprDelete(Integer approverNo) {
		apprService.apprDelete(approverNo);
		return "redirect:apprList";
	}

	// 결재자 수정
	@GetMapping("/apprModify")
	public Map<String, Object> apprUpdateAJAXQueryString(ApprVO apprVO, Model model) {
		model.addAttribute("approvers", apprVO);
		return apprService.apprUpdate(apprVO);
	}

	@PostMapping("/apprModify")
	@ResponseBody // AJAX
	public Map<String, Object> apprUpdateAJAXJSON(@RequestBody ApprVO apprVO) {
		return apprService.apprUpdate(apprVO);
	}
}// 끝
