package com.contractor.app.fileroom.service;

import lombok.Data;

@Data
public class ShareUserVO {
	private Integer userNo; //사용자 번호
	private Integer authority; //권한
	private String id; //아이디
	private Integer folderNo; //폴더 번호
}
