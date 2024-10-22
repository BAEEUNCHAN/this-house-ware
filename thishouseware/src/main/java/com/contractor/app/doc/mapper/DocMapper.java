package com.contractor.app.doc.mapper;

import java.util.List;

import com.contractor.app.doc.service.DocJoinVO;
import com.contractor.app.doc.service.DocVO;

public interface DocMapper {

	// 개인문서함
	// 문서함 문서 개인전체조회
	public List<DocJoinVO> selectDocJoinList();
	
	//부서문서함
	
}
