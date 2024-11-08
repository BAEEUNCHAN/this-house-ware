package com.contractor.app.reply.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.complain.service.ComplainsVO;
import com.contractor.app.reply.mapper.ReplyMapper;
import com.contractor.app.reply.service.ReplyService;
import com.contractor.app.reply.service.ReplysVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	private ReplyMapper replyMapper;
	
	@Autowired
	public ReplyServiceImpl(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}
	
	@Override
	public List<ComplainsVO> replyList(int complainNo) {
		return replyMapper.replyList(complainNo);
	}
	
	@Override
	public int insertReply(ReplysVO replyVO) {
		int result = replyMapper.insertReply(replyVO);
		return result == 1 ? replyVO.getComplainNo() : -1;
	}
	
	@Override
	public int replyDelete(int replyNo) {
		return replyMapper.deleteReplyInfo(replyNo) ;
	}
	
	// 댓글수정
	@Override
	public Map<String, Object> replyUpdate(ReplysVO replyVO) {
		Map<String, Object> map = new HashMap<>();
		 boolean isSuccessed = false;
		 
		 int result = replyMapper.replyUpdate(replyVO);
		 if(result == 1) {
			 isSuccessed = true;
		 }
		 
		 map.put("result", isSuccessed);
		 map.put("target", replyVO);
		 return map;
	}
}
