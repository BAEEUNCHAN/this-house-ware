package com.contractor.app.board.service;

import java.util.List;

public interface BoardService {
	// 전체 조회
	public List<BoardsVO> boardList();
	
	// 등록
	public int insertBoard(BoardsVO boardsVO);
}
