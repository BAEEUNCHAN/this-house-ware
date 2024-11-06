package com.contractor.app.board.service;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BoardsVO {
	private Integer boardsNo; //게시판 번호
	private String boardsType; //게시판 유형
	private String departmentName; //부서명
	private Integer anonyChk; //익명 여부
	private String title; //제목
	private String description; //설명
	private String authority; //권한
	
	List<PostsVO> posts;
	
	private Integer departmentNo; // 부서 번호 추가
	
	@Override
	public boolean equals(Object obj) {
		return this.boardsType.equals(((BoardsVO)obj).boardsType);
	}
	
	@Override
	 public int hashCode() {
	    int prime = 31;
	    int result = 1;
	    result = prime * result + ((boardsType == null) ? 0 : boardsType.hashCode());
	    return result;
	  }
}
