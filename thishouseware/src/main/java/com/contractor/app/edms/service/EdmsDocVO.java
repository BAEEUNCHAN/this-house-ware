package com.contractor.app.edms.service;

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
public class EdmsDocVO {
	private String edmsDocNo; // 결재문서 번호 Primary Key / not null
	private String id; // 아이디 Foreign Key / not null 
	private String title; // 제목 / not null
	private String content; // 내용
	private String fileName; // 문서파일명
	private String attatch;// 첨부파일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date submitDt; // 상신일시
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approvalDt;// 결재일시
	private String approvalStatus; // 결재상태
	private String edmsFormNo; // 결재양식 번호 / not null
	private String shareStatus; // 공유폴더 여부 / not null
	private String shareFolderNo; // 공유 폴더 번호 / not null
	private boolean important; // 중요문서
	

}
