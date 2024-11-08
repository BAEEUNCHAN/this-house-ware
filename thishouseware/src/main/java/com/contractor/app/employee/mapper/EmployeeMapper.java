package com.contractor.app.employee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.contractor.app.employee.service.EmployeeVO;

public interface EmployeeMapper {

	void insertEmployee(EmployeeVO empVO);

	EmployeeVO selectEmployee(EmployeeVO empVO);

	int updateEmployee(EmployeeVO empVO);

	List<EmployeeVO> selectEmployees();

	List<EmployeeVO> selectEmployeeByEmail(EmployeeVO empVO);

	String canChangePwFunc(@Param("id") String id,@Param("authenticationsValue") String authenticationsValue);

	int updateEmployeeByEmp(EmployeeVO empVO);

	List<EmployeeVO> selectEmployeesWhereDept(EmployeeVO employeeVO);

	boolean updateEmployeeForResign(String id);

	//은찬 추가 결재자 리스트에 부서별 직원 조회	
	List<EmployeeVO> selectEmpByDept(int departmentNo);
}
