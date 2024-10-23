package com.contractor.app.employee.mapper;

import java.util.List;

import com.contractor.app.employee.service.EmployeeVO;

public interface EmployeeMapper {

	void insertEmployee(EmployeeVO empVO);

	EmployeeVO selectEmployee(EmployeeVO empVO);

	int modifyEmployee(EmployeeVO empVO);

	List<EmployeeVO> selectEmployees();

	List<EmployeeVO> selectEmployeeByEmail(EmployeeVO empVO);

}
