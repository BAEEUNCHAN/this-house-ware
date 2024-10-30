package com.contractor.app.schedule.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.contractor.app.schedule.mapper.LeaveDetailMapper;
import com.contractor.app.schedule.service.LeaveDetailService;
import com.contractor.app.schedule.service.LeaveDetailVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaveDetailServiceImpl implements LeaveDetailService {
	private final LeaveDetailMapper leaveDetailMapper; 

	@Override
	public List<LeaveDetailVO> leaveDetailList() {		
		return leaveDetailMapper.listLeaveDetail();
	}

	@Override
	public LeaveDetailVO leaveDetailSelect(String id) {
		return leaveDetailMapper.getLeaveDetail(id);
	}
	
	@Override
	public boolean leaveDetailInsert(LeaveDetailVO leaveDetailVO) {
		return leaveDetailMapper.insertLeaveDetail(leaveDetailVO) == 1;
	}

	@Override
	public boolean leaveDetailUpdate(LeaveDetailVO leaveDetailVO) {
		return leaveDetailMapper.updateLeaveDetail(leaveDetailVO) == 1;
	}

	@Override
	public boolean leaveDetailDelete(String id) {
		return leaveDetailMapper.deleteLeaveDetail(id) == 1;
	}
	
}
