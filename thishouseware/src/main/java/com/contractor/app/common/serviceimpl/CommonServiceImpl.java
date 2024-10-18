package com.contractor.app.common.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.common.mapper.CommonMapper;
import com.contractor.app.common.service.CommonCodeService;
import com.contractor.app.common.service.CommonCodeVO;

@Service
public class CommonServiceImpl implements CommonCodeService{
	private CommonMapper commonMapper;
	
	@Autowired // 생성자 주입
	public CommonServiceImpl(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}
	
	@Override
	public List<CommonCodeVO> selectCommonCode(String commonCode) {
		return commonMapper.selectCommonCode(commonCode);
	}

}
