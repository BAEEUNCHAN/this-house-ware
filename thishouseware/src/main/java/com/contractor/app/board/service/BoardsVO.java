package com.contractor.app.board.service;

import lombok.Data;

@Data
public class BoardsVO {
	private Integer boards_no; //게시판 번호
	private String boards_type; //게시판 유형
	private Integer anony_chk; //익명 여부
	private String title; //제목
	private String description; //설명
	private String authority; //권한
}
