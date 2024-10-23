package com.contractor.app.doc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.doc.mapper.DocMapper;
import com.contractor.app.doc.service.DocJoinVO;
import com.contractor.app.doc.service.DocService;

@Service
public class DocServiceImpl implements DocService {
	private DocMapper docMapper;

	@Autowired
	DocServiceImpl(DocMapper docMapper) {
		this.docMapper = docMapper;
	}

	@Override
	public List<DocJoinVO> DocJoinList() {
		return docMapper.selectDocJoinList();
	} // 문서함 문서 전체조회	

	@Override
	public List<DocJoinVO> docApprovalStatusList(String approvalStatus) {
		return docMapper.getApprovalStatus(approvalStatus);
	} // 문서결과별 문서 조회	

	@Override
	public List<DocJoinVO> DocDetfList() {
		return docMapper.selectDocDeftList();
	} // 부서별 문서 전체조회
	
	@Override
	public List<DocJoinVO> docDeftStatusList(String approvalStatus, Integer departmentNo) {
	    return docMapper.getDeftStatus(approvalStatus, departmentNo); 
	} // 부서문서 조회
	
}// 끝
