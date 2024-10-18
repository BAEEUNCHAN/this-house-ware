package com.contractor.app.complain.service;

import java.util.List;
import java.util.Map;

public interface ComplainService {

	// 문의사항 조회
	public List<ComplainsVO> complainList();
	
	// 문의사항 단건조회
	public ComplainsVO complainInfo(ComplainsVO complainVO);
	
	// 등록 
	public int insertComplain(ComplainsVO complainVO);
	
	// 수정
	public Map<String, Object> updateComplain(ComplainsVO complainVO);	
	 
	// 삭제 
	public int deleteComplain(int ComplainNo);
	
	/***********************************************************/
	// 문의 타입 0/1
	public List<ComplainsVO> complainType0();
	
	
	// 진행상황 : 0 ~ 4 조회
	public List<ComplainsVO> complainList0();
	public List<ComplainsVO> complainList1();
	public List<ComplainsVO> complainList2();
	public List<ComplainsVO> complainList3();
	public List<ComplainsVO> complainList4();
	
}
