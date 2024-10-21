package com.contractor.app.board.mapper;

import java.util.List;

import com.contractor.app.board.service.BoardsVO;
import com.contractor.app.board.service.PostsVO;

public interface BoardMapper {
	// 게시판 전체조회
	public List<BoardsVO> selectBoardAll(BoardsVO boardsVO);

	// 게시글 전체조회
	public List<PostsVO> selectPostAll(PostsVO postsVO);

	// 게시글 단건조회 : 조건 - postsNo
	public PostsVO selectPostInfo(PostsVO postsVO);

	// 게시글 등록
	public int insertPostInfo(PostsVO postsVO);
	
	// 게시글 삭제
	public int deleteBoradInfo(int postsNo);
}
