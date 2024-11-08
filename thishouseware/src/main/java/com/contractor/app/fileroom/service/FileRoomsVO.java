package com.contractor.app.fileroom.service;

import java.util.List;

import lombok.Data;

@Data
public class FileRoomsVO {
	private Integer fileRoomsNo; //자료실 번호
	private String fileRoomsType; //자료실 유형
	private String fileRoomsName; //자료실 이름
	private Double capacity; //전체용량
	private String authority; //권한
	private String id; //아이디
	private Double usedCapacity; //사용량
	
	private FileRoomsVO fileRoomsVO;
	
	List<FolderVO> folders;
	
	List<FilesVO> files;
	
	List<FolderFileVO> folderFile;
	
	List<FileRoomsVO> fileRooms;
}
