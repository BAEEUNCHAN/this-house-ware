package com.contractor.app.board.service;

import lombok.Data;

@Data
public class BoardsVO {
	private Integer boardsNo; //게시판 번호
	private String boardsType; //게시판 유형
	private Integer anonyChk; //익명 여부
	private String title; //제목
	private String description; //설명
	private String authority; //권한
}
