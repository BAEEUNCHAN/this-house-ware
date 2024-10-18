package com.contractor.app.reply.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.reply.mapper.ReplyMapper;
import com.contractor.app.reply.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {
	private ReplyMapper replyMapper;
	
	@Autowired
	public ReplyServiceImpl(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}
	
	
}
