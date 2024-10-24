package com.contractor.app.employee.mapper;

import org.apache.ibatis.annotations.Param;

public interface AuthenticationMapper {

	int updateAuthentication(@Param("id") String id,@Param("randomValue") String randomValue);

}
