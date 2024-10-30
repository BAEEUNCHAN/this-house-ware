package com.contractor.app.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostsVO {
	private Integer postsNo; // 게시글 번호
	private Integer boardsNo; // 게시판 번호
	private String postsType; // 게시글 유형
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date submitDT; // 작성 날짜
	private String title; // 제목
	private String content; // 내용
	private String postSetting; // 게시 기간 여부 (q1: 게시, q2: 비게시)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date postStartTime; // 게시 시작시간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date postEndTime; // 게시 종료시간
	private String authority; // 권한
	private String id; // 아이디
	private String display; // 게시 상태 (q1: 게시, q2: 비게시)

	private String name; // 이름
	private String boardTitle; // 게시판 제목

	@Override
	public boolean equals(Object obj) {
		return this.postsType.equals(((PostsVO)obj).postsType);
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((postsType == null) ? 0 : postsType.hashCode());
		return result;
	}
}
