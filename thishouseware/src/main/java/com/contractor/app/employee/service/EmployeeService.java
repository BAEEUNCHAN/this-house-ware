package com.contractor.app.employee.service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

	List<DepartmentVO> getDepartmentList();

	void addEmployee(EmployeeVO empVO);

	EmployeeVO getEmployee(EmployeeVO empVO);

}
