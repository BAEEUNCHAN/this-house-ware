package com.contractor.app.schedule.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class LeaveVO {
	Integer leaveNo; 	// 휴가 번호
	String id; 			// 아이디
	 //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
	String start; // 휴가 시작일시
	 //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
	String end; // 휴가 종료일시
	Integer leaveDays; // 휴가 일수
	String content; // 휴가 사유 = (leave_reason)
	String title; // 휴가 종류 = (leave_type)
	String color; // 휴가 색
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
