package com.contractor.app.util;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;

import lombok.RequiredArgsConstructor;

/**
 * 스프링 싱글톤 방식 주의
 */
@Component
@RequiredArgsConstructor
public class EmpAuthUtil {
	
	private final EmployeeService empService;
	
	/**
	 * 스프링 시큐리티에 로그인한 대상의 유저 정보를 가져온다.
	 */
	public EmployeeVO getAuthEmp(Authentication auth) {
		EmployeeVO empVO = new EmployeeVO();
		empVO.setId(auth.getName());
		return empService.getEmployee(empVO);
	}
	
	/**
	 * 해당 단건 페이지에대하여 (단건 수정이나,단건 조회)
	 * 할 수 있는 권한이 있는지 채크한다.
	 * @param auth 스프링 시큐리티 세션 아이디
	 * @param id 가져오고자하는 데이터의 직원 아이디
	 * @return true 인증 성공, false 인증 실패
	 */
	public Boolean authChek(Authentication auth, String id) {
		
		EmployeeVO authEmp = getAuthEmp(auth);
		
		// 개인인증 먼저 체크
		if(personalCheck(auth, id)) {
			return true;
		}
		
		// 해당 직원이 조회대상의 팀장이거나, 본부장이거나, 부장인지 확인
		EmployeeVO emp = new EmployeeVO();
		emp.setId(id);
		emp = empService.getEmployee(emp);
		
		if(managerCheck(authEmp,emp)) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * 해당 직원이, 접근하고자하는 직원의 팀장, 본부장, 사장, 관리자 인지 확인한다.
	 */
	private boolean managerCheck(EmployeeVO manager, EmployeeVO emp) {
		// 팀장급 이상이 아니면 애초에 권한이 없으니 false를 반환한다.
		if(manager.getPositionCode().equals("a5") || 
				manager.getPositionCode().equals("a6")) {
			return false;
		}
		
		// 사장 또는 관리자라면 부서 를 채크하지 않는다.
		if(manager.getPositionCode().equals("a1") ||
				manager.getPositionCode().equals("a2")) {
			return true;
		}
		
		DepartmentVO empDepartmentVO 
			= empService.getDepartmentBydeptNo(emp.getDepartmentNo());
		// 관리자의 부서가 같거나 상위 부서인지 채크한다.
		if(manager.getDepartmentNo() == empDepartmentVO.getDepartmentNo()) {
			return true;
		}
		
		if(manager.getDepartmentNo() == empDepartmentVO.getUpperDepartmentNo()) {
			return true;
		}
		
		return false;
	}

	/**
	 * 로그인한 대상이 해당 정보가 자신의 정보인지 확인한다.
	 * @param 스프링 시큐리티 세션 아이디
	 * @param 가져오고자하는 데이터의 직원 아이디
	 * @return true 인증 성공, false 인증 실패
	 */
	private Boolean personalCheck(Authentication auth, String id) {
		if(auth.getName().equals(id)) {
			return true;
		}
		return false;
	}
	
	
}
