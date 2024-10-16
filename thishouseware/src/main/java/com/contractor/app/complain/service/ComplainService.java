package com.contractor.app.complain.service;

import java.util.List;

public interface ComplainService {

	// 문의사항 조회
	public List<ComplainsVO> complainList();

	// 문의사항 단건조회
	public ComplainsVO complainInfo(ComplainsVO complainVO);
	
	// 등록 
	public int insertComplain(ComplainsVO complainVO);
	 
	// 삭제 
	//public int deleteComplain(int ComplainNo);
	
}
