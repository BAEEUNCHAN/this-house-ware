package com.contractor.app.complain.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.complain.mapper.ComplainMapper;
import com.contractor.app.complain.service.ComplainService;
import com.contractor.app.complain.service.ComplainsVO;


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
	 @Override public ComplainsVO complainInfo(ComplainsVO complainVO) {
		 return  complainMapper.selectComplainInfo(complainVO);
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
	 
	 // 문의 삭제
	 @Override public int deleteComplain(int ComplainNo) {
		 return complainMapper.deleteComplainInfo(ComplainNo); }
	 
	 /***********************************************************/
	 // 문의타입
	 @Override
	public List<ComplainsVO> complainType0() {
		return complainMapper.selectComplainType0();
	}
	 
	 /***********************************************************/
	 
	 // 진행상황 : 0 조회
	 @Override
	public List<ComplainsVO> complainList0() {
		return complainMapper.selectComplainAll0();
	}
	@Override
	public List<ComplainsVO> complainList1() {
		return complainMapper.selectComplainAll1();
	}
	@Override
	public List<ComplainsVO> complainList2() {
		return complainMapper.selectComplainAll2();
	}
	@Override
	public List<ComplainsVO> complainList3() {
		return complainMapper.selectComplainAll3();
	}
	@Override
	public List<ComplainsVO> complainList4() {
		return complainMapper.selectComplainAll4();
	}
	
}
