package com.contractor.app.reply.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.contractor.app.company.service.CompanyService;
import com.contractor.app.complain.service.ComplainService;
import com.contractor.app.reply.service.ReplyService;

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
	
	
	
	
	
	
	
}
