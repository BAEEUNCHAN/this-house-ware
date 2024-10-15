package com.contractor.app.employee.service;

import lombok.Data;

@Data
public class DepartmentVO {
	int departmentNo; // 부서 번호
	String departmentName;// 부서 이름
	String departmentLevel; // 부서 레벨
	int upperDepartmentNo; // 상위 부서 번호
}
