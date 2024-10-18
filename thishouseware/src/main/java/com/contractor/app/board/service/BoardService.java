package com.contractor.app.board.service;

import java.util.List;

public interface BoardService {
	// 게시판 전체 조회
	public List<BoardsVO> boardList(BoardsVO boardsVO);

	// 게시글 전체 조회
	public List<PostsVO> postList(PostsVO postsVO);
	
	// 게시글 단건 조회
	public PostsVO postInfo(PostsVO postsVO);
	
	// 게시글 등록
	public int insertPost(PostsVO postsVO);
	
	// 게시글 삭제
	public int deletePost(int postsNo);
}
