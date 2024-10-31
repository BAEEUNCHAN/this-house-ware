package com.contractor.app.fileroom.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FolderFileVO {
	// 폴더 테이블
	private Integer folderNo; //폴더 번호
	private String folderName; //폴더 이름
	private Double folderSize; //폴더 크기
	private String authority; //권한
	private Integer fileRoomsNo; //자료실 번호
	private String shareChk; //공유 여부
	private String folderPath; //폴더 경로
	private String folderId; //폴더 아이디
	
	// 파일 테이블
	private Integer fileNo; //파일 번호
	private String fileName; //파일 이름
	private String extension; //확장자
	private String path; //경로
	private Double fileSize; //크기
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date uploadDT; //등록 날짜
	private String fileId; //파일 아이디 
	
	private FolderFileVO folderFileVO;
}
