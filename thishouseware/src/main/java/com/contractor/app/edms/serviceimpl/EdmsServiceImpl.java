package com.contractor.app.edms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.edms.mapper.EdmsMapper;
import com.contractor.app.edms.service.EdmsDocVO;
import com.contractor.app.edms.service.EdmsFormVO;
import com.contractor.app.edms.service.EdmsService;

@Service
public class EdmsServiceImpl implements EdmsService {
	private EdmsMapper edmsMapper;

	@Autowired
	EdmsServiceImpl(EdmsMapper edmsMapper) {
		this.edmsMapper = edmsMapper;
	}
	

	@Override
	public List<EdmsDocVO> edmsDocList() { 
		return edmsMapper.selectDocAllList();
	} // 결재문서 전체조회
	
	@Override
	public EdmsDocVO edmsDocInfo(EdmsDocVO edmsDocVO) {
		return edmsMapper.selectEdmsDocInfo(edmsDocVO);
	} // 결재문서 단건조회
	
	@Override
	public String edmsInsert(EdmsDocVO edmsDocVO) {
		int result = edmsMapper.insertEdmsInfo(edmsDocVO);
		
		return result == 1 ? edmsDocVO.getEdmsDocNo() : null ;
	} // 결재문서 단건등록
	
	
	
	
	
	
	
	@Override
	public List<EdmsFormVO> edmsFormList() { 
		return edmsMapper.selectFormAllList();
	} // 결재양식 전체조회
	
	@Override
	public EdmsFormVO edmsFormInfo(EdmsFormVO edmsFormVO) {
		return edmsMapper.selectEdmsFormInfo(edmsFormVO);
	} // 결재문서 단건조회

}// 끝
