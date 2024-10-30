package com.contractor.app.doc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.doc.mapper.DocMapper;
import com.contractor.app.doc.service.DocJoinVO;
import com.contractor.app.doc.service.DocService;
import com.contractor.app.util.DepartmentNameUtil;

@Service
public class DocServiceImpl implements DocService {
	private DocMapper docMapper;

	@Autowired
	DocServiceImpl(DocMapper docMapper) {
		this.docMapper = docMapper;
	}

	@Override
	public List<DocJoinVO> DocJoinList(String id) {
		List<DocJoinVO> list = docMapper.selectDocJoinList(id);
		/*
		 * for (DocJoinVO docJoinVO : list) {
		 * docJoinVO.setDepartmentName(DepartmentNameUtil.getDepartmentName(docJoinVO.
		 * getDepartmentNo())); }
		 */
		for (int i = 0; i < list.size(); i++) {
			int deptNo = list.get(i).getDepartmentNo();
			list.get(i).setDepartmentName(DepartmentNameUtil.getDepartmentName(deptNo));
		}
		return list;
	} // 문서함 문서 전체조회

	@Override
	public List<DocJoinVO> docApprovalStatusList(String approvalStatus, String id) {
		List<DocJoinVO> list = docMapper.getApprovalStatus(approvalStatus, id);
		for (int i = 0; i < list.size(); i++) {
			int deptNo = list.get(i).getDepartmentNo();
			list.get(i).setDepartmentName(DepartmentNameUtil.getDepartmentName(deptNo));
		}
		return list;
	} // 문서결과별 문서 조회

	@Override
	public List<DocJoinVO> DocDeptList(int departmentNo) {
		List<DocJoinVO> list = docMapper.selectDocDeptList(departmentNo);
		for (int i = 0; i < list.size(); i++) {
			int deptNo = list.get(i).getDepartmentNo();
			list.get(i).setDepartmentName(DepartmentNameUtil.getDepartmentName(deptNo));
		}
		return list;
	} // 부서별 문서 전체조회

	@Override
	public List<DocJoinVO> docDeptStatusList(String approvalStatus, int departmentNo) {
		List<DocJoinVO> list = docMapper.getDeptStatus(approvalStatus, departmentNo);
		for (int i = 0; i < list.size(); i++) {
			int deptNo = list.get(i).getDepartmentNo();
			list.get(i).setDepartmentName(DepartmentNameUtil.getDepartmentName(deptNo));
		}
		return list;
	} // 부서문서 결재완료 조회

	@Override
	public void docUpdateImportant(String edmsDocNo, String important) {
		docMapper.updateDeptImportant(edmsDocNo, important);
	} // 부서문서 중요문서 업데이트

	@Override
	public List<DocJoinVO> docDeptImportantList(String important, int departmentNo) {
		List<DocJoinVO> list = docMapper.getDeptImportant(important, departmentNo);
		for (int i = 0; i < list.size(); i++) {
			int deptNo = list.get(i).getDepartmentNo();
			list.get(i).setDepartmentName(DepartmentNameUtil.getDepartmentName(deptNo));
		}
		return list;
	} // 부서문서 중요문서

}// 끝
