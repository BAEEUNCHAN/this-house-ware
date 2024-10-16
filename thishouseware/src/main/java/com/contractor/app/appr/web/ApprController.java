package com.contractor.app.appr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contractor.app.appr.service.ApprFavoriteVO;
import com.contractor.app.appr.service.ApprLineVO;
import com.contractor.app.appr.service.ApprService;
import com.contractor.app.appr.service.ApprVO;

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

	// 결재선 즐겨찾기 전체조회
	@GetMapping("/apprFavoriteList")
	public void apprFavoriteList(Model model) {
		List<ApprFavoriteVO> list = apprService.apprFavoriteList();
		model.addAttribute("apprFavorites", list);
		// return "appr/apprFavoriteList";

	}

	// 결재자 정보 전체조회
	@GetMapping("/apprList")
	public void apprList(Model model) {
		List<ApprVO> list = apprService.apprList();
		model.addAttribute("apprs", list);
		// return "appr/apprFavoriteList";
	}

}// 끝
