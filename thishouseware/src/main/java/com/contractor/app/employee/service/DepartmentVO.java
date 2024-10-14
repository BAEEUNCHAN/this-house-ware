package com.contractor.app.employee.service;

import lombok.Data;

@Data
public class DepartmentVO {
	int departmentNo;
	String departmentName;
	String departmentLevel;
	int upperDepartmentNo;
}
