package com.contractor.app.common.mapper;

import java.util.List;

import com.contractor.app.common.service.CommonCodeVO;

public interface CommonMapper {
	public List<CommonCodeVO> selectCommonCode(String commonCode);
}
