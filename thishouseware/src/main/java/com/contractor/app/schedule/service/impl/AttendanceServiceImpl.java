package com.contractor.app.schedule.service.impl;

import org.springframework.stereotype.Service;

import com.contractor.app.schedule.mapper.AttendanceMapper;
import com.contractor.app.schedule.service.AttendanceService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{

	AttendanceMapper attendanceMapper;
}
