package com.contractor.app.board.service;

import java.util.Date;

import lombok.Data;

@Data
public class PostsVO {
	private Integer postsNo; //게시글 번호
	private Integer boardsNo; //게시판 번호
	private String postsType; //게시글 유형
	private String title; //제목
	private String content; //내용
	private String postSetting; //게시 기간여부
	private Date postStartTime; //게시 시작시간
	private Date postEndTime; //게시 종료시간
	private String authority; //권한
	private String id; //아이디
	
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
