package com.contractor.app.schedule.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contractor.app.schedule.mapper.LeaveMapper;
import com.contractor.app.schedule.service.LeaveService;
import com.contractor.app.schedule.service.LeaveVO;

@Service
public class LeaveServiceImpl implements LeaveService {
	
	@Autowired
	private LeaveMapper leaveMapper;

	@Override
	public List<Map<String, Object>> leaveListAll() {
		List<Map<String, Object>> leaves = leaveMapper.listLeave();
		return leaves;
	}
	
	@Override
	public List<LeaveVO> getLeaveListAll() {
		return leaveMapper.getLeaveListAll();
	}
	
	@Override
	public List<LeaveVO> leaveListAllWhereDepartmentNo(int departmentNo) {
		return leaveMapper.leaveListAllWhereDepartmentNo(departmentNo);
	}
	
	@Override
	public List<LeaveVO> leaveListWhereId(String id) {
		return leaveMapper.leaveListWhereId(id);
	}
	
	@Override
	public int leaveUpdate(LeaveVO leaveVO) {
		int result = leaveMapper.leaveUpdate(leaveVO);
		return result == 1 ? leaveVO.getLeaveNo() : -1;
	}
	
	@Override
	public List<Map<String, Object>> leaveList(String id) {
		List<Map<String, Object>> leaves = leaveMapper.selectLeave(id);
		return leaves;
	}
	
	@Override
	public int leaveInsert(LeaveVO leaveVO) {
		int days = leaveVO.calLeaveDays(leaveVO.getStart(), leaveVO.getEnd());
		leaveVO.setLeaveDays(days);
		int result = leaveMapper.insertLeave(leaveVO);
		return result == 1 ? leaveVO.getLeaveNo() : -1;
	}

	@Override
	public boolean leaveDelete(Integer leaveNo) {
		return leaveMapper.deleteLeave(leaveNo) == 1;
	}
	
}
