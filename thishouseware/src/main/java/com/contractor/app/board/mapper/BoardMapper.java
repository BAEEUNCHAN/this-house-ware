package com.contractor.app.board.mapper;

import java.util.List;

import com.contractor.app.board.service.BoardsVO;
import com.contractor.app.board.service.PostsVO;

public interface BoardMapper {
	// 게시판 전체조회
	public List<BoardsVO> selectBoardAll(BoardsVO boardsVO);

	// 게시판 등록
	public int insertBoardInfo(BoardsVO boardsVO);

	// 게시글 전체조회
	public List<PostsVO> selectPostAll(PostsVO postsVO);
}
