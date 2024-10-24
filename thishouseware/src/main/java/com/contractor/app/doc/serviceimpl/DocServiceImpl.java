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
	public List<DocJoinVO> DocJoinList(String id) {
		return docMapper.selectDocJoinList(id);
	} // 문서함 문서 전체조회	

	@Override
	public List<DocJoinVO> docApprovalStatusList(String approvalStatus,String id) {
		return docMapper.getApprovalStatus(approvalStatus,id);
	} // 문서결과별 문서 조회	

	@Override
	public List<DocJoinVO> DocDeptList(int departmentNo) {
		return docMapper.selectDocDeptList(departmentNo);
	} // 부서별 문서 전체조회
	
	@Override
	public List<DocJoinVO> docDeptStatusList(String approvalStatus, Integer departmentNo) {
	    return docMapper.getDeptStatus(approvalStatus, departmentNo); 
	} // 부서문서 결재완료 조회
	
}// 끝
