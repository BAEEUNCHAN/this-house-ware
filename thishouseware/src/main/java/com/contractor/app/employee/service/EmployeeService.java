package com.contractor.app.employee.service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

	List<DepartmentVO> getDepartmentList();
	
	List<EmployeeVO> getEmployeesWhereDept(EmployeeVO employeeVO);

	void addEmployee(EmployeeVO empVO);

	EmployeeVO getEmployee(EmployeeVO empVO);

	boolean modifyEmployee(EmployeeVO empVO);

	List<EmployeeVO> getEmployees();

	EmployeeVO getEmployeeByEmail(EmployeeVO empVO);

	boolean modifyAuthentication(String id,String randomValue);

	String canChangePw(String id, String authenticationsValue);

	boolean modifyPasswordByEmp(EmployeeVO empVO);

	DepartmentVO getDepartmentBydeptNo(int departmentNo);

	boolean resignEmp(String id);

	//은찬 추가 결재자 리스트에 부서별 직원 조회	
	List<EmployeeVO> getEmpDept(int departmentNo);
	
	// 수민 : 상위 부서 번호 가져오기
	List<DepartmentVO> selectUpperDepartmentNo(int upperDepartmentNo);
	

	
}
