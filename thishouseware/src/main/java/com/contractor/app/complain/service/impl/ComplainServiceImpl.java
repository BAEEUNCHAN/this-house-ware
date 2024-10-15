package com.contractor.app.complain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.company.service.ComplainsVO;
import com.contractor.app.complain.mapper.ComplainMapper;
import com.contractor.app.complain.service.ComplainService;

@Service
public class ComplainServiceImpl implements ComplainService {
	private ComplainMapper complainMapper;
	
	@Autowired
	public ComplainServiceImpl(ComplainMapper complainMapper) {
		this.complainMapper = complainMapper;
	}
	
	// 문의 전체조회
	@Override
	public List<ComplainsVO> complainList() {
		return complainMapper.selectComplainAll();
	}
	
	
	 // 문의 단건조회
	 //@Override public ComplainsVO complainInfo(ComplainsVO complainVO) { return
	 //complainMapper.selectComplainInfo(complainVO); }
	 
	
	 // 문의 등록
	 @Override
	 public int insertComplain(ComplainsVO complainVO) {
		 int result = complainMapper.insertComplainInfo(complainVO);
		 return result == 1 ? complainVO.getComplainNo() : -1;
	 }
	 
	 // 문의 삭제
	 //@Override public int deleteComplain(int ComplainNo) { return
	 //complainMapper.deleteComplainInfo(ComplainNo); }
	 
	
}
