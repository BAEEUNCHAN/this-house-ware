package com.contractor.app.util;

public class EmployeeUtil {
	
	// 직급 코드로 직급을 반환해준다.
	public static String getPostionName(String positionCode) {
		switch (positionCode) {
			case "a1":
				return "사장";
			case "a2":
				return "관리자";
			case "a3":
				return "본부장";
			case "a4":
				return "팀장";
			case "a5":
				return "사원";
			case "a6":
				return "인턴";
		}
		
		return null;
	}
}
