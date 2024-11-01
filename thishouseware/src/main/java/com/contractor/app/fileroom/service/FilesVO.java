package com.contractor.app.fileroom.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FilesVO {
	private Integer fileNo; // 파일 번호
	private String fileName; // 파일 이름
	private String extension; // 확장자
	private String path; // 경로
	private Double fileSize; // 크기
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date uploadDT; // 등록 날짜
	private String id; // 아이디
	private Integer folderNo; // 폴더 번호
	private Integer fileRoomsNo; // 자료실 번호
	private String attatch; // 첨부파일

	private FilesVO filesVO;

	// FolderVO
	private String folderName; // 폴더이름
}
