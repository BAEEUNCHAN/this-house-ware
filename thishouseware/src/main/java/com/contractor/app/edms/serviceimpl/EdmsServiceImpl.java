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
	} // 결재문서 전체 출력
	
	@Override
	public List<EdmsFormVO> edmsFormList() { 
		return edmsMapper.selectFormAllList();
	} // 결재영식 전체 출력

}// 끝
