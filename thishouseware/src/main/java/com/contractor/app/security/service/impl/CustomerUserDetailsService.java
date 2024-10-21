package com.contractor.app.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.contractor.app.employee.mapper.EmployeeMapper;
import com.contractor.app.employee.service.EmployeeVO;
import com.contractor.app.security.service.LoginUserVO;
import com.contractor.app.util.EmployeeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService{

	private final EmployeeMapper empMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// DB 방식으로 정보를 가져오기 위해 Mapper을 사용
		EmployeeVO empVO = new EmployeeVO();
		empVO.setId(username);
		EmployeeVO employeeVO = empMapper.selectEmployee(empVO);
		
		// 해당 유저가 없을 시 
		if(employeeVO == null) {
			throw new UsernameNotFoundException(username);
		}
		// 해당 유저가 있는 경우 
		employeeVO.setPositionName(EmployeeUtil.getPostionName(employeeVO.getPositionCode()));
		// 세션상으로 스프링시큐리티가 올릴 것이다.
		return new LoginUserVO(employeeVO);
	}
	
	
}
