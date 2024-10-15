package com.contractor.app.board.mapper;

import java.util.List;

import com.contractor.app.board.service.BoardsVO;

public interface BoardMapper {
	// 전체조회
	public List<BoardsVO> selectBoardAll();
}
