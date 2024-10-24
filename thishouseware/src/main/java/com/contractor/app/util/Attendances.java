package com.contractor.app.util;

public class Attendances {
	
	// 직급 코드로 직급을 반환해준다.
	public static String getAttendanceCode(String attendanceCode) {
		switch (attendanceCode) {
			case "j1":
				return "출근";
			case "J2":
				return "퇴근";
			case "J3":
				return "외출";
			case "J4":
				return "복귀";
		}
		return null;
	}
}
