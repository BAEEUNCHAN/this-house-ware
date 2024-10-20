package com.contractor.app.reply.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.contractor.app.company.service.CompanyService;
import com.contractor.app.complain.service.ComplainService;
import com.contractor.app.complain.service.ComplainsVO;
import com.contractor.app.reply.service.ReplyService;
import com.contractor.app.reply.service.ReplysVO;

@Controller
public class ReplyController {
	private ComplainService complainService;
	private ReplyService replyService;
	private CompanyService companyService;
	
	@Autowired
	public ReplyController(ComplainService complainService, ReplyService replyService, CompanyService companyService) {
		this.complainService = complainService;
		this.replyService = replyService;
		this.companyService = companyService;
	}
	
	// 문의 전체조회 : URI - replyList / return - reply/replyList
	@GetMapping("replyList")
	public String replyList(ComplainsVO complainVO, Model model) {
		List<ComplainsVO> list = complainService.complainList();
		model.addAttribute("complains", list);
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		model.addAttribute("complain", findVO);
		return "reply/replyList";
	}
	
	// reply 단건 + 문의답변
	@GetMapping("replyInfo")
	public String replyInfo(ComplainsVO complainVO, Model model, ReplysVO replyVO) {
		ComplainsVO findVO = complainService.complainInfo(complainVO);
		// employees 테이블 List / reply + employees 등록
		model.addAttribute("complain", findVO);
		return "reply/replyInfo";
	}
	
	
	
}
