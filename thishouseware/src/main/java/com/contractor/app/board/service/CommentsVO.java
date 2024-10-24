package com.contractor.app.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CommentsVO {
	private Integer commentsNo; //댓글 번호
	private Integer postsNo; //게시글 번호
	private String content; //내용
	private String authority; //권한
	private String id; //아이디
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date submitDT; // 작성 날짜
}
