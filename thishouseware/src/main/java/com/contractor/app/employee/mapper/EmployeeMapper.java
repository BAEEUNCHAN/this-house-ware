package com.contractor.app.employee.mapper;

import com.contractor.app.employee.service.EmployeeVO;

public interface EmployeeMapper {

	int insertEmployee(EmployeeVO empVO);

	EmployeeVO selectEmployee(EmployeeVO empVO);

}
