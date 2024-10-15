package com.contractor.app.fileroom.service;

import lombok.Data;

@Data
public class FolderVO {
	private Integer folderNo; //폴더 번호
	private String folderName; //폴더 이름
	private Double folderSize; //폴더 크기
	private String authority; //권한
	private Integer fileRoomsNo; //자료실 번호
	private Integer shareChk; //공유 여부
	private String folderPath; //폴더 경로
	private String id; //아이디
}
