package com.contractor.app.reply.service;

import java.util.List;

import com.contractor.app.complain.service.ComplainsVO;

public interface ReplyService {
	
	public List<ComplainsVO> replyList(int complainVO);
	
	public int insertReply(ReplysVO replyVO);

}
