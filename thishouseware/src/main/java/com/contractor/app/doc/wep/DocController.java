package com.contractor.app.doc.wep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}// 끝
