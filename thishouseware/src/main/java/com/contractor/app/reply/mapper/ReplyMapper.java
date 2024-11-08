package com.contractor.app.reply.mapper;

import java.util.List;

import com.contractor.app.complain.service.ComplainsVO;
import com.contractor.app.reply.service.ReplysVO;

public interface ReplyMapper {
	public List<ComplainsVO> replyList(int complainNo);
	
	public int insertReply(ReplysVO replyVO);
	
	public int deleteReplyInfo(int replyNo);
	
	// 댓글수정
	public int replyUpdate(ReplysVO replyVO);
	
}
