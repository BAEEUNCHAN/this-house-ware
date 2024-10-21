package com.contractor.app.complain.service;

import java.util.List;
import java.util.Map;

import com.contractor.app.reply.service.ReplysVO;

public interface ComplainService {

	// 문의사항 조회
	public List<ComplainsVO> complainList();
	
	// 문의사항 단건조회
	public ComplainsVO complainInfo(ComplainsVO complainVO);
	public List<ComplainsVO> complainDeptInfo(ComplainsVO complainVO);
	
	// 등록 
	public int insertComplain(ComplainsVO complainVO);
	
	// 수정
	public Map<String, Object> updateComplain(ComplainsVO complainVO);
	// 처리과정 업데이트
	public Map<String, Object> updateComplainProgress(ComplainsVO complainVO);
	 
	// 삭제 
	public int deleteComplain(int ComplainNo);
	
	
	
	
}
