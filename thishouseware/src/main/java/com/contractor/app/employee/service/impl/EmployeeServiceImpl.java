package com.contractor.app.employee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.contractor.app.employee.mapper.DepartmentMapper;
import com.contractor.app.employee.mapper.EmployeeMapper;
import com.contractor.app.employee.service.DepartmentVO;
import com.contractor.app.employee.service.EmployeeService;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.util.EmployeeUtil;

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

	@Override
	public void addEmployee(EmployeeVO empVO) {
		employeeMapper.insertEmployee(empVO);
	}

	@Override
	public EmployeeVO getEmployee(EmployeeVO empVO) {
		empVO = employeeMapper.selectEmployee(empVO);
		empVO.setPositionName(EmployeeUtil.getPostionName(empVO.getPositionCode()));
		return empVO;
	}

	@Override
	public boolean modifyEmployee(EmployeeVO empVO) {
		return employeeMapper.modifyEmployee(empVO) == 1;
	}

	@Override
	public List<EmployeeVO> getEmployees() {
		return employeeMapper.selectEmployees();
	}

}
