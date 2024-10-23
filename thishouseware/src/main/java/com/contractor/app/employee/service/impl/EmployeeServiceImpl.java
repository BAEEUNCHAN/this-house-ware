package com.contractor.app.employee.service.impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.contractor.app.employee.mapper.AuthenticationMapper;
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
	
	private final AuthenticationMapper authenticationMapper;
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

	@Override
	public EmployeeVO getEmployeeByEmail(EmployeeVO empVO) {
		empVO = employeeMapper.selectEmployeeByEmail(empVO).get(0);
		empVO.setPositionName(EmployeeUtil.getPostionName(empVO.getPositionCode()));
		return empVO;
	}

	@Override
	public boolean modifyAuthentication(String id, String randomValue) {
		return authenticationMapper.updateAuthentication(id, randomValue) == 1;
	}

}
