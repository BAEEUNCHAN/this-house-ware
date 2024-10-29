package com.contractor.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.contractor.app.employee.service.EmployeeVO;

import lombok.Getter;

@Getter
public class LoginUserVO implements UserDetails{
	
	private EmployeeVO empVO;
	
	public LoginUserVO(EmployeeVO empVo) {
		this.empVO = empVo;
	}
	
	/**
	 * 권한들을 리턴한다.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		auths.add(new SimpleGrantedAuthority(empVO.getPositionCode()));
		// 부서이름은 join으로 가져와 줘야한다.
		auths.add(new SimpleGrantedAuthority(empVO.getDepartmentNo()+""));
		return auths;
	}

	@Override
	public String getPassword() {
		return empVO.getPassword();
	}

	@Override
	public String getUsername() {
		return empVO.getId();
	}
	
	/*
	 * 아래 boolean 타입의 메서드들중 하나라도
	 * false를 반환하면  로그인이 안되기 때문에
	 * 임시로 true로 봐꿔준다.
	 * */

	/**
	 * 계정 만료 여부
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 계정 잠금 여부
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 계정 패스워드 만료 여부
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 계정 사용여부 
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
