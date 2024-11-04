package com.contractor.app.doc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.contractor.app.doc.service.DocJoinVO;
import com.contractor.app.edms.service.EdmsDocVO;

public interface DocMapper {

	// 개인문서함
	// 문서함 문서 개인전체조회
	public List<DocJoinVO> selectDocJoinList(String id);
	// 문서결과별 문서 조회
	public List<EdmsDocVO> getDocumentsByStatusAndUserId(@Param("approvalStatus") String approvalStatus, @Param("userId") String userId);


	// 부서문서함
	// 문서함 문서 부서전체조회
	public List<DocJoinVO> selectDocDeptList(@Param("departmentNo") int departmentNo);
	// 부서문서 결재완료 조회
	public List<DocJoinVO> getDeptStatus(String approvalStatus, int departmentNo);
	// 부서문서 중요문서 업데이트
	public void updateDeptImportant(@Param("edmsDocNo")String edmsDocNo, 
									@Param("important") String important);
	// 부서문서 중요문서 조회
	public List<DocJoinVO> getDeptImportant(String important, int departmentNo);
}