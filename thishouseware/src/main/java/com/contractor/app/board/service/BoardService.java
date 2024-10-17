package com.contractor.app.board.service;

import java.util.List;

public interface BoardService {
	// 게시판 전체 조회
	public List<BoardsVO> boardList(BoardsVO boardsVO);

	// 등록
	public int insertBoard(BoardsVO boardsVO);

	// 게시글 전체 조회
	public List<PostsVO> postList(PostsVO postsVO);
}
