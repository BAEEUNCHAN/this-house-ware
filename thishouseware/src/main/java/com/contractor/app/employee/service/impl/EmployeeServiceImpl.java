package com.contractor.app.employee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.contractor.app.employee.mapper.DepartmentMapper;
import com.contractor.app.employee.mapper.EmployeeMapper;
import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private final DepartmentMapper departmentMapper;
	private final EmployeeMapper employeeMapper;

	@Override
	public List<DepartmentVO> getDepartmentList() {
		return departmentMapper.selectDepartmentAll();
	}

}
