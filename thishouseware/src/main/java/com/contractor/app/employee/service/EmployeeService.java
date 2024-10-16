package com.contractor.app.employee.service;

import java.util.List;

public interface EmployeeService {

	List<DepartmentVO> getDepartmentList();

	int addEmployee(EmployeeVO empVO);

	EmployeeVO getEmployee(EmployeeVO empVO);

}
