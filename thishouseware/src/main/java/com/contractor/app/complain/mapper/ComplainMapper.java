package com.contractor.app.complain.mapper;

import java.util.List;

import com.contractor.app.complain.service.ComplainsVO;
import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeVO;

public interface ComplainMapper {
	
	// 문의 전체조회
	public List<ComplainsVO> selectComplainAll();
	public List<ComplainsVO> selectComplainAll0To2();
	// 문의 전체조회(상황완료/보고완료만)
	public List<ComplainsVO> complainResultList();
	public List<ComplainsVO> resultList(ComplainsVO complainVO);
	
	// 문의 단건조회
	public ComplainsVO selectComplainInfo(ComplainsVO complainVO);
	public List<ComplainsVO> selectComplainDeptInfo(ComplainsVO complainVO); // Reply join
	 
	// 문의 등록
	public int insertComplainInfo(ComplainsVO complainVO);
	
	// 문의 수정
	public int updateComplainInfo(ComplainsVO complainVO);
	public int updateComplainProgress(ComplainsVO complainVO);
	 
	// 문의 삭제
	public int deleteComplainInfo(int complainNo);
	public int complainDelete(int complainNo);
	
	// 비밀번호 체크
	public int checkPwd(String complainPwd, int complainNo);
	
	public ComplainsVO getComplainPwd(int complainNo);
}
