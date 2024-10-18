package com.contractor.app.complain.mapper;

import java.util.List;

import com.contractor.app.complain.service.ComplainsVO;

public interface ComplainMapper {
	
	// 문의 전체조회
	public List<ComplainsVO> selectComplainAll();
	
	
	// 문의 단건조회
	public ComplainsVO selectComplainInfo(ComplainsVO complainVO);
	 
	// 문의 등록
	public int insertComplainInfo(ComplainsVO complainVO);
	
	// 문의 수정
	public int updateComplainInfo(ComplainsVO complainVO);
	 
	// 문의 삭제
	public int deleteComplainInfo(int complainNo);
	 
}
