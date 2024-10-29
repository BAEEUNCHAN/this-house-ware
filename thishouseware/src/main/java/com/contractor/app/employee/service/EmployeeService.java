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

	
}
