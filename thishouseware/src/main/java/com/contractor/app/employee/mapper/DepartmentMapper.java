package com.contractor.app.employee.mapper;

import java.util.List;

import com.contractor.app.employee.service.DepartmentVO;

public interface DepartmentMapper {

	List<DepartmentVO> selectDepartmentAll();

	DepartmentVO selectDepartmentBydeptNo(int departmentNo);

}
