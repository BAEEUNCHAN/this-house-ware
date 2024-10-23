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


}// 끝
