package com.contractor.app.util;

public class DepartmentNameUtil {
	// 부서번호에 따른 부서 이름은 반환한다.
	public static String getDepartmentName(int num) {
		switch (num) {
			case 1:
				return "기업";
			case 2:
				return "경영지원본부";
			case 3:
				return "영업본부";
			case 4:
				return "기술지원본부";
			case 5:
				return "총무회계팀";
			case 6:
				return "인사관리팀";
			case 7:
				return "업무지원팀";
			case 8:
				return "기업영업팀";
			case 9:
				return "제휴팀";
			case 10:
				return "해외영업팀";
			case 11:
				return "기술지원팀";
			case 12:
				return "디자인팀";
			case 13:
				return "상품개발팀";
		}
		
		return null;
	}
}
