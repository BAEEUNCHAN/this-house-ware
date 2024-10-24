package com.contractor.app.complain.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.complain.mapper.ComplainMapper;
import com.contractor.app.complain.service.ComplainService;
import com.contractor.app.complain.service.ComplainsVO;
import com.contractor.app.reply.mapper.ReplyMapper;
import com.contractor.app.reply.service.ReplysVO;


@Service
public class ComplainServiceImpl implements ComplainService {
	private ComplainMapper complainMapper;
	private ReplyMapper replyMapper;
	
	@Autowired
	public ComplainServiceImpl(ComplainMapper complainMapper, ReplyMapper replyMapper) {
		this.complainMapper = complainMapper;
		this.replyMapper = replyMapper;
	}
	
	// 문의 전체조회
	@Override
	public List<ComplainsVO> complainList() {
		return complainMapper.selectComplainAll();
	}
	// 문의 상황완료 보고완료만 전체조회
	@Override
	public List<ComplainsVO> complainResultList() {
		return complainMapper.complainResultList();
	}
	@Override
	public List<ComplainsVO> resultList(ComplainsVO complainVO) {
		return complainMapper.resultList(complainVO);
	}
	
	// 문의 단건조회(complain)
	 @Override public ComplainsVO complainInfo(ComplainsVO complainVO) {
		 return  complainMapper.selectComplainInfo(complainVO);
	}
	// 문의 단건조회(department)
	@Override
	public List<ComplainsVO> complainDeptInfo(ComplainsVO complainVO) {
		return complainMapper.selectComplainDeptInfo(complainVO);
	}
	
	 // 문의 등록
	 @Override
	 public int insertComplain(ComplainsVO complainVO) {
		 int result = complainMapper.insertComplainInfo(complainVO);
		 return result == 1 ? complainVO.getComplainNo() : -1;
	 }
	 
	 // 문의 수정
	 @Override
	public Map<String, Object> updateComplain(ComplainsVO complainVO) {
		 Map<String, Object> map = new HashMap<>();
		 boolean isSuccessed = false;
		 
		 int result = complainMapper.updateComplainInfo(complainVO);
		 if(result == 1) {
			 isSuccessed = true;
		 }
		 
		 map.put("result", isSuccessed);
		 map.put("target", complainVO);
		 return map;
		 
	}
	 @Override
	public Map<String, Object> updateComplainProgress(ComplainsVO complainVO) {
		 Map<String, Object> map = new HashMap<>();
		 boolean isSuccessed = false;
		 
		 int result = complainMapper.updateComplainProgress(complainVO);
		 if(result == 1) {
			 isSuccessed = true;
		 }
		 
		 map.put("result", isSuccessed);
		 map.put("target", complainVO);
		 return map;
	}
	 
	 // 문의 삭제
	 @Override public int deleteComplain(int ComplainNo) {
		 return complainMapper.deleteComplainInfo(ComplainNo); }
	 
	
}
