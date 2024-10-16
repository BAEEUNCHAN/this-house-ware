package com.contractor.app.employee.mapper;

import java.util.Map;

import com.contractor.app.employee.service.EmployeeVO;

public interface EmployeeMapper {

	Map<String, Object> insertEmployee(EmployeeVO empVO);

	EmployeeVO selectEmployee(EmployeeVO empVO);

}
