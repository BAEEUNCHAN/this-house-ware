package com.contractor.app.schedule.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
@Data
public class LeavesVO {
	Integer leaveNo; // 휴가 번호
	String id; // 아이디
	String leaveStartDt; // 휴가 시작일시
	String leaveEndDt; // 휴가 종료일시
	Integer leaveDays; // 휴가 일수
	String leaveReason; // 휴가 사유
	String leaveType; // 휴가 종류
	/* n1 연차휴가
    n2 생리휴가
    n3 출산전후휴가
    n4 배우자출산휴가
    n5 가족돌봄휴가
    n6 병가
    n7 경조사휴가
    */
	
	public int calLeaveDays(String begin, String end) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		int diffDays = 0;
		try {
			Date startDt = format.parse(begin);
			Date endDt = format.parse(end);
			
			long diff = endDt.getTime() - startDt.getTime();
			diffDays = (int) (diff / (24 * 60 * 60 * 1000));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diffDays;
	}
}
