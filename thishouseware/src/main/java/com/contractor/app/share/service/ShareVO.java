package com.contractor.app.share.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ShareVO {
	private String shareFolderNo; // 공유 폴더번호
	private String id; // 아이디
	private String shareDepartment; // 공유 부서
	private String shareEmployees; // 공유 직원
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDt; // 생성일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDt; // 수정일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finalAccessDt; // 최근 접근일자
	private String folderDescription; // 폴더설명
	private String accessRestription; // 접근제한 설정
	private String folderPath; // 폴더 경로
	private String folderSize; // 폴더 크기
	private String folderStatus; // 폴더 상태
	private String docBoxNo; //문서함 번호


}
